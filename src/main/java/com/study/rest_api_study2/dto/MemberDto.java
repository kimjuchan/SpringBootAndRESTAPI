package com.study.rest_api_study2.dto;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private Integer id;
    private String email;
    private String userName;
    private int age;
    private String phoneNumber;
    private String marriedYn;

}
