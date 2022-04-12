package com.example.recruit.service;

import com.example.recruit.domain.Bbs;
import com.example.recruit.domain.Review;
import com.example.recruit.dto.BbsContentDto;
import com.example.recruit.dto.BbsListDto;
import com.example.recruit.dto.BbsListInfo;
import com.example.recruit.dto.ReviewListDto;
import com.example.recruit.mapper.BbsMapper;
import com.example.recruit.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BbsService {
    @Autowired
    private BbsMapper bbsMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    public List<BbsListDto> bbsList(int page){
        page = (page - 1) * 5;
        List<BbsListDto> bbsListDto = bbsMapper.listBoard(page);
        return bbsListDto;
    }
    public BbsListInfo bbsListInfo(int page) {
        page = (page - 1) * 5;
        BbsListInfo bbsListInfo = new BbsListInfo();
        bbsListInfo.setBbsListDto(bbsMapper.listBoard(page));
        bbsListInfo.setPage(bbsMapper.bbsPage());
        return bbsListInfo;
    }
    public BbsContentDto findBbsContentById(int bbsId){ // 게시판 내용 상세조회 테스트
        BbsContentDto bbsContentDto = bbsMapper.findBbsContentById(bbsId);

        List<ReviewListDto> reviewListDtoList = reviewMapper.findReviewsByBbsId(bbsId);
        bbsContentDto.setReview(reviewListDtoList);

        return bbsContentDto;
    }

    public BbsListInfo searchByTitleBbsList(String title){
        BbsListInfo bbsListInfo = new BbsListInfo();
        bbsListInfo.setBbsListDto(bbsMapper.searchByTitleBbsList(title));
        bbsListInfo.setPage(bbsMapper.searchCount(title));
        return bbsListInfo;
    }

    public BbsListInfo searchByContentBbsList(String content){
        BbsListInfo bbsListInfo = new BbsListInfo();
        bbsListInfo.setBbsListDto(bbsMapper.searchByContentBbsList(content));
        bbsListInfo.setPage(bbsMapper.searchCount(content));
        return bbsListInfo;
    }

    public BbsListInfo searchByTitleAndContentBbsList(String titleAndContent){
        BbsListInfo bbsListInfo = new BbsListInfo();
        bbsListInfo.setBbsListDto(bbsMapper.searchByTitleAndContentBbsList(titleAndContent));
        bbsListInfo.setPage(bbsMapper.searchCount(titleAndContent));
        return bbsListInfo;
    }

    public BbsContentDto bbsContentList(int bbsId){ // 게시판 아이디 상세 조회
        BbsContentDto bbsContentDto =  bbsMapper.findBbsContentById(bbsId);
        bbsContentDto.setReview(reviewMapper.findReviewsByBbsId(bbsId));
        return bbsContentDto;
    }

    public int writeReview(Review review){
        return reviewMapper.writeReview(review);
    }
    // 게시글 삭제
    public Boolean removeBbs(int bbsId, String bbsPw){
        Boolean result = bbsMapper.checkBbsPassword(bbsId, bbsPw);
        if(result){
            bbsMapper.deleteBbs(bbsId);
        }
        return result;
    }
    public Boolean checkBbsPw(int bbsId, String bbsPw){
        return bbsMapper.checkBbsPassword(bbsId, bbsPw);
    }
    // 리뷰 삭제
    public Boolean removeReview(int reviewId, int reviewPw){
        Boolean result = reviewMapper.checkBbsPassword(reviewId, reviewPw);
        if(result){
            bbsMapper.deleteBbs(reviewId);
        }
        return result;
    }

    public int writeBbs(Bbs bbs) {
        return bbsMapper.writeBoard(bbs);
    }

    public int editBbs(Bbs bbs) {
        return bbsMapper.editBoard(bbs);
    }


}
