package com.example.recruit.domain;

import lombok.Data;

import java.sql.Date;


@Data
public class Certificate {
    private int certId;
    private Date certDate;
    private String certName;
    private String certType;
    private int enrollId;
}
