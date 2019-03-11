package com.tensquare.common;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author dongcheng
 * create date 2019/3/8
 **/
public class CreateToken {

    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder().setId("123456")  //设置id
                .setSubject("black")        //设置请求对象  用户名称
                .setIssuedAt(new Date())        //设置登录时间
                .signWith(SignatureAlgorithm.HS256,"goodjob")   //设置登录头信息
                .setExpiration(new Date(new Date().getTime() + 60000)) //设置过期时间
                .claim("rol","admin");
        System.out.println(jwtBuilder.compact());

    }

}
