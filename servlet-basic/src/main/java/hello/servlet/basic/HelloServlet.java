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
        System.out.println("--- Headers ํธ์ ์กฐํ start ----");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        printCookies(request);
        printContent(request);
        System.out.println("--- Headers ํธ์ ์กฐํ end ----");
    }

    private void printCookies(HttpServletRequest request) {
        System.out.println("[cookie ํธ์ ์กฐํ] start");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + " = " + cookie.getValue());
            }
        }
        System.out.println("[cookie ํธ์ ์กฐํ] end");
    }

    private void printContent(HttpServletRequest request) {
        System.out.println("[Content ํธ์ ์กฐํ] start");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        System.out.println("[Content ํธ์ ์กฐํ] end");
    }

    private void printEtc(HttpServletRequest request) {
        System.out.println("--- ๊ธฐํ ์กฐํ start ----- ");
        // ์ปค๋ฅ์ ๋งบ์ ๋์ ์?๋ณด๋ค
        System.out.println("[Remote ์?๋ณด]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        // ๋์ ์๋ฒ ์?๋ณด
        System.out.println("[Local ์?๋ณด]");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request = " + request);
    }
}
