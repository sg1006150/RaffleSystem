package com.raffle.rafflesystem.entity;

public class User {
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    private String username;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
