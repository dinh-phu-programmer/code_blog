package com.dinhphu.blog.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtSecretKey {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtSecretKey(JwtConfig jwtConfig){
        this.jwtConfig=jwtConfig;
    }

    @Bean
    public SecretKey secretKey(){
        return Keys.hmacShaKeyFor(this.jwtConfig.getSecretKey().getBytes());
    }

}
