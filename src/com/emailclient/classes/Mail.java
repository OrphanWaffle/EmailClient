package com.emailclient.classes;

import com.emailclient.dao.MailDAO;

import java.time.LocalDateTime;
import java.util.List;

public class Mail {
    private Integer id_mail;
    private List<String> receiver;
    private String sender;
    private LocalDateTime data;
    private String subiect;
    private String mesaj;

    public String getReceivers(String adresa) throws Exception {
        return MailDAO.Receivers(adresa , this.id_mail);
    }

    public String getFriends(String username) throws Exception {
        return MailDAO.Friends(username);
    }

    public Mail(){

    }

    public Integer getId_mail() {
        return id_mail;
    }

    public void setId_mail(Integer id_mail) {
        this.id_mail = id_mail;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getSubiect() { return subiect; }

    public void setSubiect(String subiect) {
        this.subiect = subiect;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
}
