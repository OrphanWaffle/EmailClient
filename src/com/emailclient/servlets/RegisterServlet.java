package com.emailclient.servlets;

import com.emailclient.dao.UserDAO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class RegisterServlet extends HttpServlet {

    public boolean verifyPass(String parola,String confirmparola, HttpServletRequest req)
    {
        if (parola.equals(confirmparola)) return true;
        else req.setAttribute("mesaj","Parola Gresita");
        return false;

    }

    public boolean verifyUsername(String username, HttpServletRequest req) throws Exception
    {
        if(UserDAO.verifyUsername(username)){
            req.setAttribute("mesaj","Username deja in folosire");
            return false;
        }
        else return true;
    }

    public boolean verify(String username, String parola, String confirmparola , HttpServletRequest req) throws Exception
    {
        return verifyPass(parola,confirmparola,req) && verifyUsername(username,req);
    }

    public void Register(HttpServletRequest req, HttpServletResponse res)
    {
        String username = req.getParameter("Username");
        String nume = req.getParameter("Nume");
        String prenume = req.getParameter("Prenume");
        String parola = req.getParameter("Parola");
        String confirmparola = req.getParameter("ConfirmParola");
        String datans = req.getParameter("DataN");

        LocalDate datan = LocalDate.parse(datans, DateTimeFormatter.ISO_LOCAL_DATE);

        try {
            if(verify(username,parola,confirmparola,req))
            {
                String hashpass = DigestUtils.sha256Hex(parola.getBytes());

                UserDAO.RegisterUser(username, nume, prenume, hashpass, datan);
                req.getSession().setAttribute("Username",username);
                res.sendRedirect(req.getContextPath() + "/Login.jsp");
            }
            else
            {
                req.getRequestDispatcher("/Register.jsp").forward(req,res);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyAddress(String adresa, HttpServletRequest req) throws Exception
    {
        if(UserDAO.verifyAddress(adresa)){
            req.setAttribute("mesaj","Adresa deja in folosire");
            return false;
        }
        else return true;
    }

    public void AddEmail(HttpServletRequest req, HttpServletResponse res)
    {
        String adresa = req.getParameter("Adresa");
        String parola = req.getParameter("Parola");

        try {
            if(verifyAddress(adresa,req)) {
                String hashpass = DigestUtils.sha256Hex(parola.getBytes());
                String User = (String) req.getSession().getAttribute("Username");
                UserDAO.RegisterEmail(User, adresa, hashpass);
                req.getSession().setAttribute("ListaEmail",UserDAO.EmailList((String) req.getSession().getAttribute("Adresa")));
                res.sendRedirect(req.getContextPath() + "/Private/Inbox");
            }
            else
            {
                req.getRequestDispatcher("/Private/RegisterEmail.jsp").forward(req,res);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path=req.getServletPath();
        if(path.equals("/Register")) Register(req,res);
        if(path.equals("/Private/AddEmail")) AddEmail(req,res);

    }
}
