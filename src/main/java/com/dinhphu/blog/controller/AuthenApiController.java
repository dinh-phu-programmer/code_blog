package com.dinhphu.blog.controller;

import com.dinhphu.blog.exception.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenApiController {

//    @PutMapping()
//    @PreAuthorize("hasAuthority('user:write')")
//    public ResponseEntity<HttpResponse> deleteUser(){
//        return ResponseEntity.ok(new HttpResponse(HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN,"abc","reason"));
//    }


    @PutMapping()
    @PreAuthorize("hasAnyAuthority('user:write','user:read')")
    public ResponseEntity<String> deleteUser(){
        return ResponseEntity.ok("OKKK");
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_USER')")
    public ResponseEntity<String> updateUser(){
        return ResponseEntity.ok("ROLE ADMIN USER");
    }
}
