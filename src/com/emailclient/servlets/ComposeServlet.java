package com.emailclient.servlets;

import com.emailclient.dao.MailDAO;
import com.emailclient.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ComposeServlet extends HttpServlet
{
    public void Send(HttpServletRequest req , HttpServletResponse res)
    {
        String User = (String) req.getSession().getAttribute("Username");
        String sendto= req.getParameter("Sendto");
        String Subiect = req.getParameter("Subiect");
        String Mesaj = req.getParameter("Mesaj");

        String[] Sendto = sendto.split("[\\s,]+");
        boolean a=true;

        for(String ss : Sendto)
        {
            try {
                if(UserDAO.verifyAddress(ss)==false)
                {
                    a=false;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            if (a==true)
            {
                MailDAO.addMail(User, sendto, Subiect, Mesaj);
                res.sendRedirect(req.getContextPath() + "/Private/Outbox");
            }
            else
            {
                req.setAttribute("Sendto",sendto);
                req.setAttribute("Subiect",Subiect);
                req.setAttribute("Mesaj",Mesaj);
                req.setAttribute("mesaj","Adresa invalida !");
                req.getRequestDispatcher("/Private/Compose.jsp").forward(req,res);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void replyAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        req.setAttribute("Sendto",req.getParameter("Sendto"));
        req.setAttribute("Subiect",req.getParameter("Subiect"));
        req.getRequestDispatcher("/Private/Compose.jsp").forward(req,res);
    }

    public void Forward(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        req.setAttribute("Subiect",req.getParameter("Subiect"));
        req.setAttribute("Mesaj",req.getParameter("Mesaj"));
        req.getRequestDispatcher("/Private/Compose.jsp").forward(req,res);
    }

    public void ForwardFriends(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        req.setAttribute("Sendto",req.getParameter("Sendto"));
        req.setAttribute("Subiect",req.getParameter("Subiect"));
        req.setAttribute("Mesaj",req.getParameter("Mesaj"));
        req.getRequestDispatcher("/Private/Compose.jsp").forward(req,res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String path = req.getServletPath();
        if (path.equals("/Private/ComposeRedirect"))
        {
            if((String)req.getSession().getAttribute("Adresa") == null || ((String)req.getSession().getAttribute("Adresa")).equals("") )
            {
                res.sendRedirect(req.getContextPath() + "/Private/RegisterEmail.jsp");
                return;
            }
            else
            {
                res.sendRedirect(req.getContextPath() + "/Private/Compose.jsp");
                return;
            }
        }
        if (path.equals("/Private/Compose")) Send(req, res);
        if (path.equals("/Private/ReplyAll")) replyAll(req, res);
        if (path.equals("/Private/Forward")) Forward(req, res);
        if (path.equals("/Private/ForwardFriends")) ForwardFriends(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String path = req.getServletPath();
        if (path.equals("/Private/ComposeRedirect"))
        {
            if((String)req.getSession().getAttribute("Adresa") == null || ((String)req.getSession().getAttribute("Adresa")).equals("") )
            {
                res.sendRedirect(req.getContextPath() + "/Private/RegisterEmail.jsp");
                return;
            }
            else
            {
                res.sendRedirect(req.getContextPath() + "/Private/Compose.jsp");
                return;
            }
        }
    }
}
