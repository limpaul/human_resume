package com.example.recruit.dto;

import com.example.recruit.domain.BasicInfo;
import com.example.recruit.domain.Certificate;
import com.example.recruit.domain.Degree;
import com.example.recruit.domain.Enroll;
import lombok.Data;

@Data
public class UserBasicEnrollDto {
    private Enroll enroll;
    private BasicInfo basicInfo;
    private Degree degree;
    private Certificate certificate;
}
