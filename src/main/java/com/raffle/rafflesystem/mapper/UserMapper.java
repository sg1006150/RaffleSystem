package com.raffle.rafflesystem.mapper;

import com.raffle.rafflesystem.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    @Select("select id,username,phone,email,type,isValid from user")
    List<User> getAllUser();
    @Select("select * from user where phone=#{Phone}")
    User findByPhone(String Phone);
    @Insert("insert into user(phone,password) values(#{phone},#{password})")
    int insertUser(User user);
    @Update("update user set phone=#{phone},email=#{email},username=#{username} where id=#{id}")
    int updateUser(User user);
    @Select("select * from user where username=#{username}")
    User findByUsername(String username);
    @Update("update user set isValid=#{isValid} where id=#{id}")
    int updateValid(User user);
}
