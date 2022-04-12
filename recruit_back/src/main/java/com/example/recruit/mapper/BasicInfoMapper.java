package com.example.recruit.mapper;

import com.example.recruit.domain.BasicInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BasicInfoMapper {
    @Insert("INSERT INTO basic_info (img_name, name, gender, birth, email, phone_num, home_num, address, user_no) " +
            "VALUES(#{basic.imgName},#{basic.name},#{basic.gender},#{basic.birth},#{basic.email}," +
            "#{basic.phoneNum}, #{basic.homeNum},#{basic.address}, #{basic.userNo})")
    int writeBasicInfo(@Param("basic")BasicInfo basicInfo);


    @Insert("call basic_iu(#{basic.imgName},#{basic.name},#{basic.gender},#{basic.birth},#{basic.email}," +
            "#{basic.phoneNum}, #{basic.homeNum},#{basic.address}, #{basic.userNo})")
    int tempBasicInfo(@Param("basic")BasicInfo basicInfo);


    @Select("SELECT * FROM basic_info where enroll_id = #{enrollId}")
    BasicInfo findByEnrollId(@Param("enrollId") int enrollId);

    @Select("SELECT img_name, name, gender, birth, email, phone_num, home_num, address " +
            "FROM basic_info where basic_id = #{basicId}")
    BasicInfo findByBasicInfoId(@Param("basicId") int basicId);
}
