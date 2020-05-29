package com.emailclient.servlets;

import com.emailclient.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class RapoarteServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String adresa = (String) req.getSession().getAttribute("Adresa");
        String username = (String) req.getSession().getAttribute("Username");

        String rap_1 = null;
        LocalDate rap_2 = null;
        try {
            rap_1 = UserDAO.mostMailsSent(adresa);
            rap_2 = UserDAO.mostActiveDay(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("rap_1",rap_1);
        req.setAttribute("rap_2",rap_2);
        req.getRequestDispatcher("/Private/Rapoarte.jsp").forward(req,res);
    }
}
