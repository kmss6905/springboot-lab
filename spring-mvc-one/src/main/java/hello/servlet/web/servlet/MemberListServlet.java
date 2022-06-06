package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     *
     * 뷰 생성 및 데이터 넣는 것 까지 모두 하나의 메서드안에 들어있음.
     * 하나의 메서드 안에 넣으면, 코드를 파악하기가 힘듬.
     * 사실 두가지로 나눠볼 수 있음. 회원정보들을 가져오는 것 + 회원정보들을 뷰에 뿌리는 것
     * 모두다 혼재되어 있음..
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");


        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write(" <meta charset=\"UTF-8\">");
        w.write(" <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write(" <thead>");
        w.write(" <th>id</th>");
        w.write(" <th>username</th>");
        w.write(" <th>age</th>");
        w.write(" </thead>");
        w.write(" <tbody>");
        /*
        w.write(" <tr>");
        w.write(" <td>1</td>");
        w.write(" <td>userA</td>");
        w.write(" <td>10</td>");
        w.write(" </tr>");
        */
        for (Member member : members) {
            w.write(" <tr>");
            w.write(" <td>" + member.getId() + "</td>");
            w.write(" <td>" + member.getUsername() + "</td>");
            w.write(" <td>" + member.getAge() + "</td>");
            w.write(" </tr>");
        }
        w.write(" </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");

        // 너무 어렵다.. 템플릿 엔진을 사용하여 html에 자바코드를 넣어보자.
        // html 에 필요한 곳만 코드를 적용해서 적용해서 동적으로 변경할 수 있다.

    }
}
