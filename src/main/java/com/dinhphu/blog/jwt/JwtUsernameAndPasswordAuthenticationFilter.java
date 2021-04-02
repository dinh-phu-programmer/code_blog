package com.dinhphu.blog.jwt;

import com.dinhphu.blog.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import static com.dinhphu.blog.constant.ExceptionMessageConstant.*;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private  AuthenticationManager authenticationManager;
    private JwtConfig jwtConfig;
    private SecretKey secretKey;
    @Autowired
    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig =jwtConfig;
        this.secretKey=secretKey;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            User user= new ObjectMapper().readValue(request.getInputStream(),User.class);
            Authentication authentication= new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
            Authentication authenticate = this.authenticationManager.authenticate(authentication);
            return authenticate;
        }catch (IOException e){
            throw new UsernameNotFoundException(USER_NOT_FOUND_BY_ID);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities",authResult.getAuthorities())
                .setIssuedAt(Date.valueOf(LocalDate.now().plusDays(this.jwtConfig.getTokenExpirationAfterDays())))
                .signWith(this.secretKey)
                .compact();

        response.addHeader(this.jwtConfig.getAuthorizationHeaders(),this.jwtConfig.getTokenPrefix()+token);
    }
}
