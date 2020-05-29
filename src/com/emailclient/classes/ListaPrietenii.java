package com.emailclient.classes;

import java.time.LocalDate;


public class ListaPrietenii {
    private Integer id_user_req;
    private Integer id_user_acc;
    private Boolean req_state;
    private LocalDate data;

    public ListaPrietenii(){

    }

    public ListaPrietenii(Integer id_user_req, Integer id_user_acc, Boolean req_state, LocalDate data) {
        this.id_user_req = id_user_req;
        this.id_user_acc = id_user_acc;
        this.req_state = req_state;
        this.data = data;
    }

    public Integer getId_user_req() {
        return id_user_req;
    }

    public void setId_user_req(Integer id_user_req) {
        this.id_user_req = id_user_req;
    }

    public Integer getId_user_acc() {
        return id_user_acc;
    }

    public void setId_user_acc(Integer id_user_acc) {
        this.id_user_acc = id_user_acc;
    }

    public Boolean isReq_state() {
        return req_state;
    }

    public void setReq_state(boolean req_state) {
        this.req_state = req_state;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
