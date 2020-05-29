package com.emailclient.servlets;

import com.emailclient.classes.Mail;
import com.emailclient.dao.MailDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MailServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String User = (String) req.getSession().getAttribute("Username");
        Integer id_mail = (Integer) req.getAttribute("id_mail");
        Mail mail = new Mail();
        try {
            mail = MailDAO.mailInfo(id_mail,User);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("mail",mail);
        req.getRequestDispatcher("/Private/Mail.jsp").forward(req,res);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String User = (String) req.getSession().getAttribute("Username");
        Integer id_mail = Integer.parseInt((String) req.getSession().getAttribute("id_mail"));
        Mail mail = new Mail();
        try {
            mail = MailDAO.mailInfo(id_mail,User);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("mail",mail);
        req.getRequestDispatcher("/Private/Mail.jsp").forward(req,res);
    }
}
