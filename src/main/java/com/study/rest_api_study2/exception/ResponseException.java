package com.study.rest_api_study2.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseException {
    //예외처리 발생시간
    private Date errorDate;
    private String message;
    //예외처리된 url 정보.
    private String details;


}
