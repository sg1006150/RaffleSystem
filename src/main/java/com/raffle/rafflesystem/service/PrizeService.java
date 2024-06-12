package com.raffle.rafflesystem.service;

import com.raffle.rafflesystem.entity.Prize;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.exception.ServiceException;
import com.raffle.rafflesystem.mapper.PrizeMapper;
import com.raffle.rafflesystem.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PrizeService {
    @Autowired
    PrizeMapper prizeMapper;
    public List<Prize> getAllPrize(){
        if(!Objects.equals(Objects.requireNonNull(TokenUtils.getUser())
                .getType(), "admin")){
            throw new ServiceException(401,"权限不足");
        }
        return prizeMapper.getAllPrize();
    }
    public List<Prize> getPrizeByUser(User user){
        return prizeMapper.getPrizeByUsername(user.getUsername());
    }
    public void addPrize(Prize prize){
        int flag=prizeMapper.addPrize(prize);
        if(flag==0){
            throw new ServiceException("添加失败");
        }
    }
    public void deletePrize(Prize prize){
        int flag= prizeMapper.deletePrizeById(prize.getId());
        if(flag==0){
            throw new ServiceException("删除失败");
        }
    }
    public void updatePrize(Prize prize){
        int flag=prizeMapper.updatePrize(prize);
        if(flag==0){
            throw new ServiceException("更新失败");
        }
    }
}
