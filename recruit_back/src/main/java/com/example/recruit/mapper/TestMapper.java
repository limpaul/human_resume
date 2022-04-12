package com.example.recruit.mapper;

import com.example.recruit.domain.BasicInfo;
import com.example.recruit.domain.Enroll;
import com.example.recruit.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestMapper {
    List<User> findAll();
    int updateEnroll(@Param("e") Enroll enroll);
}
