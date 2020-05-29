package com.emailclient.classes;

import java.time.LocalDate;

public class Users {
    private Integer id_user;
    private String username;
    private String nume;
    private String prenume;
    private String parola;
    private LocalDate data;
    private Integer id_selected_email;

    public Users() {

    }

    public Users(Integer id_user, String username, String nume, String prenume, String parola, LocalDate data, Integer id_selected_email) {
        this.id_user = id_user;
        this.username = username;
        this.nume = nume;
        this.prenume = prenume;
        this.parola = parola;
        this.data = data;
        this.id_selected_email = id_selected_email;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getId_selected_email() {
        return id_selected_email;
    }

    public void setId_selected_email(Integer id_selected_email) {
        this.id_selected_email = id_selected_email;
    }
}
