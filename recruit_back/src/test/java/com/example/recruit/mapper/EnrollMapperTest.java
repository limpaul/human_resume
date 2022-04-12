package com.example.recruit.mapper;

import com.example.recruit.domain.Career;
import com.example.recruit.domain.Certificate;
import com.example.recruit.domain.Degree;
import com.example.recruit.domain.Enroll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EnrollMapperTest {
    @Autowired
    private EnrollMapper enrollMapper;

    @Test
    @Transactional
    public void writeEnrollTest(){
        Enroll enroll = new Enroll();
       /* enroll.setEnrollTitle("user2 write title");
        enroll.setEnrollContent("user2 write content");*/
        enroll.setUserNo(12);
        //int result = enrollMapper.writeEnroll(enroll);
        //assertEquals(1, result);
    }

    @Test
    @Transactional
    public void createEnrollTest(){
        Enroll enroll = new Enroll();
        enroll.setEnrollTitle("test");
        enroll.setEnrollContent("test write content");
        enroll.setHopeJob(1);
        enroll.setHopeSalary(3);
        enroll.setHopeRegion(1);
        enroll.setIsNewComer(false);
        enroll.setIsPublic(true);
        enroll.setUserNo(12);
        int result = enrollMapper.createEnroll(enroll);
        System.out.println(enroll);
        assertEquals(1, result);
    }

    @Test
    @Transactional
    public void insertDegreeTest(){
        List<Degree> degreeList = new ArrayList<>();
        Degree degree1 = new Degree();
        degree1.setDegreeType(4);
        degree1.setDegreeName("고려대");
        degree1.setDegreeRegion(1);
        degree1.setDegreeScore(2.85f);
        degree1.setIsGraduate(1);
        degree1.setEnrollId(2);

        Degree degree2 = new Degree();
        degree2.setDegreeType(3);
        degree2.setDegreeName("동양미래대학교");
        degree2.setDegreeRegion(1);
        degree2.setDegreeScore(3.5f);
        degree2.setEnrollId(2);
        degree2.setIsGraduate(1);
        degreeList.add(degree1);
        degreeList.add(degree2);

        for(Degree degree : degreeList){
            int reuslt = enrollMapper.insertDegree(2, degree);
            assertEquals(1, reuslt);
        }
    }
    @Test
    @Transactional
    public void insertCertificateTest(){
        List<Certificate> certificates = new ArrayList<>();

        Certificate certificate1 = new Certificate();
        certificate1.setEnrollId(2);
        certificate1.setCertType("자격증");
        certificate1.setCertName("정보처리기사");
        certificate1.setCertDate(Date.valueOf("2021-12-21"));

        Certificate certificate2 = new Certificate();
        certificate2.setEnrollId(2);
        certificate2.setCertType("자격증");
        certificate2.setCertName("전기기사");
        certificate2.setCertDate(Date.valueOf("2022-03-02"));

        certificates.add(certificate1);
        certificates.add(certificate2);

        for(Certificate certificate : certificates){
            int result = enrollMapper.insertCertificate(2, certificate);
            assertEquals(certificates.size(), result);
        }
    }

    @Test
    @Transactional
    public void noPublic(){
        int result = enrollMapper.resumePublic(2, false);
        assertEquals(1, result);
    }
    @Test
    @Transactional
    public void updateDegreeTest(){
        List<Degree> degreeList = new ArrayList<>();
        Degree degree1 = new Degree();
        degree1.setDegreeType(4);
        degree1.setDegreeName("고려대학교");
        degree1.setDegreeRegion(1);
        degree1.setDegreeScore(2.85f);
        degree1.setIsGraduate(1);
        degree1.setEnrollId(2);
        degree1.setDegreeId(3);

        Degree degree2 = new Degree();
        degree2.setDegreeType(3);
        degree2.setDegreeName("동양공전");
        degree2.setDegreeRegion(1);
        degree2.setDegreeScore(3.5f);
        degree2.setEnrollId(2);
        degree2.setIsGraduate(1);
        degree1.setEnrollId(4);

        degreeList.add(degree1);
        degreeList.add(degree2);

        for(Degree degree : degreeList){
            int reuslt = enrollMapper.updateDegree(degree);
            assertEquals(1, reuslt);
        }
    }
    @Test
    @Transactional
    public void updateCertificateTest(){
        List<Certificate> certificates = new ArrayList<>();
        Certificate c1 = new Certificate();
        c1.setCertType("자격증");
        c1.setCertName("정처기");
        c1.setCertDate(Date.valueOf("2020-09-14"));

        Certificate c2 = new Certificate();
        c2.setCertType("자격증");
        c2.setCertName("전기기사");
        c2.setCertDate(Date.valueOf("2002-03-12"));

        c1.setEnrollId(3);
        c2.setEnrollId(4);

        certificates.add(c1);
        certificates.add(c2);

        certificates.forEach(certificate -> {
            enrollMapper.updateCertificate(certificate);
        });

    }

    @Test
    @Transactional
    public void updateEnrollTest(){
        Enroll enroll = new Enroll();
        enroll.setEnrollTitle("aa");
        enroll.setEnrollContent(null);
        System.out.println(enroll);
        enrollMapper.updateEnroll(2, enroll);
    }

    @Test // 사용자 본인 자소서 하나만 확인
    public void showUserEnrollInfo(){
        Enroll enrollResult = enrollMapper.findByEnrollId(2);
    }

    @Transactional
    @Test
    public void insertCareerTest(){
        List<Career> careers = new ArrayList<>();
        Career career1 = new Career();
        career1.setStartDate(Date.valueOf("2011-03-02"));
        career1.setEndDate(Date.valueOf("2022-03-01"));
        career1.setCompanyName("apple");
        career1.setPosition("과장");
        career1.setDepartment("영업");
        career1.setRegion(1);
        career1.setSalary(3);
        career1.setTask("연구");
        career1.setEnrollId(2);

        Career career2 = new Career();
        career2.setStartDate(Date.valueOf("2011-03-02"));
        career2.setEndDate(Date.valueOf("2022-03-01"));
        career2.setCompanyName("apple");
        career2.setPosition("과장");
        career2.setDepartment("영업");
        career2.setRegion(1);
        career2.setSalary(5);
        career2.setTask("연구");
        career2.setEnrollId(2);

        careers.add(career1);
        careers.add(career2);

        careers.forEach(career -> {
            int reuslt = enrollMapper.insertCareer(career);
            assertEquals(1, reuslt);
        });
    }
    @Test
    public void findResumeListByUserIdTest(){
        enrollMapper.findResumeListByUserId(13).forEach(System.out::println);
    }
    @Test // 이력서 공개 여부 설정
    public void updateResumePublicTest(){
        enrollMapper.updateResumePublic(19, false);
    }
    @Test
    public void selectAllTest(){
        enrollMapper.findDegreesByEnrollId(19).forEach(System.out::println);
        enrollMapper.findCertsByCertId(19).forEach(System.out::println);
        enrollMapper.findCareersByCareerId(20).forEach(System.out::println);
    }
    @Test
    public void enrollTest(){
        System.out.println(enrollMapper.findEnrollByEnrollId(19));
    }
}