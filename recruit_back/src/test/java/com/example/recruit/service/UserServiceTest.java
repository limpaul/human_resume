package com.example.recruit.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void myInfoCheck(){ // 본인 이력서 번호 조회
        System.out.println(userService.mySelfInfoCheck(2));
    }
}