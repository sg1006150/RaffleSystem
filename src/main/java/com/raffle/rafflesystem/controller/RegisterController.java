package com.raffle.rafflesystem.controller;

import com.raffle.rafflesystem.common.Result;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RegisterController
{
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@RequestBody User user)
    {
        User newuser=userService.register(user);
        return Result.success(newuser);
    }

}
