package com.raffle.rafflesystem.entity;

public class LoginUser {
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    private String password;

}
