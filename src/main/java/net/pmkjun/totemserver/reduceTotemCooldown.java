package net.pmkjun.totemserver;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "reduceTotemCooldown", value = "/reduceTotemCooldown")
public class reduceTotemCooldown extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        long lastTotemCooldownTime = Long.parseLong(request.getParameter("lastTotemCooldownTime"));

        int returnStat = TotemDB.getInstance().reduceTotemCooldown(username, lastTotemCooldownTime);

        // Hello
        PrintWriter out = response.getWriter();
        if(returnStat == 1){
            out.println("토템 데이터를 성공적으로 저장했습니다.");
        }
        else{
            out.println("해당 유저의 토템 데이터를 업데이트했습니다.");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
