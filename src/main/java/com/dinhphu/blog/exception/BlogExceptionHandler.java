package com.dinhphu.blog.exception;

import com.dinhphu.blog.exception.specific.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BlogExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException e, HttpStatus httpStatus){
        HttpResponse httpResponse=new HttpResponse(httpStatus.NOT_FOUND.value(),httpStatus.NOT_FOUND,e.getMessage(),httpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<HttpResponse>(httpResponse,httpStatus.NOT_FOUND);
    }
}
