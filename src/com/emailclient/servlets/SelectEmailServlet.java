package com.emailclient.servlets;

import com.emailclient.dao.UserDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectEmailServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    {
        String Username = (String)req.getSession().getAttribute("Username");
        Integer id_email = Integer.parseInt(req.getParameter("id_email"));

        try {
            UserDAO.updateEmail(Username,id_email);
            req.getSession().setAttribute("Adresa",UserDAO.userEmail(Username));
            res.sendRedirect(req.getContextPath()+"/Private/Inbox");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
