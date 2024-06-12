package com.raffle.rafflesystem.controller;

import com.raffle.rafflesystem.common.Result;
import com.raffle.rafflesystem.entity.Participant;
import com.raffle.rafflesystem.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;
    @GetMapping("/getAllParticipants")
    public Result<List<Participant>> getParticipant() {
        return Result.success(participantService.getAllParticipants());
    }
    @GetMapping("/getParticipantsByUser")
    public Result<List<Participant>>getParticipantByUser() {
        return Result.success(participantService.getParticipantByUser());
    }
    @PostMapping("/addParticipant")
    public Result<Participant> insertParticipant(@RequestBody Participant participant) {
        participantService.addParticipant(participant);
        return Result.success(participant);
    }
    @PostMapping("/updateParticipant")
    public Result<Participant> updateParticipant(@RequestBody Participant participant) {
        participantService.updateParticipant(participant);
        return Result.success(participant);
    }
    @DeleteMapping("/deleteParticipant")
    public Result<Participant>deleteParticipant(@RequestBody Participant participant) {
        participantService.deleteParticipant(participant);
        return Result.success(participant);
    }
}
