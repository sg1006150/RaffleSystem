package com.raffle.rafflesystem.controller;

import com.raffle.rafflesystem.common.Result;
import com.raffle.rafflesystem.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@CrossOrigin
@RestController
public class ImageController {
String uploadPath="src/main/resources/static/upload";
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath = uploadPath +"/"+ fileName;
            file.transferTo(new File(filePath).getAbsoluteFile());
            return Result.success(fileName);
        }catch (IOException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
