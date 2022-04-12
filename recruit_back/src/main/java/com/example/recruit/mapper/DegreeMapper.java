package com.example.recruit.mapper;

import com.example.recruit.domain.Degree;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DegreeMapper {
    @Insert("INSERT INTO degree(degree_name, degree_start_date, degree_end_date, is_graduate, degree_score, enroll_id)" +
            "VALUES(#{degree.degreeName},#{degree.degreeStartDate}, #{degree.degreeEndDate}, " +
            "#{degree.isGraduate}, #{degree.degreeScore}, #{degree.enrollId})")
    int writeDegree(@Param("degree")Degree degree);


    @Select("SELECT * FROM degree where enroll_id = #{enroll_id}")
    List<Degree> findByDegreeId(@Param("enroll_id") int enrollId);
}
