package com.example.recruit.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Bbs {
    private int bbsId;
    private int bbsWriterId;// foreign key
    private String bbsWriter;
    private int bbsLike;
    private int bbsRead;
    private String bbsTitle;
    private String bbsContent;
    private String bbsPw;
    private Date bbsDate;
}
