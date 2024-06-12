package com.raffle.rafflesystem.mapper;

import com.raffle.rafflesystem.entity.Participant;
import com.raffle.rafflesystem.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ParticipantMapper {
    @Select("select * from participants")
    List<Participant> getAllParticipants();
    @Select("select * from participants where addedby=#{username}")
    List<Participant> getParticipantsByUser(User user);
    @Insert("insert into participants (name,phone,addedby) " +
            "values(#{name},#{phone},#{addedby})")
    int addParticipant(Participant participant);
    @Delete("delete from participants where id=#{id}")
    int deleteParticipant(int id);
    @Update("update participants set name=#{name}, phone=#{phone} where id=#{id}")
    int updateParticipant(Participant participant);
    @Delete("delete from participants where id=#{id}")
    int deleteParticipantById(int id);

}