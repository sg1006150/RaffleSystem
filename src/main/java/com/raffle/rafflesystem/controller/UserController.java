package com.raffle.rafflesystem.controller;

import com.raffle.rafflesystem.common.Result;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.exception.ServiceException;
import com.raffle.rafflesystem.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {
    @GetMapping("/getCurrentUser")
    public Result<User> getCurrentUser() {
        User currentUser = TokenUtils.getUser();
        if (currentUser==null)
        {
            throw new ServiceException(401,"请登录");
        }
        return Result.success(currentUser);
    }

}
