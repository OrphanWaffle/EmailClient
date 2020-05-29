package com.emailclient.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class C3P0conn {
    private static C3P0conn dataSource;
    private  ComboPooledDataSource pooledData;
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/eap_proiect_1";
    private static String user="root";
    private static String pass="";

    public C3P0conn() throws Exception {
        this.pooledData = new ComboPooledDataSource();
        this.pooledData.setDriverClass("com.mysql.cj.jdbc.Driver");
        this.pooledData.setJdbcUrl(jdbcUrl);
        this.pooledData.setUser(user);
        this.pooledData.setPassword(pass);
    }

    public static C3P0conn getInstance () throws Exception
    {
        if(dataSource == null) dataSource = new C3P0conn();
        return dataSource;
    }
    public Connection getConn() throws Exception
    {
        return pooledData.getConnection();
    }
}
