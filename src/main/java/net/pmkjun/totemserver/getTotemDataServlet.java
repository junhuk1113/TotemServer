package net.pmkjun.totemserver;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getTotemDataServlet", value = "/getTotemData")
public class getTotemDataServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String username = request.getParameter("username");

        String totemdata = TotemDB.getInstance().getTotemData(username);

        // Hello
        PrintWriter out = response.getWriter();
        out.println(totemdata);
    }
}
