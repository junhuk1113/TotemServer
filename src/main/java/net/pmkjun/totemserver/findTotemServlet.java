package net.pmkjun.totemserver;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "findTotemServlet", value = "/findTotemData")
public class findTotemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        double player_X = Double.parseDouble(request.getParameter("player_X"));

        double player_Z = Double.parseDouble(request.getParameter("player_Z"));

        String playerWorld = request.getParameter("playerWorld");

        String returnData = TotemDB.getInstance().findTotemData(player_X, player_Z, playerWorld);

        // Hello
        PrintWriter out = response.getWriter();
        out.print(returnData);
    }
}

