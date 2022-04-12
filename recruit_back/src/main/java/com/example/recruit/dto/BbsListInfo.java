package com.example.recruit.dto;

import lombok.Data;

import java.util.List;

@Data
public class BbsListInfo {
    private List<BbsListDto> bbsListDto;
    private int page;
}
