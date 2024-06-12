package com.raffle.rafflesystem.controller;

import com.raffle.rafflesystem.common.Result;
import com.raffle.rafflesystem.entity.Prize;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.service.PrizeService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PrizeController {
    @Autowired
    private PrizeService prizeService;
    @GetMapping("/getAllPrize")
    public Result getAllPrize() {
        return Result.success(prizeService.getAllPrize());
    }
    @GetMapping("/getPrizeByUser")
    public Result getPrizeByUser(@RequestBody User user) {
        return Result.success(prizeService.getPrizeByUser(user));
    }
    @PostMapping("/addPrize")
    public Result addPrize(@RequestBody Prize prize) {
        prizeService.addPrize(prize);
        return Result.success(prize);
    }
    @DeleteMapping("/deletePrize")
    public Result deletePrize(@RequestBody Prize prize) {
        prizeService.deletePrize(prize);
        return Result.success(prize);
    }
    @PostMapping("/updatePrize")
    public Result updatePrize(@RequestBody Prize prize) {
        prizeService.updatePrize(prize);
        return Result.success(prize);
    }
}
