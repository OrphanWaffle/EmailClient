package com.emailclient.classes;

import com.emailclient.filters.Private;

import java.time.LocalDateTime;

public class HelloFriend {
    private String Username;
    private String Nume;
    private String Prenume;
    private LocalDateTime data;

    public HelloFriend(){}

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
