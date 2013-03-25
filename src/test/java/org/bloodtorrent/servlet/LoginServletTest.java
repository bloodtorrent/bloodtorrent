package org.bloodtorrent.servlet;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static junit.framework.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/22/13
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginServletTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);

    @Test
    public void shouldSetAttributeAndRedirectToRoot() {
        LoginServlet servlet = new LoginServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("iamnotadmin");
        when(request.getParameter("password")).thenReturn("p@ssw0rd");

        try {
            servlet.service(request, response);
            verify(response).sendRedirect("/logindb");
        } catch (ServletException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void shouldSetAttributeAndRedirectToAdmin() {
        LoginServlet servlet = new LoginServlet();
        when(request.getSession()).thenReturn(session);
        String emailID = "Administrator";
        when(request.getParameter("email")).thenReturn(emailID);
        when(request.getParameter("password")).thenReturn("p@ssw0rd");

        try {
            servlet.service(request, response);
            verify(response).sendRedirect("/logindb");
        } catch (ServletException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }
}
