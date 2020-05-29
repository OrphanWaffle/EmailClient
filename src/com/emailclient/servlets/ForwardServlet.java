package com.emailclient.servlets;

import com.emailclient.classes.HelloFriend;
import com.emailclient.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ForwardServlet extends HttpServlet
    {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String User = (String) req.getSession().getAttribute("Username");

        List<HelloFriend> Request = new ArrayList<>();
        List<HelloFriend> Friend = new ArrayList<>();

        try {
            Request = UserDAO.userRequests(User);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Friend = UserDAO.userFriends(User);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("Request",Request);
        req.setAttribute("Friend",Friend);
        req.getRequestDispatcher("/Private/Friends.jsp").forward(req,res);
    }
    }
