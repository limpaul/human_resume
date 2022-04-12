package com.example.recruit.mapper;

import com.example.recruit.domain.Review;
import com.example.recruit.dto.ReviewListDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Insert("INSERT INTO review(review_writer, bbs_id, user_id,review_content, review_pw)" +
            "VALUES(#{review.reviewWriter}, #{review.bbsId}, #{review.userId},#{review.reviewContent}, #{review.reviewPw})")
    int writeReview(@Param("review") Review review);

    @Select("SELECT review_id, review_writer, review_content, review_date FROM review WHERE bbs_id = #{bbs_id}")
    List<ReviewListDto> findReviewsByBbsId(@Param("bbs_id") int bbsId);

    @Select("SELECT review_pw = #{reviewPw} FROM review where review_id = #{reviewId}")
    Boolean checkBbsPassword(int reviewId, int reviewPw);

    @Delete("DELETE FROM review where review_id = #{reviewId}")
    int deleteReview(@Param("reviewId") int reviewId);
}
