package com.tensquare.user.config;

import com.tensquare.user.handler.JwtIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author dongcheng
 * create date 2019/3/8
 **/
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtIntercept jwtIntercept;
    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtIntercept)
        .addPathPatterns("/**")
        .excludePathPatterns("/**/login/**");
    }


}
