package com.example.recruit.dto;

import lombok.Data;

import java.sql.Date;


@Data
public class BbsListDto {
    private int bbsId;
    private String bbsTitle;
    private String bbsWriter; // foreign key
    private int bbsLike;
    private int bbsRead;
    private Date bbsDate;
}
