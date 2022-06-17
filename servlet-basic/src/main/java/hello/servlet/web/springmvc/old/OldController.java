package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * spring 에서 제공하는 @Controller 이전에 사용했던 간단한 컨트롤러
 *
 * 아래의 컨트롤러가 호출되기 위해서는 두 가지가 필요하다.
 * 1. 핸들러 매핑
 * 핸들러 매핑(핸들러 매핑 정보)에서 아래의 핸들러를 찾을 수 있어야 한다. ( 특정 url 이 들어왔을 때 해당 url 에 맞는 요청을 처리할 수 있는 헨들러를 찾아야한다. )
 * 예) 스프링 빈의 이름으로 핸들러를 찾을 수 있는 핸들러 매핑이 있어야 한다.
 *
 * 2. 핸들러 어댑터
 * 핸드러 매핑을 통해서 해당 요청을 처리할 수 있는 핸들러를 찾은 후, 해당 핸들러를 실행할 수 있는 핸들러 어댑터가 필요하다.
 * 예) Controller 인터페이스를 실행할 수 있는 핸들러 어댑터를 찾고 실행해야 한다.
 *
 * 하지만 이미 스프링은 핸들러 매핑과 어댑터를 미리 구현해 놓았다. 디스패쳐서블릿이 스프링에서 정의한 전략(어떤 핸들러 매핑과, 어댑터를 사용해야 하는 지)에 따라
 * 매핑과 어댑터를 선택한다.
 * 스프링은 자동으로 등록하는 핸들러 매핑과 어뎁터가 있다.
 *
 * ex), 핸들러매핑 :
 * 1. RequestMappingHandlerMapping (어노테이션 기반의 컨트롤러인 @RequestMapping)
 * 2. BeanNameHandlerMapping (빈 이름으로 찾는 핸들러 매핑)
 *
 * ex)  어뎁터들 :
 * 1. RequestMappingHandlerAdapter(어노테이션 기반의 컨트롤러인 @RequestMapping)
 * 2. HttpRequestHandlerAdapter(HttpRequestHandler 처리)
 * 3. SimpleControllerHandlerAdapter( Controller 인터페이스 처리(@Controller 와 상관 x ) )
 *
 * 결론
 *
 * OldController 가 실행되면 서 사용된 객체
 * 1. HandlerMapping : BeanNameUrlHandlerMapping
 * 2. HandlerAdapter : SimpleControllerHandlerAdapter
 */
@Component("/springmvc/old-controller")
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
