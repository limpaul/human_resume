package com.example.recruit.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Career {
    private int careerId;
    private Date startDate;
    private Date endDate;
    private String companyName;
    private String position;
    private String department;
    private int region;
    private int salary;
    private String task;
    private int enrollId;
}
