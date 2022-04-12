package com.example.recruit.mapper;

import com.example.recruit.domain.Bbs;
import com.example.recruit.domain.Review;
import com.example.recruit.dto.BbsContentDto;
import com.example.recruit.dto.BbsListDto;
import com.example.recruit.dto.BbsListInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BbsMapperTest {

    @Autowired
    private BbsMapper bbsMapper;

    @Autowired
    private ReviewMapper reviewMapper;


    @Test
    void writeBoardTest(){
        Bbs bbs = new Bbs();
        bbs.setBbsWriterId(13);
        bbs.setBbsWriter("tester");
        bbs.setBbsTitle("tester write title~");
        bbs.setBbsContent("tester write Content~");
        bbs.setBbsPw("1234");
        int result = bbsMapper.writeBoard(bbs);
        assertEquals(1, result);
    }

    @Test
    public void reviewTest(){
        Review review = new Review();
        review.setBbsId(1);
        review.setUserId(13);
        review.setReviewWriter("tester");
        review.setReviewContent("review by tester~~");
        review.setReviewPw("1234");
        int result = reviewMapper.writeReview(review);
        System.out.println(result);
    }

    @Test //
    public void listBoard(){ // 게시판 목록 조회 테스트
        List<BbsListDto> bbsList = bbsMapper.listBoard(1);
        bbsList.forEach(System.out::println);
    }
    @Test
    public void listBoardInfo(){
        BbsListInfo binfo = new BbsListInfo();
        binfo.setBbsListDto(bbsMapper.listBoard(1));
        binfo.setPage(bbsMapper.bbsPage());
        System.out.println(binfo);
    }

    @Test // reivew 가 null이 나오는거는 정상
    public void findBbsContentById(){ // 게시판 내용 상세조회 테스트
        BbsContentDto bbsContentDto = bbsMapper.findBbsContentById(1);
        System.out.println(bbsContentDto);
    }

    @Test
    @Transactional
    public void deleteBbsById(){
        // 비밀번호를 물어본다.
        Boolean result = bbsMapper.checkBbsPassword(1, "1234");
        assertEquals(true, result);
        if(result){
            int removeResult = bbsMapper.deleteBbs(1);
            assertEquals(1, removeResult);
        }
    }
    @Test
    public void bbsListInfoSearchByTitleTest(){
        bbsMapper.searchByTitleBbsList("b").forEach(System.out::println);
    }
}