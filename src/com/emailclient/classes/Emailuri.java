package com.emailclient.classes;

public class Emailuri {
    private Integer id_email;
    private Integer id_user_asignat;
    private String adresa;
    private String parola;

    public Emailuri(){

    }

    public Emailuri(Integer id_email, Integer id_user_asignat, String adresa, String parola) {
        this.id_email = id_email;
        this.id_user_asignat = id_user_asignat;
        this.adresa = adresa;
        this.parola = parola;
    }

    public Integer getId_email() {
        return id_email;
    }

    public void setId_email(Integer id_email) {
        this.id_email = id_email;
    }

    public Integer getId_user_asignat() {
        return id_user_asignat;
    }

    public void setId_user_asignat(Integer id_user_asignat) {
        this.id_user_asignat = id_user_asignat;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}
