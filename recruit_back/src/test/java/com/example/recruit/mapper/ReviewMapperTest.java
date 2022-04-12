package com.example.recruit.mapper;

import com.example.recruit.dto.ReviewListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
class ReviewMapperTest {

    @Autowired
    private ReviewMapper reviewMapper;
    @Test
    public void reviewTest(){
       List<ReviewListDto>  reviewListDtoList = reviewMapper.findReviewsByBbsId(1);
        System.out.println(reviewListDtoList);
    }
    @Test
    @Transactional
    public void deleteReviewById(){
        // 비밀번호를 물어본다
        Boolean result = reviewMapper.checkBbsPassword(8, 1234);
        assertEquals(true, result);
        if(result){
            int removeReviewResult = reviewMapper.deleteReview(8);
            assertEquals(1, removeReviewResult);
        }
    }
}