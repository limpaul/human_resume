package com.example.recruit.dto;

import lombok.Data;

@Data
public class UserInfo {
    private int userNo; // 사용자 아이디 번호
    private Integer basicId; // 이력서 아이디 번호
    private Integer enrollId; // 사용자 이력서 아이디
    private Boolean isPublic; // 이력서를 공개
    private Boolean isCreated; // 기존이력서를 갖다 썻는지
}
