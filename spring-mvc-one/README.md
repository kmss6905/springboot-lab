MVC 패턴 개요

**너무 많은 역할**

**변경의 라이프 사이클**

**기능 특화**

**Model View Controller**
하나의 서블릿이나, JSP로 처리하던 것을 컨트롤러(Controller)와 뷰(View)라는 영역으로
서로 역할을 나눈 것을 말함. 웹 애플리케이션은 보통 이 MVC 패턴을 사용함

**컨트롤러**: HTTP 요청을 받아서 파라미터를 검증하고, 비즈니스 로직을 실행, 그리고 뷰에 전달할 결과 데이터를 조회에서 모델에 담음

**모델**: 뷰에 출력할 데이터를 담아줌. 뷰가 필요한 데이터를 모두   모델에 담아서 전달해주는 덕분에 뷰는 
비즈니스 로직이나 데이터 접근을 몰라도 되고, 화면을 렌더링 하는 일에 집중할 수 있다.

**뷰** : 모델에 담겨있는 데이터를 사용해서 화면을 그리는 일에 집중한다. 여기서는 HTML을 생성하는 부분을 말함


### MVC 패턴의 한계
* 포워드 중복
* ViewPath에 중복
  * 사용하지 않는 코드
  * ```java
    HttpServletRequest request, HttpSErvletResponse response
    ```
* 공통 처리가 어렵다.
  * 기능이 복잡해질 수록 컨트롤러에서 공통으로 처리해야하는 부분이 늘어나는 경우 관리가 되지 않음. 물론 공통기능을 메서드로 뽑으면 되지만, 결과적으로 
  해당 메서드를 항상 호출해야하는 등, 실수로 호출하지 않으면 또한 문제가 발생함. (공통처리가 어렵다!!)


### 점진적인 발전
v1 : 프론트 컨트롤러 도입

v2 : View 분류

v3 : Model 추가

v4 : 단순하고 실용적인 컨트롤러

v5 : 유연한 컨트롤러

프레임워크나 공통기능이 수고로워야 사용하는 개발자가 편리해진다. 


### SpringMVC 구조
1. FrontController -> DispatcherServlet
2. handlerMappingMap -> HandlerMapping
3. MyHandlerAdapter -> HandlerAdapter
4. ModelView -> ModelAndView
5. viewResolver -> ViewResolver(interface)
6. MyView -> View(interface)

**DispatcherServlet** 구조
스프링 MVC도 프론트 컨트롤러 패턴으로 구현되어 있음
* 스프링 부트는 `DispatcherServlet` 을 서블릿으로 자동등록하면서 모든 경로(urlPatterns = "/)에 대해서 매핑한다.
* 참고 : 더 자세한 경로가 우선순위가 높다. 그래서 기존에 등록한 서블릿도 함께 동작한다.


**요청 흐름**
* 서블릿이 호출되면 `HttpServlet`이 제공하는 `service()` 가 호출된다.
* 스프링 MVC 는 `DispatcherServlet`의 부모인 `FrameworkServelt`에서 `Service()`를 오버라이드 해두었다.
* `FrameworkServlet.servie()`를 시작으로 여러 메서드가 호출되면서 `DispatcherServlet` 의 `doDispatch()`가 호출됨

1. 핸들러 조회
2. 핸들러 어댑터 조회 - 핸들러 처리할 수 있는 어댑터
3. 핸들러 어댑터 실행 -> 4. 핸들어 어댑터를 통해 핸들러 실행 -> 5. ModelAndView 반환

뷰 랜더링 호출

6. 류 리졸버를 통해서 뷰 찾기
7. View 반환
8. 뷰 렌더링