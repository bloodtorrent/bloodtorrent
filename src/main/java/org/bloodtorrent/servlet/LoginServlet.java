package org.bloodtorrent.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    public static String URI_PATH = "/login";

    @Override
    public void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {

        HttpSession session = servletRequest.getSession();
        String email = servletRequest.getParameter("email");
        String password = servletRequest.getParameter("password");

        session.setAttribute("email", email);
        session.setAttribute("password", password);
        servletResponse.sendRedirect("/logindb");
    }
}