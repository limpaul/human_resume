package com.example.recruit.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Degree {
    private int degreeId;
    private int degreeType;
    private String degreeName;
    private int degreeRegion;
    private Date degreeStartDate;
    private Date degreeEndDate;
    private String degreeMajor;
    private float degreeScore;
    private int isGraduate;
    private int enrollId;
}
