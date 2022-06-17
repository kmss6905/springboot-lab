package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);

        response.getWriter().print("ok");
    }

    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- Start Line start");
        System.out.println("request.getMethod() = " + request.getMethod());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getProtocol() = " + request.getProtocol());
        System.out.println("request.getScheme() = " + request.getScheme());
        System.out.println("--- Start Line end");
    }

    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers start ----");
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " +  headerName));
        System.out.println("--- Headers end ----");
    }

    private void printHeaderUtils(HttpServletRequest request){
        System.out.println("--- Headers 편의 조회 start ----");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        printCookies(request);
        printContent(request);
        System.out.println("--- Headers 편의 조회 end ----");
    }

    private void printCookies(HttpServletRequest request) {
        System.out.println("[cookie 편의 조회] start");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + " = " + cookie.getValue());
            }
        }
        System.out.println("[cookie 편의 조회] end");
    }

    private void printContent(HttpServletRequest request) {
        System.out.println("[Content 편의 조회] start");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        System.out.println("[Content 편의 조회] end");
    }

    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ----- ");
        // 커넥션 맺을 때의 정보들
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        // 나의 서버 정보
        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request = " + request);
    }
}
