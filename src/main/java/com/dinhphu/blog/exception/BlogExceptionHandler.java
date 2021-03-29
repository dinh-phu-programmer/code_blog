package com.dinhphu.blog.exception;

import com.dinhphu.blog.exception.specific.ObjectNotFoundException;
import com.dinhphu.blog.exception.specific.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BlogExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException e){
        return createHttpResponse(HttpStatus.NOT_FOUND,e.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<HttpResponse> objNotFoundException(ObjectNotFoundException e){
        return createHttpResponse(HttpStatus.NOT_FOUND,e.getMessage());
    }

    public ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus,String message){
        HttpResponse response=new HttpResponse(httpStatus.value(),httpStatus,message,httpStatus.getReasonPhrase().toUpperCase());
        return new ResponseEntity<>(response,httpStatus);
    }
}
