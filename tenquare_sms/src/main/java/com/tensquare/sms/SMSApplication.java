package com.tensquare.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author dongcheng
 * create date 2019/3/7
 **/
@SpringBootApplication
public class SMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(SMSApplication.class,args);
    }

    @Bean
    public IdWorker setIdWorker(){
        return new IdWorker();
    }


}
