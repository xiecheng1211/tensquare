package com.tensquare.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;
import util.JwtUtil;

/**
 * @author dongcheng
 * create date 2019/3/11
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class FrendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrendApplication.class,args);
    }

    @Bean
    public JwtUtil setJwtUtil(){
        return new JwtUtil();
    }

    @Bean
    public IdWorker setIdWorker(){
        return new IdWorker();
    }

}
