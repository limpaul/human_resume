package com.example.recruit.mapper;

import com.example.recruit.domain.BasicInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BasicInfoMapperTest {


    @Autowired
    private BasicInfoMapper basicInfoMapper;
    @Test
    public void test(){
        Date date = new Date(new GregorianCalendar().getTimeInMillis());
        System.out.println(date);
    }

    @Test
    public void findImage(){
        String path = "C:\\img";
        File dir = new File(path);
        File[] files = dir.listFiles();
        for(File file : files){
            System.out.println(file.getName());
        }
    }
    @Test
    public void writeBasicInfo() throws ParseException {
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setImgName("/home/user1/img");
        basicInfo.setGender('m');
        basicInfo.setBirth(Date.valueOf("2022-04-07"));
        basicInfo.setEmail("ibw1953@naver.com");
        basicInfo.setPhoneNum("010-1234-1234");
        basicInfo.setAddress("seoul gurogu apart");
        int result = basicInfoMapper.writeBasicInfo(basicInfo);
        assertEquals(1, result);
    }
    @Test
    public void tempWriteBasicInfo(){
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setImgName("test.png");
        basicInfo.setGender('m');
        basicInfo.setName("tester");
        basicInfo.setBirth(Date.valueOf("2022-04-07"));
        basicInfo.setEmail("ibw1953@naver.com");
        basicInfo.setPhoneNum("010-1234-1234");
        basicInfo.setAddress("seoul gurogu apart");
        basicInfo.setHomeNum("USA NewYork");
        basicInfo.setUserNo(13);
        int result = basicInfoMapper.tempBasicInfo(basicInfo);
        assertEquals(1, result);
    }
    @Test
    public void findByBasicInfoId(){
        System.out.println(basicInfoMapper.findByBasicInfoId(2));
    }
    @Test
    public void basicInfoSearchById(){ // basicInfoId 조회
        BasicInfo basicInfo = basicInfoMapper.findByBasicInfoId(2);
        System.out.println(basicInfo);
    }

}