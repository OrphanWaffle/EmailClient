package com.emailclient.classes;

public class ListaTrimiteri {
    private Integer id_email_send;
    private Integer id_email_rec;
    private Integer id_mail;
    private Boolean viewed;

    public ListaTrimiteri(){

    }

    public ListaTrimiteri(Integer id_email_send, Integer id_email_rec, Integer id_mail, Boolean viewed) {
        this.id_email_send = id_email_send;
        this.id_email_rec = id_email_rec;
        this.id_mail = id_mail;
        this.viewed = viewed;
    }

    public Integer getId_email_send() {
        return id_email_send;
    }

    public void setId_email_send(Integer id_email_send) {
        this.id_email_send = id_email_send;
    }

    public Integer getId_email_rec() {
        return id_email_rec;
    }

    public void setId_email_rec(Integer id_email_rec) {
        this.id_email_rec = id_email_rec;
    }

    public Integer getId_mail() {
        return id_mail;
    }

    public void setId_mail(Integer id_mail) {
        this.id_mail = id_mail;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }
}
