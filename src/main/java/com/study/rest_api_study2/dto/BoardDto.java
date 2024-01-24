package com.study.rest_api_study2.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private Integer id;
    private String boardTitle;
    private String boardContent;
}
