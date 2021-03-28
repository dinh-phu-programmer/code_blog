package com.dinhphu.blog.controller;

import com.dinhphu.blog.model.User;
import com.dinhphu.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/management/")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<User>> findAllUser(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam Optional<String> sortBy
    ){

        Page<User> users= this.userService.findAllWithPage(page,size,sortBy);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
