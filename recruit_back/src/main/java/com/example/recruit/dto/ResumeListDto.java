package com.example.recruit.dto;

import lombok.Data;

@Data
public class ResumeListDto {
    private int userNo;
    private int enrollId;
    private String enrollTitle;
    private int hopeJob;
    private int hopeSalary;
    private int hopeRegion;
    private Boolean isNewComer;
    private Boolean isPublic;
}
