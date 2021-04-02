package com.dinhphu.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenApiController {

    @GetMapping()
    public ResponseEntity<String> getMapping(){
        return ResponseEntity.ok("Hello");
    }

}
