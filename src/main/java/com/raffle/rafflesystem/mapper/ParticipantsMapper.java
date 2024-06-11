package com.raffle.rafflesystem.mapper;

import com.raffle.rafflesystem.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ParticipantsMapper {
    @Select("select * from participants")
    List<User> getAllParticipants();
    @Select("select * from participants where addedby=#{user}")
    List<User> getParticipantsByUser(User user);



}