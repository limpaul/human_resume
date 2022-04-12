package com.example.recruit.domain;

import lombok.Data;

@Data
public class Enroll {
    private int enrollId;
    private String enrollTitle;
    private String enrollContent;
    private int hopeJob; // 희망 직종
    private int hopeSalary; // 희망급여
    private int hopeRegion; // 희망근무지역
    private Boolean isNewComer; // 신입인지 경력인지
    public Boolean isPublic;
    private int userNo;
}
