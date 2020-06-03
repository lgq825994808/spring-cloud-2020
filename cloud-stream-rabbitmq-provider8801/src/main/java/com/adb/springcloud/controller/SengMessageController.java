package com.adb.springcloud.controller;

import com.adb.springcloud.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SengMessageController {

    @Autowired
    private IMessageService iMessageService;

    @GetMapping(value = "/sendMessage")
    private String send(){
        return iMessageService.send();
    }
}
