package com.example.recruit.dto;

import com.example.recruit.domain.Review;
import lombok.Data;

import java.util.List;

@Data
public class BbsContentDto {
    private int bbsId;
    private String bbsTitle;
    private String bbsWriter;
    private String bbsContent;
    private int bbsRead;
    private int bbsLike;
    private List<ReviewListDto> review;
}
