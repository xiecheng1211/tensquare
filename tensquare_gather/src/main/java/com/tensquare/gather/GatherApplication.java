package com.tensquare.gather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author dongcheng
 * create date 2019/2/26
 **/
@SpringBootApplication
@EnableCaching
public class GatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatherApplication.class,args);
    }

    @Bean
    public IdWorker setIdWork(){
        return new IdWorker();
    }

}
