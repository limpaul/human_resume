package com.example.recruit.mapper;

import com.example.recruit.domain.Degree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DegreeMapperTest {
    @Autowired
    private DegreeMapper degreeMapper;

    @Test
    public void degreeTest() throws ParseException {
        Degree degree = new Degree();
        degree.setDegreeName("고려대학교");

        //degree.setIsGraduate("졸업");
        degree.setDegreeScore(4.0f);
        degree.setEnrollId(2);
        int result = degreeMapper.writeDegree(degree);

        assertEquals(1, result);
    }
}