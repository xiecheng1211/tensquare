package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author dongcheng
 * create date 2019/3/7
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //authorizeRequests所有security全注解配置实现开端，表示开始说明需要权限
        //需要的权限分为两个部分，第一部分是拦截路劲。第二部分是方位该路径需要的权限
        //antMathers表示拦截什么路径,permitAll任何权限都可以访问，直接放行所有
        //anyRequest()任何请求,authenticated认证后才能访问
        //.and().csrf().disable();固定写法,表示使csrf拦截失效
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

}
