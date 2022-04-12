package com.example.recruit.mapper;

import com.example.recruit.domain.Certificate;
import com.example.recruit.domain.Degree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CertificateMapperTest {
    @Autowired
    private CertificateMapper certificateMapper;
    @Test
    public void degreeTest() throws ParseException {
        Certificate certificate = new Certificate();
        certificate.setCertName("전기기사");
        //certificate.setCertDate(new SimpleDateFormat("YYYY.MM").parse("2014.02"));
        certificate.setEnrollId(2);
        int result = certificateMapper.addCertificate(certificate);

        assertEquals(1, result);
    }
}