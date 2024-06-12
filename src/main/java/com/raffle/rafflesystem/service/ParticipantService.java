package com.raffle.rafflesystem.service;

import com.raffle.rafflesystem.entity.Participant;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.exception.ServiceException;
import com.raffle.rafflesystem.mapper.ParticipantMapper;
import com.raffle.rafflesystem.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantMapper participantMapper;

    public List<Participant> getAllParticipants() {
        User currentUser = TokenUtils.getUser();
        if (currentUser == null || !Objects.equals(currentUser.getType(), "admin")) {
            throw new ServiceException(402, "权限不足");
        }
        return participantMapper.getAllParticipants();
    }

    public List<Participant> getParticipantByUser() {
        User currentUser = TokenUtils.getUser();
        return participantMapper.getParticipantsByUser(currentUser);
    }
    public void addParticipant(Participant participant) {
        int flag=participantMapper.addParticipant(participant);
        if(flag==0){
            throw new ServiceException("添加失败");
        }
    }
    public void updateParticipant(Participant participant) {
        User currentUser = TokenUtils.getUser();
        assert currentUser != null;
        if(!Objects.equals(currentUser.getUsername(), participant.getAddedby())){
            throw new ServiceException(402,"权限不足");
        }
        int flag=participantMapper.updateParticipant(participant);
        if(flag==0){
            throw new ServiceException("添加失败");
        }
    }
    public void deleteParticipant(Participant participant) {
        User currentUser = TokenUtils.getUser();
        assert currentUser != null;
        if(!Objects.equals(currentUser.getUsername(), participant.getAddedby())){
            throw new ServiceException(402,"权限不足");
        }
        int flag=participantMapper.deleteParticipantById(participant.getId());
        if(flag==0){
            throw new ServiceException("删除失败");
        }
    }
}
