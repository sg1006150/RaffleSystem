package com.raffle.rafflesystem.controller;

import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
    @GetMapping("/user")
    public List query()
    {
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
        return list;
    }
    @PostMapping("/user")
    public String save(User user)
    {
        int i = userMapper.insert(user);
        if (i > 0)
        {
            return "插入成功";
        }
        else
        {
            return "插入失败";
        }
    }

    @PostMapping("/userD")
    public String delete(int id)
    {
        int i = userMapper.deleteById(id);
        if(i > 0)
        {
            return "删除成功";
        }
        else
        {
            return "删除失败";
        }
    }


    @Autowired
    private UserMapper userMapper;
}
