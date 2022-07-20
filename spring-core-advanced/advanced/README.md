🚀 spring core 심화 2022-07-11 

### 손수 만든 로그추적기의 문제점 - 동시성문제
1. HTTP요청을 구분하고 깊이를 표현하기 위해서 `TraceId` 동기화가 필요하다.
2. `TraceId` 의 동기화를 위해서 관련 메서드의 모든 파라미터를 수정해야 한다.
3. 로그를 처음 시작할 때는 `begin()`을 호출하고, 처음이 아닐떄는 `beginSync()`를 호출해야한다.
   1. 만약에 컨트롤러를 통해서 서비스를 호출하는 것이 아니라, 다른 곳에서 서비스부터 호출하는 상황이라면 파라미터를 넘길`TraceId`가 없다.

제대로 된 로그(HTTP요청을 구분하고 깊이를 표현)를 남기기 위해 v0 -> v1 -> v2 로 점진적으로 서비스를 개선하였다.
개인적으로 느낀점은 모든 지 처음부터 뚝딱하고 원하는 모양의 코드형태는 절대로 나올 수 없다는 것이다.

HTTP 요청을 구분하고 깊이를 표현하기 위해서 `TraceId`를 파라미터로 넘기는 것 말고 다른 대안은 없을까?

`TraceId`를 넘기지 말고 이전 TraceId 정보를 개별 로직(서비스, 리포지토리)의 `LogTrace` 구현체에 정보를 갱신하고 사용한다.
하지만 우리가 빈으로 등록한 LogTrace 는 개별 요청에 대한 Level 수준에 대한 정보를 저장하고 관리하여 그 다음 호출된 서비스나 리포지토리에서 사용할 수 있도록 한다.

문제는 유저로부터 요청이 왔을 때 상태값이 섞이거나 제대로 관리가 되지 않되는 동시성문제가 일어날 수 밖에 없다.
A유저가 요청이 들어왔다. 상태를 "A-level-1"이라고 바꿔놓았다. 문제는 A유저가 끝까지 응답을 받기전에 B유저가 요청한다.
이때 B는 싱글톤으로 관리되고 있는 빈으로 부터 상태를 조사한다. 이때 B는 "A-level-1" 상태를 볼 것이다. 다른 유저의 값을 참조하고 있는 셈이다.
이건 우리가 원하는 모양이 아니다.

빈에서는 상태와 관련된 값을 담아서는 안된다. 정확히는 요청이 들어와서 응답할때까지 빈내부에 요청에 대한 상태를 유지하는 경우, 
하나의 상태를 공유하는 셈이기 때문에 동시성 문제가 일어날 수 있다.
즉. `FieldLogTrace` 는 싱글톤으로 스프링 빈이다. 이 객체의 인스턴스가 애플리케이션 딱 1존재한다. 이렇게 하나만 있는 
인스턴스의 `FieldLogTrace.traceHolder` 필드를 여러 쓰레드가 동시에 접근하기 때문에 문제가 발생한다.

### ThreadLocal
쓰레드 로컬은 해당 쓰레드만 접근할 수 있는 특별한 저장소를 말한다.
쓰레드 로컬을 통해서 데이터를 조회/저장 시 개별 쓰레드가 접근할 수 있는 변수가 따로 관리 됨.


### ThreadLocal 주의사항
쓰레드 로컬의 값을 사용 후 제거하지 않고 그냥 두면 WAS(톰켓)처럼 쓰레드 풀을 사용하는 경우에 심각한 문제가 발생할 수 있다.
따라서 요청이 끝날 때 쓰레드 로컬의 값을 `ThreadLocal.remove()`를 통해서 꼭 제거해야 한다.

### 핵심기능 vs 부가기능
핵심기능은 해당 객체가 제공하는 고유의 기능이다.
부가기능은 핵심기능과 함께 제공되는 기능이다. 예를들어서 로그 추적 로직, 트랜잭션 기능이 있다. 이러한 부가 기능은
단독으로 사용되지는 않고, 핵심 기능과 함께 사용된다. 

구조가 동일하다, 중간에 핵심 기능을 사용하는 코드만 다르다.
부가기능과 관련된 코드가 중복을 별도의 메서드로 뽑아 내면 될거같지만, try catch 는 물론이고, 핵심 기능 부분이 중간에 있어서 
단순하게 메서드로 추출하는 것은 어렵다.


**변하는 것과 변하지 않는 것을 분리**
좋은 설계는 변하는 것과 변하지 않는 것을 분리하는 것이다. 
여기서 핵심기능은 변하고, 로그 추척기를 사용하는 부분은 변하지 않는 부분이다.
탬플릿 메서드 페턴을 이를 해결하기위한 디자인 패턴이다.
```java
@Slf4j
public class SubClassLogic1 extends AbstractTemplate {

    @Override
    protected void call() {
        log.info("비즈니스 로직1 실행");
    }
}

public class Service{
    
   @Test
   void serviceTest() {
      AbstractTemplate template = new SubClassLogic1();
      template.execute();
   }
    
}


```

추가로 익명내부클래스를 사용하여 객체 인스턴스를 생성하면서 동시에 성할 클래스를 상속 받은 자식 클래스를 정의할 수 있다. 
이렇게 되면 매번 추상 클래스를 상속하는 클래스를 매번 만들 필요가 없게 된다.
하지만 비즈니스 로직 호출 메서드에 코드가 많아지면 그에 따라 전체적인 코드양도 똑같이 많아지는 단점이 있다.
```java

    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        template2.execute();
    }

```

```text
실행결과

00:12:53.813 [Test worker] INFO hello.advanced.trace.template.TemplateMethodTest - 비즈니스 로직1 실행
00:12:53.814 [Test worker] INFO hello.advanced.trace.template.code.AbstractTemplate - elapsedTime=2
00:12:53.814 [Test worker] INFO hello.advanced.trace.template.TemplateMethodTest - 비즈니스 로직2 실행
00:12:53.815 [Test worker] INFO hello.advanced.trace.template.code.AbstractTemplate - elapsedTime=1

```


