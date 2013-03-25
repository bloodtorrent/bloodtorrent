package org.bloodtorrent.servlet;

import org.bloodtorrent.dto.User;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/21/13
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginServlet extends HttpServlet {

//    private static final String ADMIN_ID = "Administrator";
//    private static final String ADMIN_PWD = "p@ssw0rd";

    @Override
    public void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {

        HttpSession session = servletRequest.getSession();
        String email = servletRequest.getParameter("email");
        String password = servletRequest.getParameter("password");

        session.setAttribute("email", email);
        session.setAttribute("password", password);
        servletResponse.sendRedirect("/logindb");

//
//        User user = new User();
//
//        if(ADMIN_ID.equals(email) && ADMIN_PWD.equals(password)){
//            user.setId(ADMIN_ID);
//            session.setAttribute("user", user);
//            session.setAttribute("adminCheck", email);
//            servletResponse.sendRedirect("/admin");
//        }
//        servletResponse.sendRedirect("/");
    }
}