package com.raffle.rafflesystem.entity;

import java.util.Date;

public class Prize {
    private int id;
    private String name;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getPicdirectory() {
        return picdirectory;
    }

    public void setPicdirectory(String picdirectory) {
        this.picdirectory = picdirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String picdirectory;
    private Date updatetime;
    private double price;
}
