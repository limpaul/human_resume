package com.example.recruit.mapper;

import com.example.recruit.domain.Career;
import com.example.recruit.domain.Certificate;
import com.example.recruit.domain.Degree;
import com.example.recruit.domain.Enroll;
import com.example.recruit.dto.ResumeDto;
import com.example.recruit.dto.ResumeListDto;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EnrollMapper {
    @Insert("INSERT INTO enroll(enroll_title, enroll_content, hope_job, hope_salary, hope_region, is_new_comer ,is_public , user_no)" +
            "VALUES(#{enroll.enrollTitle}, #{enroll.enrollContent}, #{enroll.hopeJob}, #{enroll.hopeSalary},#{enroll.hopeRegion}, " +
            "#{enroll.isNewComer} ,#{enroll.isPublic}, #{enroll.userNo})")
    @Options(useGeneratedKeys = true, keyProperty="enrollId")
    int createEnroll(@Param("enroll")Enroll enroll);



    @Insert("INSERT INTO degree(enroll_id, degree_type, degree_name, degree_region, degree_start_date, degree_end_date," +
            "degree_major, degree_score, is_graduate)" +
            " VALUES(#{d.enrollId}, #{d.degreeType}, #{d.degreeName}, #{d.degreeRegion}, #{d.degreeStartDate}, " +
            "#{d.degreeEndDate}, #{d.degreeMajor}, #{d.degreeScore}, #{d.isGraduate})")
    int insertDegree(@Param("enrollId") int enrollId, @Param("d")Degree degree);

    @Insert("INSERT into certificate(enroll_id, cert_type, cert_name, cert_date) " +
            "values(#{c.enrollId}, #{c.certType}, #{c.certName}, #{c.certDate})")
    int insertCertificate(@Param("enrollId") int enrollId, @Param("c")Certificate certificate);

    int updateEnroll(@Param("enrollId") int enrollId,@Param("e") Enroll e);


    @Insert("INSERT INTO career(start_date, end_date, company_name, position, " +
            "department, region, salary, task, enroll_id) " +
            "values(#{c.startDate},#{c.endDate},#{c.companyName},#{c.position}, " +
            "#{c.department}, #{c.region}, #{c.salary}, #{c.task}, #{c.enrollId})")
    int insertCareer(@Param("c")Career c);




    @Update("UPDATE enroll set is_public = #{val} where enroll_id = #{enrollId}")
    int resumePublic(@Param("enrollId")int enrollId,@Param("val") boolean val);

    /* mybatis */
    int updateDegree(@Param("d")Degree degree);


    int updateCertificate(@Param("c")Certificate certificate);


    // 사용자 아이디로 이력서 번호 조회
    @Select("select enroll_id from enroll where user_no = #{userMp}")
    int findByEnrollByUserNo(@Param("userNo")int userNo);

    @Select("SELECT * FROM enroll where enroll_id = #{enrollId}")
    Enroll findByEnrollId(@Param("enrollId") int enrollId);

    // 이력서 여러개 작성한거 목록 조회
    @Select("SELECT user_no, enroll_id, enroll_title, hope_job, hope_salary, hope_region, is_new_comer, is_public " +
            "FROM enroll where user_no = #{userNo}")
    List<ResumeListDto> findResumeListByUserId(@Param("userNo")int userNo);

    Boolean updateResumePublic(@Param("enrollId")int enrollId, @Param("isPublic")Boolean isPublic);


    @Select("SELECT * FROM enroll WHERE enroll_id = #{enrollId}")
    Enroll findEnrollByEnrollId(@Param("enrollId")int enrollId);

    @Select("SELECT * FROM degree WHERE enroll_id = #{enrollId}")
    List<Degree> findDegreesByEnrollId(@Param("enrollId") int enrollId);

    @Select("SELECT * FROM certificate WHERE enroll_id = #{enrollId}")
    List<Certificate> findCertsByCertId(@Param("enrollId") int enrollId);

    @Select("SELECT * FROM career WHERE enroll_id = #{enrollId}")
    List<Career> findCareersByCareerId(@Param("enrollId") int enrollId);
}
