package com.tensquare.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

/**
 * @author dongcheng
 * create date 2019/3/8
 **/
public class ParseToken {

    public static void main(String[] args) {

        Claims claims = Jwts.parser()
                .setSigningKey("goodjob")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NTYiLCJzdWIiOiJibGFjayIsImlhdCI6MTU1MjAyNTA1MywiZXhwIjoxNTUyMDI1MTEzLCJyb2wiOiJhZG1pbiJ9.p9XYhi0WSQjrWjB_j5mNGnmOEyc0jbp6ysHWPXqAAsk")
                .getBody();

        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println(claims.get("rol"));
    }

}
