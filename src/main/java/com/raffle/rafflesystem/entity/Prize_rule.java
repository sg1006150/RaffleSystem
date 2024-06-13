package com.raffle.rafflesystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Prize_rule {
    private int prize_id;
    private int rule_id;
    private String prize_level;
    private int count;
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    public int getPrize_id() {
        return prize_id;
    }

    public void setPrize_id(int prize_id) {
        this.prize_id = prize_id;
    }

    public int getRule_id() {
        return rule_id;
    }

    public void setRule_id(int rule_id) {
        this.rule_id = rule_id;
    }

    public String getPrize_level() {
        return prize_level;
    }

    public void setPrize_level(String prize_level) {
        this.prize_level = prize_level;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
