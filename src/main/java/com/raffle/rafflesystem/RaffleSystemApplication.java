package com.raffle.rafflesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.raffle.rafflesystem.mapper")
public class RaffleSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaffleSystemApplication.class, args);
    }

}
