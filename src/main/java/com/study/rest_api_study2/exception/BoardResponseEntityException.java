package com.study.rest_api_study2.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


//특정 범위 기준으로 예외처리 적용 가능.
//@ControllerAdvice(basePackages = {"org.woowa.tmp.pkg"})  --> 해당 패키지 && 하위 패키지내에서 발생되는 예외는 해당 Handler 탐.
//assignableTypes = {SimpleController.class} class 단위로도 부여 가능.
//@ExceptionHandler 특징
// - controller, restcontroller에서만 사용 가능.
// - 메소드 별 정의를 해줘야함.. -> 이를 보완하기 위해서 사용되는 부분이 @advice.
//사용하는 이유에 대해서 생각해보자면 .. 예외처리 응답 양식에 대해서 일정하게 맞추기 위해서 (api service) 사용하는게 아닐까 ?...
//중요한건 특정 범위내의 공통적으로 예외처리 해주는 부분인걸 생각해야함.
@RestControllerAdvice
public class BoardResponseEntityException extends ResponseEntityExceptionHandler {

    //예외처리 class 설정. (controller 마다 해당 예외처리를 해줘야함.)
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> hadleAllExceptions(Exception ex , WebRequest request){
        ResponseException responseException =
                //날짜, MSG, DETAIL(요청 URL) 정보
                new ResponseException(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //커스텀 예외처리에 대해서는 이렇게 하나하나 다 만들어줘야하는걸까??
    @ExceptionHandler(BoardNotFoundException.class)
    public final ResponseEntity<Object> hadleBoardNotFoundExceptions(Exception ex , WebRequest request){
        ResponseException responseException =
                new ResponseException(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
    }

    //ResponseEntity  커스텀해서 사용하는 방법도 있음.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ResponseException responseException =
                //날짜, MSG, DETAIL 정보
                new ResponseException(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);

    }



}
