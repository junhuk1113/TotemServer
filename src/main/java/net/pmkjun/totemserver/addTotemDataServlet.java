package net.pmkjun.totemserver;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addTotemDataServlet", value = "/addTotemData")
public class addTotemDataServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        int valueTotemCooldown = Integer.parseInt(request.getParameter("valueTotemCooldown"));
        int valueTotemActiveTime = Integer.parseInt(request.getParameter("valueTotemActiveTime"));
        int valueTotemRange = Integer.parseInt(request.getParameter("valueTotemRange"));
        long lastTotemtime = Long.parseLong(request.getParameter("lastTotemtime"));
        int totem_X = Integer.parseInt(request.getParameter("totem_X"));
        int totem_Z = Integer.parseInt(request.getParameter("totem_Z"));
        String totemWorld = request.getParameter("totemWorld");

        int returnStat = TotemDB.getInstance().addTotemData(username, valueTotemCooldown, valueTotemActiveTime, valueTotemRange, lastTotemtime, totem_X, totem_Z, totemWorld);

        // Hello
        PrintWriter out = response.getWriter();
        if(returnStat == 0){
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
//http://localhost:8181/MCModServlet_war_exploded/addTotemData?username=dd&valueTotemCooldown=2&valueTotemActiveTime=3&valueTotemRange=4&lastTotemtime=12345&totem_X=-133&totem_Z=-12
