package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 메시지 바디의 내용을 바이트코드로 얻는다.
        ServletInputStream inputStream = request.getInputStream();

        // 바이트코드를 String 으로 변환(이미 Spring 에서 유틸리티 제공함), 항상 바이트 <-> 문자 변환시 인코딩 정보를 알려줘야함
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("body = " + body);

        response.getWriter().write("ok");
    }
}
