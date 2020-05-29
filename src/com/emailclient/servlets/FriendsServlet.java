package com.emailclient.servlets;

import com.emailclient.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FriendsServlet extends HttpServlet
{
    public void AddFriend(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String User = (String) req.getSession().getAttribute("Username");
        String username = req.getParameter("Sendto");

        try {
            if(User.equals(username))
            {
                req.setAttribute("mesaj", "Nu iti poti trimite cerere de prietenie tie !");
                req.getRequestDispatcher("/Private/Friends").forward(req, res);
            }
            else {
                if (UserDAO.verifyUsername(username) == true)
                {
                    if (UserDAO.verifyFriend(User, username) == true)
                    {
                        req.setAttribute("mesaj", "Cerere deja existenta !");
                        req.getRequestDispatcher("/Private/Friends").forward(req, res);
                    }
                    else
                    {
                        UserDAO.sendFriendReq(User, username);
                        req.setAttribute("mesaj", "Cerere trimisa !");
                        req.getRequestDispatcher("/Private/Friends").forward(req, res);
                    }
                }
                else
                {
                    req.setAttribute("mesaj", "Acest user nu exista !");
                    req.getRequestDispatcher("/Private/Friends").forward(req, res);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Accept(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String User = (String) req.getSession().getAttribute("Username");
        String username = req.getParameter("User");

        UserDAO.acceptFriendRequest(username,User);
        res.sendRedirect(req.getContextPath() + "/Private/Friends");
    }

    public void Decline(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String User = (String) req.getSession().getAttribute("Username");
        String username = req.getParameter("User");

        UserDAO.declineFriendRequest(username,User);
        res.sendRedirect(req.getContextPath() + "/Private/Friends");
    }

    public void Delete(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String User = (String) req.getSession().getAttribute("Username");
        String username = req.getParameter("User");

        UserDAO.deleteFriendRequest(username,User);
        res.sendRedirect(req.getContextPath() + "/Private/Friends");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String path = req.getServletPath();
        try {
            if (path.equals("/Private/AddFriend")) AddFriend(req, res);
            if (path.equals("/Private/Accept")) Accept(req, res);
            if (path.equals("/Private/Decline")) Decline(req, res);
            if (path.equals("/Private/Delete")) Delete(req, res);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
