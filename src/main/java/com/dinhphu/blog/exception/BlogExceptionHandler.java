package com.dinhphu.blog.exception;

import com.dinhphu.blog.exception.specific.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BlogExceptionHandler {

    //Not Found
    @ExceptionHandler(value={UserNotFoundException.class,ObjectNotFoundException.class})
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException e){
        return createHttpResponse(HttpStatus.NOT_FOUND,e.getMessage());
    }
    //Bad Request
    @ExceptionHandler(value={UserFieldValidationException.class,EmailExistException.class,UserExistException.class})
    public ResponseEntity<HttpResponse> errorFieldException(Exception e){
        return createHttpResponse(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    public ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus,String message){
        HttpResponse response=new HttpResponse(httpStatus.value(),httpStatus,message,httpStatus.getReasonPhrase().toUpperCase());
        return new ResponseEntity<>(response,httpStatus);
    }
}
