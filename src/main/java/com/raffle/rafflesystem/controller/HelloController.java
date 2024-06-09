package com.raffle.rafflesystem.controller;

import com.raffle.rafflesystem.common.Result;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class HelloController {
    @Operation(description = "abc",summary = "bedc")//为api添加注释
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
    @Autowired
    private UserMapper userMapper;
}
