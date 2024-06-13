package com.raffle.rafflesystem.controller;

import com.raffle.rafflesystem.common.Result;
import com.raffle.rafflesystem.entity.Rule;
import com.raffle.rafflesystem.service.RuleService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RuleController {
    @Autowired
RuleService ruleService;
    @PostMapping("/addRule")
    public Result addRule(@RequestBody Rule rule) {
        return Result.success(ruleService.addRule(rule));
    }
}
