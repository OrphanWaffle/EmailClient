package com.emailclient.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InboxMail {
    private Integer id_mail;
    private String adresa;
    private LocalDateTime data;
    private String subiect;
    private Boolean viewed;

    public InboxMail()
    {

    }

    public Integer getId_mail() {
        return id_mail;
    }

    public void setId_mail(Integer id_mail) {
        this.id_mail = id_mail;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getSubiect() {
        return subiect;
    }

    public void setSubiect(String subiect) {
        this.subiect = subiect;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }
}
