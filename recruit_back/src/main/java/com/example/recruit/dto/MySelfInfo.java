package com.example.recruit.dto;

import com.example.recruit.domain.BasicInfo;
import com.example.recruit.domain.Certificate;
import com.example.recruit.domain.Degree;
import com.example.recruit.domain.Enroll;
import lombok.Data;

import java.util.List;

@Data
public class MySelfInfo {
    private Enroll enroll;
    private BasicInfo basicInfo;
    private List<Degree> degree; // N
    private List<Certificate> certificate;// N
}
