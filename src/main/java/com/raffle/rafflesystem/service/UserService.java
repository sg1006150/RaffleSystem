package com.raffle.rafflesystem.service;

import com.raffle.rafflesystem.entity.LoginUser;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.exception.ServiceException;
import com.raffle.rafflesystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User login(User user)
    {
        User dbuser=userMapper.findByUsername(user.getUsername());
        if(dbuser==null)
        {
            //抛出异常
            throw new ServiceException("账号不存在");
        }
        if(!user.getPassword().equals(dbuser.getPassword())){
            throw new ServiceException("密码错误");
        }
        return dbuser;
    }

}
