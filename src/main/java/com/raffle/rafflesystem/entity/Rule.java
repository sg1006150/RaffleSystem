package com.raffle.rafflesystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class Rule {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String rulename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean isIstemporary() {
        return istemporary;
    }

    public void setIstemporary(boolean istemporary) {
        this.istemporary = istemporary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRulename() {
        return rulename;
    }

    public void setRulename(String rulename) {
        this.rulename = rulename;
    }

    private Date createtime;
    private String createdby;
    private String description;
    private boolean istemporary;
    

}
