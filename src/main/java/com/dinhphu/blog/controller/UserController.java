package com.dinhphu.blog.controller;

import com.dinhphu.blog.exception.specific.EmailExistException;
import com.dinhphu.blog.exception.specific.ObjectNotFoundException;
import com.dinhphu.blog.exception.specific.UserExistException;
import com.dinhphu.blog.exception.specific.UserFieldValidationException;
import com.dinhphu.blog.model.User;
import com.dinhphu.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) throws ObjectNotFoundException {
        User user= this.userService.findById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user, BindingResult theBinding) throws UserExistException, EmailExistException, UserFieldValidationException {
        if (theBinding.hasErrors()){
            List<ObjectError> errors=theBinding.getAllErrors();

            for (ObjectError error : errors) {
                throw new UserFieldValidationException(error.getDefaultMessage());
            }

        }
        User newUser = this.userService.register(user);
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }

}
