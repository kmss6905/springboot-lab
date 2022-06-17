package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-type: application/json
        // applicatoin/json 은 스펙상 utf-8 형식을 사용하도록 정의되어 있다. 그래서 스팩에서 charset=utf-8 과 같은 추가 파라미터를 지원하지 않는다.
        // 따라서 "application/json" 만 사용해야지, application/json;charset=utf-8 이라고 전달하는 것은 의미없는 파라미터를 추가한 것이 됨.
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "application/json;charset=utf-8");


        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("민식");

        String result = objectMapper.writeValueAsString(helloData);
//        response.getOutputStream().println(result);
        PrintWriter writer = response.getWriter();
        writer.write(result);
    }
}
