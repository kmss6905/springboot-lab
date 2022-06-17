package hello.servlet.web.servlet;

import hello.servlet.basic.HelloData;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * 모든 것이 하나의 메서드안에 다 모여 있음..
     * 비지니스로직에 집중하기 어렵다..
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 파싱
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 데이터 생성
        Member member = new Member(username, age);
        System.out.println("member = " + member);

        // 저장
        memberRepository.save(member);

        // 응답 헤더 설정
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        // 출력
        PrintWriter writer = response.getWriter();

        // 응답 컨텐드 바디 생성
        writer.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
