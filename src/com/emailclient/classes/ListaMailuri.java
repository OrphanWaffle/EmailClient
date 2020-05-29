package com.emailclient.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ListaMailuri {
    private Integer id_mail;
    private LocalDateTime data;
    private String subiect;
    private String mesaj;

    public ListaMailuri(){

    }

    public ListaMailuri(Integer id_mail, LocalDateTime data, String subiect, String mesaj) {
        this.id_mail = id_mail;
        this.data = data;
        this.subiect = subiect;
        this.mesaj = mesaj;
    }

    public Integer getId_mail() {
        return id_mail;
    }

    public void setId_mail(Integer id_mail) {
        this.id_mail = id_mail;
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
