package com.raffle.rafflesystem.controller;

import com.raffle.rafflesystem.common.Result;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.exception.ServiceException;
import com.raffle.rafflesystem.service.UserService;
import com.raffle.rafflesystem.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getCurrentUser")
    public Result<User> getCurrentUser() {
        User currentUser = TokenUtils.getUser();
        if (currentUser==null)
        {
            throw new ServiceException(401,"请登录");
        }
        return Result.success(currentUser);
    }
    @GetMapping("/getAllUsers")
    public Result<List<User>>getAllUsers(){
        User currentUser = TokenUtils.getUser();
/*        if(currentUser==null){
            throw new ServiceException(401,"请登录");
        }
        if(!Objects.equals(currentUser.getType(), "admin")){
            throw new ServiceException(402,"权限不足");
        }*/
        List<User> users = userService.getAllUsers();
        return Result.success(users);
    }
}
