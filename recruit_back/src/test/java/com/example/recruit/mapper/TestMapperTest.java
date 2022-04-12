package com.example.recruit.mapper;

import com.example.recruit.domain.Enroll;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestMapperTest {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private EnrollMapper enrollMapper;
    @Test
    public void test(){
        TestMapper tm = sqlSession.getMapper(TestMapper.class);
        tm.findAll().forEach(System.out::println);
    }


}