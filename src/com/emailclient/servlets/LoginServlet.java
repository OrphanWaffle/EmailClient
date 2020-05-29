package com.emailclient.servlets;

import com.emailclient.dao.UserDAO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
    {
        String username = req.getParameter("Username");
        String parola = req.getParameter("Parola");

        String hashpass = DigestUtils.sha256Hex(parola.getBytes());
        try {
            if(hashpass.equals(UserDAO.verifyLogin(username)))
            {

                req.getSession().setAttribute("Username",username);
                req.getSession().setAttribute("Adresa",UserDAO.userEmail(username));
                req.getSession().setAttribute("ListaEmail",UserDAO.EmailList((String) req.getSession().getAttribute("Adresa")));
                res.sendRedirect(req.getContextPath() + "/Private/Inbox");

            }
            else
            {
                req.setAttribute("mesaj","Username sau parola gresita !");
                req.getRequestDispatcher("/Login.jsp").forward(req,res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
