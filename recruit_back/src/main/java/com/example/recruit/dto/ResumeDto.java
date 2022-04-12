package com.example.recruit.dto;

import com.example.recruit.domain.Career;
import com.example.recruit.domain.Certificate;
import com.example.recruit.domain.Degree;
import com.example.recruit.domain.Enroll;
import lombok.Data;

import java.util.List;

@Data
public class ResumeDto {
    private Enroll enroll; // 이력서 제목, 희망 직무, 연봉, 근무,
    private List<Degree> degrees; // 학위
    private List<Certificate> certs; // 자격증
    private List<Career> careers; // 경력직이면 정보 받음
    private UserInfo userInfo;
}
