package com.example.recruit.service;

import com.example.recruit.domain.*;
import com.example.recruit.dto.ResumeDto;
import com.example.recruit.dto.ResumeListDto;
import com.example.recruit.dto.UserInfo;
import com.example.recruit.mapper.BasicInfoMapper;
import com.example.recruit.mapper.EnrollMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
public class ResumeService {
    @Autowired
    private EnrollMapper enrollMapper;

    @Autowired
    private BasicInfoMapper basicInfoMapper;

    // 기존 이력서를 수정하였을 경우

    // 새로운 이력서를 등록하였을 경우
    @Transactional
    public int enrollResume(ResumeDto resumeDto){
        Enroll enroll =  resumeDto.getEnroll(); // 이력서 기본 정보
        List<Degree> degrees = resumeDto.getDegrees(); // 사용자 학위 정보 N개
        List<Certificate> certificates = resumeDto.getCerts(); // 사용자 자격증 정보들
        List<Career> careers = resumeDto.getCareers();
        UserInfo userInfo = resumeDto.getUserInfo();


        // 이력서 정보를 먼저 등록한다. (이력서 제목, 내용)
        enrollMapper.createEnroll(enroll);


        // 기존에 이력서를 수정한건지 확인한다. !!!!!! TODO: 기존이력서를 수정한건지 확인 해야함

        // 새로운 이력서 등록한 번호를 조회 해온다.
        int enrollId = enroll.getEnrollId();

        // 신입인지 경력인지 확인
        Boolean isNewComer = enroll.getIsNewComer();
        // 트랜잭션 한번동안 여러번 sql문 수행 ( 학위, 자격증 등록)
         enrollSubResume(enrollId, degrees, certificates, careers, isNewComer);
        return 0;
    }

    /* 새로운 이력서에 학위, 자격증, 경력 추가. */
    /* 한번의 트랜잭션으로 여러개의 학위정보와, 자격증 정보를 insert 시킨다 */
    /* 이력서 등록을 따로 돌리는 이유는 새로운 이력서를 만들어야 나머지 학위, 자격증, 경력이 외래키를 갖을 수 있기 때문 */
    @Transactional(rollbackForClassName = {"NullPointerException"})
    public void enrollSubResume(int enrollId, List<Degree> degrees, List<Certificate> certificates, List<Career> careers, Boolean isNewComer){
        degrees.forEach(degree -> {
            degree.setEnrollId(enrollId);
            enrollMapper.insertDegree(enrollId, degree);
        });

        certificates.forEach(certificate -> {
            certificate.setEnrollId(enrollId);
            enrollMapper.insertCertificate(enrollId, certificate);
        });

        if(isNewComer == false){ // 신입이 아닐경우 경력을 추가해준다.
            careers.forEach(career -> {
                career.setEnrollId(enrollId);
                enrollMapper.insertCareer(career);
            });
        }
    }
    /* 개인정보와 이미지를 등록하는 기능 */
    public int createEnroll(BasicInfo basicInfo) {
        // 이미지 등록
        basicInfo.setImgName(findImage(basicInfo.getImgName()));
        return basicInfoMapper.writeBasicInfo(basicInfo);
    }

    private String findImage(String fileName){
        String path = "C:\\img";
        File dir = new File(path);
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.getName().equals(fileName)){
                return fileName;
            }
        }
        return "test.png";
    }

    public List<ResumeListDto> findResumeListByUserId(int userId){
        return enrollMapper.findResumeListByUserId(userId);
    }
    public Boolean updateResumePublic(UserInfo userInfo){
        //int userId, int enrollId, Boolean isPublic
        int userId = userInfo.getUserNo();
        int enrollId = userInfo.getEnrollId();
        Boolean isPublic = userInfo.getIsPublic();
        return enrollMapper.updateResumePublic(enrollId, isPublic);
    }

    @Transactional
    public ResumeDto findResumeByUserIdAndEnrollId(int userId, int enrollId, Boolean isNewComer) {
        ResumeDto resumeDto = new ResumeDto();
        Enroll enroll = enrollMapper.findEnrollByEnrollId(enrollId);
        List<Degree> degrees = enrollMapper.findDegreesByEnrollId(enrollId);
        List<Certificate> certs = enrollMapper.findCertsByCertId(enrollId);
        List<Career> careers = null;
        if(!isNewComer){
            careers = enrollMapper.findCareersByCareerId(enrollId); // 신입이면 경력 조회 할 필요없음
            resumeDto.setCareers(careers);
        }
        resumeDto.setEnroll(enroll);
        resumeDto.setDegrees(degrees);
        resumeDto.setCerts(certs);

        return resumeDto;
    }

}
