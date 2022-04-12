package com.example.recruit.mapper;

import com.example.recruit.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(user_id, user_pw, user_email, username) " +
            "VALUES(#{user.userId}, #{user.userPw}, #{user.userEmail}, #{user.username})")
    int join(@Param("user") User user);


}
