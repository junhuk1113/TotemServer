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
        long lastTotemCooldownTime = Long.parseLong(request.getParameter("lastTotemCooldownTime"));
        int totem_X = Integer.parseInt(request.getParameter("totem_X"));
        int totem_Z = Integer.parseInt(request.getParameter("totem_Z"));
        String totemWorld = request.getParameter("totemWorld");
        String shareTotemPercentage = request.getParameter("shareTotemPercentage");
        boolean isMythicalWaterActive = request.getParameter("isMythicalWaterActive") == null ? false : request.getParameter("isMythicalWaterActive").equals("true");
        boolean isExpBoosterActive = request.getParameter("isExpBoosterActive") == null ? false : request.getParameter("isExpBoosterActive").equals("true");
        boolean isOverHotspotActive = request.getParameter("isOverHotspotActive") == null ? false : request.getParameter("isOverHotspotActive").equals("true");
        boolean isTreasureHunterActive = request.getParameter("isTreasureHunterActive") == null ? false : request.getParameter("isTreasureHunterActive").equals("true");
        boolean isEntropyHoarder = request.getParameter("isEntropyHoarder") == null ? false : request.getParameter("isEntropyHoarder").equals("true");
        String totemSlotString = request.getParameter("totemSlotString");

        int returnStat = TotemDB.getInstance().addTotemData(username, valueTotemCooldown, valueTotemActiveTime,
                valueTotemRange, lastTotemtime, lastTotemCooldownTime, totem_X, totem_Z, totemWorld,
                shareTotemPercentage, isMythicalWaterActive, isExpBoosterActive, isOverHotspotActive, isTreasureHunterActive, isEntropyHoarder, totemSlotString);

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
