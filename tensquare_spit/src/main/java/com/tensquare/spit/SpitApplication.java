package com.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author dongcheng
 * create date 2019/2/27
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class,args);
    }

    @Bean
    public IdWorker setIdWordk(){
        return new IdWorker();
    }


}
