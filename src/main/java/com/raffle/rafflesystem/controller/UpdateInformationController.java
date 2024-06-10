package com.raffle.rafflesystem.controller;

import com.raffle.rafflesystem.common.Result;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UpdateInformationController {
    @Autowired
    private UserService userService;
    @PostMapping("/updateinform")
    public Result updateInformation(@RequestBody User user) {
        user=userService.updateInformation(user);
        return Result.success(user);
    }
}
