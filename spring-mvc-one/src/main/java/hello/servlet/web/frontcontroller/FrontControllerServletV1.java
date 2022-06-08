package hello.servlet.web.frontcontroller;

import hello.servlet.web.frontcontroller.v1.MvcMemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.MvcMemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.MvcMemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members", new MvcMemberListControllerV1());
        controllerMap.put("/front-controller/v1/members/new-form", new MvcMemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MvcMemberSaveControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        ControllerV1 controller = controllerMap.get(request.getRequestURI());
        if(request.getRequestURI() == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        controller.process(request, response);
    }
}
