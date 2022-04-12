package com.example.recruit.service;

import com.example.recruit.domain.BasicInfo;
import com.example.recruit.domain.Certificate;
import com.example.recruit.domain.Degree;
import com.example.recruit.dto.MySelfInfo;
import com.example.recruit.dto.UserBasicEnrollDto;
import com.example.recruit.mapper.BasicInfoMapper;
import com.example.recruit.mapper.CertificateMapper;
import com.example.recruit.mapper.DegreeMapper;
import com.example.recruit.mapper.EnrollMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private EnrollMapper enrollMapper;
    @Autowired
    private BasicInfoMapper basicInfoMapper;
    @Autowired
    private DegreeMapper degreeMapper;
    @Autowired
    private CertificateMapper certificateMapper;

    public MySelfInfo mySelfInfoCheck(int num){
        MySelfInfo mySelfInfo = new MySelfInfo();
        mySelfInfo.setEnroll(enrollMapper.findByEnrollId(num));
        mySelfInfo.setBasicInfo(basicInfoMapper.findByEnrollId(num));
        mySelfInfo.setDegree(degreeMapper.findByDegreeId(num));
        mySelfInfo.setCertificate(certificateMapper.findByEnrollId(num));
        return mySelfInfo;
    }

}
