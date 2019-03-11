package com.tensquare.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import util.IdWorker;
import util.JwtUtil;

/**
 * @author dongcheng
 * create date 2019/2/27
 **/
@SpringBootApplication
@CrossOrigin
public class UserApplication {

    public static void main(String[] args){
        SpringApplication.run(UserApplication.class,args);
    }

    @Bean
    public IdWorker setIdworlk(){
        return new IdWorker();
    }

    @Bean
    public JwtUtil setJwtUtil(){
        return new JwtUtil();
    }

    @Bean
    public BCryptPasswordEncoder setBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
