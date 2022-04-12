package com.example.recruit.mapper;

import com.example.recruit.domain.Bbs;
import com.example.recruit.domain.Review;
import com.example.recruit.domain.User;
import com.example.recruit.dto.BbsContentDto;
import com.example.recruit.dto.BbsListDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BbsMapper bbsMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    public void joinTest(){
        User user = new User();
        user.setUserId("hongildong");
        user.setUserPw("1234");
        user.setUserEmail("hong@korea.com");
        user.setUsername("hongildong");

        int result = userMapper.join(user);
        Assertions.assertEquals(1, result);
    }



}