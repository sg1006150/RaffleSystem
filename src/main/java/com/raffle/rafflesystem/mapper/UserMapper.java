package com.raffle.rafflesystem.mapper;

import com.raffle.rafflesystem.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user")
    public List<User> find();
    @Select("select * from user where username=#{username}")
    User findByUsername(String username);
    @Insert("insert into user(phone) values(#{phone})")
    int insertUser(User user);
    @Select("select * from user where phone=#{phone}")
    User findByPhone(User user);
}
