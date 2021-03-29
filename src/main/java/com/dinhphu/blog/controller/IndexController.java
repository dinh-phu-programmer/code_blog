package com.dinhphu.blog.controller;

import com.dinhphu.blog.services.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private TranslationService translationService;

    @GetMapping("/translation")
    public ResponseEntity<String> getTranslation(){
        String translation = translationService.getTranslation();
        return new ResponseEntity<>(translation, HttpStatus.OK);
    }

}
