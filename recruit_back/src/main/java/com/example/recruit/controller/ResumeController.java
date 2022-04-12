package com.example.recruit.controller;

import com.example.recruit.domain.BasicInfo;
import com.example.recruit.dto.ResumeDto;
import com.example.recruit.dto.ResumeListDto;
import com.example.recruit.dto.UserInfo;
import com.example.recruit.mapper.BasicInfoMapper;
import com.example.recruit.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private BasicInfoMapper basicInfoMapper;



    /* 개인정보 양식 받는 api */
    @PostMapping("/basicinfo/write")
    public int enrollPersonalInfo(@RequestBody BasicInfo basicInfo){
        return resumeService.createEnroll(basicInfo);
    }

    /* basicId로 사용자 개인정보 등록 조회 */
    @PostMapping("/basicinfo/search")
    public BasicInfo findBasicInfoById(@RequestParam("basicId") int basicId){
        return basicInfoMapper.findByBasicInfoId(basicId);
    }

    @PostMapping("/uploadimage")
    public String uploadImage(@RequestParam("upload_file")MultipartFile uploadFile){
        if(uploadFile.isEmpty()){
            return "empty";
        }
        try {
            FileOutputStream fos = new FileOutputStream("C:\\img\\"+uploadFile.getOriginalFilename());
            InputStream is = uploadFile.getInputStream();
            int readCount = 0;
            byte[] buffer = new byte[1024];
            while((readCount = is.read(buffer))!= -1){
                fos.write(buffer,0 ,readCount);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "gg";
    }

    /* 이력서 양식 받는 api */
    @PostMapping("/write")
    public int enRollResume(@RequestBody  ResumeDto resumeDto){
        resumeService.enrollResume(resumeDto); // 이력서 등록
        System.out.println(resumeDto);
        return 1;
    }
    @PostMapping("/list/{userNo}") // 이력서 제목, 급여, 회사 희망정보들
    public List<ResumeListDto> findResumeListByUserId(@PathVariable("userNo") int userNo){
        return resumeService.findResumeListByUserId(userNo);
    }
    @PostMapping("/active")
    public Boolean activeResume(@RequestBody UserInfo userInfo){
        return resumeService.updateResumePublic(userInfo);
    }
    @GetMapping("/edit") // 기존에 등록한 이력서 조회
    public ResumeDto findResume(@RequestParam("userNo") int userNo, @RequestParam("enrollId") int enrollId, @RequestParam(name = "new", required = false)Boolean isNewComer){
        return resumeService.findResumeByUserIdAndEnrollId(userNo, enrollId, isNewComer);
    }
}
