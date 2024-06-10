package com.raffle.rafflesystem.service;

import com.raffle.rafflesystem.common.Result;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.exception.ServiceException;
import com.raffle.rafflesystem.mapper.UserMapper;
import com.raffle.rafflesystem.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User login(User user) {
        User dbuser=userMapper.findByPhone(user.getPhone());
        if(dbuser==null) {
            //抛出异常
            throw new ServiceException("用户不存在");
        }
        if(!user.getPassword().equals(dbuser.getPassword())){
            throw new ServiceException("密码错误");
        }
        String token= TokenUtils.getToken(dbuser.getPhone(),dbuser.getPassword());
        dbuser.setToken(token);
        return dbuser;
    }
    public User register(User user) {
        User existuser=userMapper.findByPhone(user.getPhone());
        if(existuser!=null) {
            throw new ServiceException("用户已存在");
        }
        int res=userMapper.insertUser(user);
        if(res==0) {
            throw new ServiceException("注册失败");
        }
        return user;
    }
    public User updateInformation(User user){
        int state=userMapper.updateUser(user);
        if(state==0) {throw new ServiceException("设置失败");}
        return user;
    }
    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }

}
