package com.emailclient.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.getSession().setAttribute("Username",null);
        req.getSession().setAttribute("Adresa",null);
        req.getSession().setAttribute("id_mail",null);
        req.getSession().setAttribute("ListaEmail", null);
        res.sendRedirect(req.getContextPath() + "/Login.jsp");
    }
}
