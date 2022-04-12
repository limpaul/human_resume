package com.example.recruit.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BbsServiceTest {
    @Autowired
    private BbsService bbsService;

    @Test
    public void bbsList(){ // 게시글 목록
        bbsService.bbsList(1).forEach(System.out::println);
    }
    @Test
    public void findBbsContentTest(){ // 게시글 상세 내용
        System.out.println(bbsService.bbsContentList(1));
    }

    @Test
    public void deleteBbsById(){
        // 비밀번호를 물어본다
        Boolean result = bbsService.removeBbs(1, "1234");
        assertEquals(true, result);

    }
    @Test
    @Transactional
    public void deleteReviewById(){
        // 비밀번호를 물어본다
        Boolean result = bbsService.removeReview(8, 1234);
        assertEquals(true, result);
    }
    @Test
    public void bbsListInfoSearchByTitle(){
        System.out.println(bbsService.searchByTitleBbsList("a"));
    }

    @Test
    public void searchByContentBbsList(){
        System.out.println(bbsService.searchByContentBbsList("b"));
    }
    @Test
    public void searchByTitleAndContentBbsList(){
        System.out.println(bbsService.searchByTitleAndContentBbsList("a"));
    }
}