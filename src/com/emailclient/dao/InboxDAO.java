package com.emailclient.dao;

import com.emailclient.classes.InboxMail;
import com.emailclient.utils.C3P0conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InboxDAO
{
    public static void deleteMailIn(String adresa, Integer id_mail) throws Exception
    {
        Connection conn = C3P0conn.getInstance().getConn();
        PreparedStatement st;
        String query;

        query = "update trimiteri " +
                "set deleted_rec = 1 " +
                "where id_mail = ? and id_email_rec = (select id_email from emailuri where adresa = ?);";

        st = conn.prepareStatement(query);
        st.setObject(1,id_mail);
        st.setObject(2,adresa);
        st.executeUpdate();

        conn.close();
        st.close();
    }

    public static List<InboxMail> Inbox(String user) throws Exception {
        Connection conn = C3P0conn.getInstance().getConn();
        PreparedStatement st;
        String query;
        ResultSet rs;
        List<InboxMail> inMail = new ArrayList<>();

        query = "select id_mail, e.adresa, m.data, m.subiect, t.viewed " +
                "from trimiteri t join mailuri m using (id_mail) " +
                "join emailuri e on (e.id_email = t.id_email_send) " +
                "where t.deleted_rec = 0 " +
                "and id_email_rec = (select id_selected_email from useri where username = ?)" +
                "order by 3 desc ; ";

        st = conn.prepareStatement(query);
        st.setObject(1, user);
        rs = st.executeQuery();

        while(rs.next())
        {
            InboxMail obMail = new InboxMail();
            obMail.setId_mail(rs.getObject("id_mail",Integer.class));
            obMail.setAdresa(rs.getObject("adresa", String.class));
            obMail.setData(rs.getObject("data", LocalDateTime.class));
            obMail.setSubiect(rs.getObject("subiect",String.class));
            obMail.setViewed(rs.getObject("viewed",Boolean.class));
            inMail.add(obMail);
        }

        conn.close();
        st.close();
        rs.close();
        return inMail;
    }
}
