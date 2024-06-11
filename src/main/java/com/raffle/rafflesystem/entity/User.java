package com.raffle.rafflesystem.entity;

import java.math.BigInteger;
import java.util.Date;

public class User {
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Boolean getValid() {
        return isValid;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    private Boolean isValid;
    private Date validUntil;
    private String username;

    private String password;
    private String token;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    private String phone;

    public void setId(String id) {
        this.id = id;
    }

    private String email;
private String id;
}
