package net.pmkjun.totemserver;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "removeTotemDataServlet", value = "/removeTotemData")
public class removeTotemDataServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        response.setContentType("text/html");

        String username = request.getParameter("username");

        int stat = TotemDB.getInstance().removeTotemData(username);

        PrintWriter out = response.getWriter();
        if(stat == 1){
            out.println("유저 데이터 제거 완료");
        }
        else {
            out.println("해당 유저를 찾을 수 없습니다.");
        }

    }
}
