package com.example.recruit.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ReviewListDto {
    private int reviewId;
    private String reviewWriter; // primary key
    private String reviewContent;
    private Date reviewDate;
}
