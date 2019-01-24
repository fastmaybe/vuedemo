package com.example.vuedemo.pojo;

import java.io.Serializable;


public class User  {

    private Long id;
    private String username;
    private String password;

    private int isDefault;
    private String keyP;


    public int getDefault() {
        return isDefault;
    }

    public void setDefault(int aDefault) {
        isDefault = aDefault;
    }

    public String getKeyP() {
        return keyP;
    }

    public void setKeyP(String keyP) {
        this.keyP = keyP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
