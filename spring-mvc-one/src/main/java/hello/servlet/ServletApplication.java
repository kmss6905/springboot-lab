package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ServletComponentScan // 스프링부트에서 서블릿을 사용하자, 서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}


	/**
	 * 스프링부트가 자동으로 등록하는 뷰 리졸버
	 * 1. BeanViewResolver(예, 엑셀 파일 생성 기능에 사용)
	 * 2. InternalResourceViewResolver : JSP를 처리할 수 있는 뷰를 반환한다. (내부에서 자원 이동)
	 * InternalResourceView 라는 것을 반환함. JSP 를 forward 하는 기능이 있음
	 * view.render() 가 호출되고 InternalResource 는 forward()를 사용해서 JSP 를 실행한다.
	 *
	 * 참고
	 * 다른 뷰는 실제 뷰를 헨더링 하지만, forward() 통해서 해당 jsp 로 이동(실행) 해야 렌더링 된다.
	 * JSP를 제외한 나머지 뷰 템플릿들은 forward() 과정없이 바로 렌더링 된다.
	 *
	 * 참고
	 * Thymeleaf 뷰 템플릿을 사용하면 ThymeleafViewResolver 를 등록해야한다. 최근에는 라이브러리만 추가하면 스프링부트가 이런 작업도 모두 자동화 해준다.
	 */
	// view resolver 가 등록된다.
	@Bean
	ViewResolver internalResourceViewResolver() {
		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
	}

}
