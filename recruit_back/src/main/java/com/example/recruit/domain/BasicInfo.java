package com.example.recruit.domain;

import lombok.Data;

import java.sql.Date;


@Data
public class BasicInfo {
    private int basicId;
    private String name;
    private Date birth;
    private String email;
    private String phoneNum;
    private String homeNum;
    private String address;
    private char gender;
    private String imgName;
    private int userNo;
}
