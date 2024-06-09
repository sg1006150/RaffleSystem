package com.raffle.rafflesystem.controller;

import com.raffle.rafflesystem.common.Result;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.exception.ServiceException;
import com.raffle.rafflesystem.service.UserService;
import io.jsonwebtoken.io.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        if(Objects.equals(user.getPassword(), "") || Objects.equals(user.getUsername(), "")) {
                throw new ServiceException("数据不合法");
            }
            user = userService.login(user);
            return Result.success(user);
        }

}
