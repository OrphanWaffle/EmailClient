package com.emailclient.dao;

import com.emailclient.classes.HelloFriend;
import com.emailclient.utils.C3P0conn;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO
{
    public static void acceptFriendRequest(String username1, String username2) throws Exception
    {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;

        query = "update lista_prietenii " +
                "set req_state = 1 " +
                "where id_user_req = (select id_user from useri where username = ?) and id_user_acc = (select id_user from useri where username = ?);";

        st = conn.prepareStatement(query);
        st.setObject(1,username1);
        st.setObject(2,username2);
        st.executeUpdate();

        st.close();
        conn.close();
    }

    public static void declineFriendRequest(String username1, String username2) throws Exception
    {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;

        query = "delete from lista_prietenii " +
                "where id_user_req = (select id_user from useri where username = ?) and id_user_acc = (select id_user from useri where username = ?);";

        st = conn.prepareStatement(query);
        st.setObject(1,username1);
        st.setObject(2,username2);
        st.executeUpdate();

        st.close();
        conn.close();

    }

    public static void deleteFriendRequest(String username1, String username2) throws Exception
    {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;

        query = "delete from lista_prietenii " +
                "where id_user_acc=(select id_user from useri where username = ?) " +
                "and id_user_req=(select id_user from useri where username = ?) " +
                "or id_user_req=(select id_user from useri where username = ?) " +
                "and id_user_acc=(select id_user from useri where username = ?);";

        st = conn.prepareStatement(query);
        st.setObject(1,username1);
        st.setObject(2,username2);
        st.setObject(3,username1);
        st.setObject(4,username2);
        st.executeUpdate();

        st.close();
        conn.close();

    }

    public static void sendFriendReq(String username1, String username2) throws Exception {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;

        query = "insert into lista_prietenii(id_user_req, id_user_acc, data) " +
                "values((select id_user from useri where username = ?),(select id_user from useri where username = ?),current_timestamp) ;";

        st = conn.prepareStatement(query);
        st.setObject(1,username1);
        st.setObject(2,username2);
        st.execute();

        st.close();
        conn.close();
    }

    public static boolean verifyUsername(String username) throws Exception {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;

        query = "select id_user from useri where username = ?;";
        st = conn.prepareStatement(query);
        st.setObject(1, username);
        rs = st.executeQuery();

        boolean a = rs.next();
        rs.close();
        st.close();
        conn.close();
        return a;
    }

    public static boolean verifyFriend(String username1, String username2) throws Exception
    {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;

        query = "select 1 " +
                "from useri acc join lista_prietenii on (acc.id_user = id_user_acc) " +
                "join useri req on (req.id_user = id_user_req) " +
                "where acc.username = ? and req.username = ? or req.username = ? and acc.username = ?;";

        st = conn.prepareStatement(query);
        st.setObject(1,username1);
        st.setObject(2,username2);
        st.setObject(3,username1);
        st.setObject(4,username2);
        rs = st.executeQuery();

        boolean a = rs.next();
        rs.close();
        st.close();
        conn.close();
        return a;
    }

    public static String verifyLogin(String username) throws Exception {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;

        query = "select parola from useri where username <=> ?;";
        st = conn.prepareStatement(query);
        st.setObject(1, username);
        rs = st.executeQuery();

        String s = null;
        if (rs.next()) s = rs.getObject("parola", String.class);
        rs.close();
        st.close();
        conn.close();
        return s;
    }

    public static void RegisterUser(String username, String nume, String prenume, String parola, LocalDate datan) throws Exception {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;

        query = "insert into useri (username, nume, prenume, parola, datan)" +
                "values(?,?,?,?,?);";

        st = conn.prepareStatement(query);
        st.setObject(1, username);
        st.setObject(2, nume);
        st.setObject(3, prenume);
        st.setObject(4, parola);
        st.setObject(5, datan);

        st.execute();

        st.close();
        conn.close();
    }

    public static boolean verifyAddress(String adresa) throws Exception {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;

        query = "select id_email from emailuri where adresa <=> ?;";
        st = conn.prepareStatement(query);
        st.setObject(1, adresa);
        rs = st.executeQuery();

        boolean a = rs.next();
        rs.close();
        st.close();
        conn.close();
        return a;
    }

    public static void RegisterEmail(String user,String adresa, String parola) throws Exception
    {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;

        query = "insert into emailuri (id_user_asignat,adresa,parola) " +
                "values ((select id_user from useri where username = ? ),?,?)";

        st = conn.prepareStatement(query);
        st.setObject(1,user);
        st.setObject(2,adresa);
        st.setObject(3,parola);

        st.execute();

        st.close();

        query = "update useri set id_selected_email = (SELECT AUTO_INCREMENT-1 " +
                "FROM  INFORMATION_SCHEMA.TABLES " +
                "WHERE TABLE_SCHEMA = 'eap_proiect_1' " +
                "AND   TABLE_NAME   = 'emailuri')" +
                "where username = ?;";

        st = conn.prepareStatement(query);
        st.setObject(1,user);
        st.execute();

        st.close();
        conn.close();
    }

    public static Map<String,Integer> EmailList (String adresa) throws Exception {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;

        query = "select adresa, id_email " +
                "from emailuri " +
                "where id_user_asignat = (select id_user_asignat from emailuri where adresa = ?);";

        st = conn.prepareStatement(query);
        st.setObject(1,adresa);
        rs = st.executeQuery();

        Map<String,Integer> emails = new HashMap<>();
        while(rs.next())
        {
            emails.put(rs.getObject("adresa",String.class),rs.getObject("id_email",Integer.class));
        }
        conn.close();
        st.close();
        rs.close();

        return emails;
    }

    public static void updateEmail(String username,Integer id_email) throws Exception {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;

        query = "update useri " +
                "set id_selected_email = ? " +
                "where username = ?;";

        st = conn.prepareStatement(query);
        st.setObject(1,id_email);
        st.setObject(2,username);
        st.executeUpdate();

        conn.close();
        st.close();
    }

    public static String userEmail(String user) throws Exception
    {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;

        query = "select adresa " +
                "from useri join emailuri on ( id_selected_email = id_email ) " +
                "where username <=> ? ;";

        st = conn.prepareStatement(query);
        st.setObject(1,user);
        rs = st.executeQuery();

        String s = null;
        if(rs.next()) s= rs.getObject("adresa",String.class);

        conn.close();
        st.close();
        rs.close();

        return s;
    }

    public static List<HelloFriend> userRequests(String user) throws Exception
    {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;
        List<HelloFriend> Request= new ArrayList<>();

        query = "select req.username, req.nume, req.prenume ,data " +
                "from useri acc join lista_prietenii on (acc.id_user = id_user_acc) " +
                "join useri req on (req.id_user = id_user_req) " +
                "where req_state = 0 and acc.username = ? " +
                "order by 4 desc ;";

        st = conn.prepareStatement(query);
        st.setObject(1,user);
        rs = st.executeQuery();

        while(rs.next())
        {
            HelloFriend fr = new HelloFriend();

            fr.setUsername(rs.getObject("username",String.class));
            fr.setNume(rs.getObject("nume",String.class));
            fr.setPrenume(rs.getObject("prenume",String.class));
            fr.setData(rs.getObject("data",LocalDateTime.class));
            Request.add(fr);
        }

        conn.close();
        st.close();
        rs.close();
        return Request;
    }

    public static List<HelloFriend> userFriends(String user) throws Exception
    {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;
        List<HelloFriend> Friend= new ArrayList<>();

        query = "select req.username, req.nume, req.prenume " +
                "from useri acc join lista_prietenii on (acc.id_user = id_user_acc) " +
                "join useri req on (req.id_user = id_user_req) " +
                "where req_state = 1 and acc.username = ? " +
                "union " +
                "select acc.username, acc.nume, acc.prenume " +
                "from useri acc join lista_prietenii on (acc.id_user = id_user_acc) " +
                "join useri req on (req.id_user = id_user_req) " +
                "where req_state = 1 and req.username = ?" +
                "order  by 1 ;";

        st = conn.prepareStatement(query);
        st.setObject(1,user);
        st.setObject(2,user);
        rs = st.executeQuery();

        while(rs.next())
        {
            HelloFriend fr = new HelloFriend();

            fr.setUsername(rs.getObject("username",String.class));
            fr.setNume(rs.getObject("nume",String.class));
            fr.setPrenume(rs.getObject("prenume",String.class));
            Friend.add(fr);
        }

        conn.close();
        st.close();
        rs.close();
        return Friend;
    }

    public static String mostMailsSent(String adresa) throws Exception
    {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;

        query = "select username " +
                "from trimiteri join emailuri on (id_email_rec = id_email) " +
                "join useri on (id_email=id_selected_email) " +
                "where id_email_send in (select id_email " +
                "                        from emailuri " +
                "                        where id_user_asignat = (select id_user_asignat " +
                "                                                from emailuri " +
                "                                                where adresa = ?))" +
                "group by id_user, username " +
                "having count(id_mail) =(select count(id_mail) counts " +
                "                        from trimiteri join emailuri on (id_email_rec = id_email) " +
                "                        join useri on (id_email=id_selected_email) " +
                "                        where id_email_send in (select id_email " +
                "                                                from emailuri " +
                "                                                where id_user_asignat = (select id_user_asignat " +
                "                                                                        from emailuri " +
                "                                                                        where adresa = ?)) " +
                "                        group by id_user, username " +
                "                        order by counts desc limit 1) " +
                "order by 1;";

        st = conn.prepareStatement(query);
        st.setObject(1,adresa);
        st.setObject(2,adresa);
        rs = st.executeQuery();

        String ss="";
        while(rs.next())
        {
            ss=ss.concat(rs.getObject("username",String.class));
            ss=ss+" ";
        }

        conn.close();
        st.close();
        rs.close();
        return ss;
    }

    public static LocalDate mostActiveDay(String username) throws Exception
    {
        Connection conn = C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;

        query = "select cast(data as date) day " +
                "from mailuri join trimiteri using (id_mail) " +
                "where id_email_send in (select id_email " +
                "                        from emailuri " +
                "                        where id_user_asignat = (select id_user from useri where username = ?)) " +
                "or id_email_rec in (select id_email " +
                "                    from emailuri " +
                "                    where id_user_asignat = (select id_user from useri where username = ?)) " +
                "group by day " +
                "order by count(data) desc ,data desc limit 1;";

        st = conn.prepareStatement(query);
        st.setObject(1,username);
        st.setObject(2,username);
        rs = st.executeQuery();

        LocalDate dd=null;
        if(rs.next()) dd=rs.getObject("day",LocalDate.class);

        conn.close();
        st.close();
        rs.close();
        return dd;
    }
}
