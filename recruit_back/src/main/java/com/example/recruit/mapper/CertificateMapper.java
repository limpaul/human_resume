package com.example.recruit.mapper;

import com.example.recruit.domain.Certificate;
import com.example.recruit.domain.Degree;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CertificateMapper {
    @Insert("INSERT INTO certificate(cert_name, cert_date, enroll_id)" +
            "VALUES(#{cert.certName},#{cert.certDate},#{cert.enrollId})")
    int addCertificate(@Param("cert") Certificate cert);

    @Select("SELECT * FROM certificate where enroll_id = #{enrollId}")
    List<Certificate> findByEnrollId(@Param("enrollId") int enrollId);
}
