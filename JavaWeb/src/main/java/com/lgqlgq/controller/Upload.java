package com.lgqlgq.controller;

import com.lgqlgq.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class Upload {

    //了解内容 企业一般使用云存储

    @PostMapping("/upload")
    public Result upload(String username, Integer age,MultipartFile image) throws IOException {
        log.info("传入数据{}{}{}", username, age, image);

        //防止出现重名现象 利用uuid重新取名
        String originalFilename = image.getOriginalFilename();
        originalFilename = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newOriginaFilename = UUID.randomUUID().toString()+originalFilename;

        //存储到本地
        image.transferTo(new File("E:/test/"+newOriginaFilename));

        return Result.success();
    }

}
