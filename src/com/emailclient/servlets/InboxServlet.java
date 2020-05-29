package com.emailclient.servlets;

import com.emailclient.classes.InboxMail;
import com.emailclient.dao.InboxDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InboxServlet extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String adresa = (String) req.getSession().getAttribute("Adresa");
        Integer id_mail = Integer.parseInt(req.getParameter("id_mail"));
        String path = req.getServletPath();

        try {
            if (path.equals("/Private/AccessMailIn"))
            {
                req.setAttribute("id_mail",id_mail);
                req.getRequestDispatcher("/Private/Mail").forward(req,res);
            }
            if (path.equals("/Private/DeleteInbox"))
            {
                InboxDAO.deleteMailIn(adresa, id_mail);
                res.sendRedirect(req.getContextPath() + "/Private/Inbox");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String User = (String) req.getSession().getAttribute("Username");

        List<InboxMail> inMail = new ArrayList<>();

        try {
            inMail= InboxDAO.Inbox(User);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("inMail",inMail);
        req.getRequestDispatcher("/Private/Inbox.jsp").forward(req,res);

    }
}
