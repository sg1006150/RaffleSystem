package com.raffle.rafflesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")//处理get请求
    public String hello(String name)
    {
        return "Hello Worl"+name;
    }
    @GetMapping("/hello/{id}")//动态路径，要加pathvariable标记
    public String Hello(@PathVariable int id)
    {
        return String.valueOf(id);
    }
}
