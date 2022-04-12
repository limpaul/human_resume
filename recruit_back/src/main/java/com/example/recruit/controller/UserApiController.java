package com.example.recruit.controller;

import com.example.recruit.dto.MySelfInfo;
import com.example.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    private UserService userService;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/{num}") //사용자 아이디?
    public MySelfInfo mySelfInfo(@PathVariable("num") int num){
        return userService.mySelfInfoCheck(num);
    }

}
