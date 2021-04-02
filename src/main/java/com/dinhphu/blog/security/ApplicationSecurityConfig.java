package com.dinhphu.blog.security;

import com.dinhphu.blog.filter.JwtAccessDeniedHandler;
import com.dinhphu.blog.filter.JwtAuthenticationEntryPoint;
import com.dinhphu.blog.jwt.JwtConfig;
import com.dinhphu.blog.jwt.JwtTokenVerifier;
import com.dinhphu.blog.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.dinhphu.blog.services.UserService;
import com.dinhphu.blog.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

import static com.dinhphu.blog.constant.SecurityConstant.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public ApplicationSecurityConfig(@Qualifier("userService") UserDetailsService userDetailsService,
                                     PasswordEncoder passwordEncoder,
                                     JwtConfig jwtConfig,
                                     SecretKey secretKey,
                                     JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                                     JwtAccessDeniedHandler jwtAccessDeniedHandler
                                     ) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.jwtAuthenticationEntryPoint=jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler=jwtAccessDeniedHandler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), this.jwtConfig, this.secretKey))
                .addFilterAfter(new JwtTokenVerifier(this.jwtConfig, this.secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(this.jwtAccessDeniedHandler)
                .authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(this.passwordEncoder);
        provider.setUserDetailsService(this.userDetailsService);
        return provider;
    }
}
