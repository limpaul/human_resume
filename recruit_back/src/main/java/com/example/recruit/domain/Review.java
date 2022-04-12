package com.example.recruit.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Review {
    private int reviewId;
    private int userId;
    private String reviewWriter; // primary key
    private int bbsId; // foreign key
    private String reviewContent;
    private String reviewPw;
    private Date reviewDate;
}
