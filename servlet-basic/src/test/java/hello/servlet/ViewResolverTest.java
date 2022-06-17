package hello.servlet;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ViewResolverTest {

    @Test
    @DisplayName("뷰 논리 이름을 통해 실제 뷰의 경로 위치 반환 - 성공")
    void test() {
        ModelView mv = new ModelView("save-form");// save-form -> /WEB-INF/views/save-form.jsp

        assertThat(mv.getViewName()).isEqualTo("save-form");
    }

    @Test
    @DisplayName("modelview -> view 찾기")
    void test1() {
        ModelView mv = new ModelView("save-form");// save-form -> /WEB-INF/views/save-form.jsp
        assertThat(mv.getViewName()).isEqualTo("save-form");
    }

    public MyView viewResolver(String viewName){
        String prefix = "/WEB-INF/views/";
        String extension = ".jsp";
        return new MyView(prefix + viewName + extension);
    }
}
