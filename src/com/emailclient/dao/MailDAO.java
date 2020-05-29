package com.emailclient.dao;

import com.emailclient.classes.ListaMailuri;
import com.emailclient.classes.Mail;
import com.emailclient.utils.C3P0conn;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MailDAO
{
    public static String Friends (String username ) throws Exception
    {
        Connection conn=C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;

        query = "select adresa " +
                "from useri eu join lista_prietenii on (id_user_req = eu.id_user) " +
                "join useri fren on (id_user_acc = fren.id_user) " +
                "join emailuri on (fren.id_selected_email = id_email) " +
                "where eu.username = ? and req_state = 1 " +
                "union " +
                "select adresa " +
                "from useri eu join lista_prietenii on (id_user_acc = eu.id_user) " +
                "join useri fren on (id_user_req = fren.id_user) " +
                "join emailuri on (fren.id_selected_email = id_email) " +
                "where eu.username = ? and req_state = 1 " +
                "order by 1;";

        st=conn.prepareStatement(query);
        st.setObject(1,username);
        st.setObject(2,username);
        rs = st.executeQuery();

        String ss = "";
        if(rs.next()) ss=rs.getObject("adresa",String.class);
        while(rs.next())
        {
            ss=ss+" ";
            ss=ss.concat(rs.getObject("adresa",String.class));
        }

        st.close();
        conn.close();
        rs.close();

        return ss;
    }
    public static String Receivers(String adresa, int id_mail) throws Exception {
        Connection conn=C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;
        ResultSet rs;

        query = "select adresa " +
                "from emailuri join trimiteri on (id_email = id_email_rec) " +
                "where id_mail = ? and adresa != ? " +
                "union " +
                "select adresa " +
                "from emailuri join trimiteri on (id_email = id_email_send) " +
                "where id_mail = ? and adresa != ? " +
                "order by 1;";

        st=conn.prepareStatement(query);
        st.setObject(1,id_mail);
        st.setObject(2,adresa);
        st.setObject(3,id_mail);
        st.setObject(4,adresa);
        rs = st.executeQuery();

        rs.next();
        String ss=rs.getObject("adresa",String.class);
        while(rs.next())
        {
            ss=ss+" ";
            ss=ss.concat(rs.getObject("adresa",String.class));
        }

        st.close();
        conn.close();
        rs.close();

        return ss;
    }

    public static void addMail(String user, String sendto, String subiect, String mesaj) throws Exception {
        Connection conn=C3P0conn.getInstance().getConn();
        String query;
        PreparedStatement st;

        query = "insert into mailuri (data, subiect, mesaj) " +
                "values(current_timestamp,?,?) ;";

        st=conn.prepareStatement(query);
        st.setObject(1,subiect);
        st.setObject(2,mesaj);
        st.execute();

        st.close();

        String[] Sendto = sendto.split("[\\s,]+");

        query = "insert into trimiteri (id_email_send, id_email_rec, id_mail) " +
                "values ((select id_selected_email from useri where username = ?), " +
                "(select id_email from emailuri where adresa = ?), " +
                "(SELECT AUTO_INCREMENT-1 " +
                "FROM  INFORMATION_SCHEMA.TABLES " +
                "WHERE TABLE_SCHEMA = 'eap_proiect_1' " +
                "AND   TABLE_NAME   = 'mailuri'));";

        st=conn.prepareStatement(query);

        for(String ss : Sendto)
        {
            st.setObject(1,user);
            st.setObject(2,ss);
            st.execute();
        }

        conn.close();
        st.close();
    }

    public static Mail mailInfo(Integer id_mail , String User) throws Exception {
        Connection conn=C3P0conn.getInstance().getConn();
        String query;
        Mail mail= new Mail();
        PreparedStatement st;
        ResultSet rs;

        query = "select send.adresa, data, mesaj, subiect,id_mail " +
                "from trimiteri t join emailuri send on (send.id_email=t.id_email_send) " +
                "join mailuri using (id_mail)" +
                "where id_mail = ?;";

        st=conn.prepareStatement(query);
        st.setObject( 1,id_mail);
        rs = st.executeQuery();

        if(rs.next())
        {
            mail.setId_mail(rs.getObject("id_mail",Integer.class));
            mail.setSubiect(rs.getObject("subiect",String.class));
            mail.setMesaj(rs.getObject("mesaj", String.class));
            mail.setData(rs.getObject("data", LocalDateTime.class));
            mail.setSender(rs.getObject("adresa",String.class));
        }

        conn.close();
        st.close();
        rs.close();

        conn=C3P0conn.getInstance().getConn();
        query = "select rec.adresa " +
                "from trimiteri t join emailuri rec on (rec.id_email=t.id_email_rec) " +
                "where id_mail = ?; ";

        st=conn.prepareStatement(query);
        st.setObject(1,id_mail);
        rs = st.executeQuery();

        List<String> list =new ArrayList<>();
        while (rs.next())
        {
            list.add(rs.getObject("adresa",String.class));
        }
        mail.setReceiver(list);

        st=conn.prepareStatement(query);

        query = "update trimiteri " +
                "set viewed = 1 " +
                "where id_email_rec = (select id_selected_email from useri where username = ?) and id_mail = ?;";

        st=conn.prepareStatement(query);
        st.setObject(1,User);
        st.setObject(2,id_mail);

        st.executeUpdate();
        st.close();
        conn.close();
        rs.close();

        return mail;
    }
}
