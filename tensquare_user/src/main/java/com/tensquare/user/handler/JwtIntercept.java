package com.tensquare.user.handler;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dongcheng
 * create date 2019/3/8
 **/
@Component
public class JwtIntercept implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String auth = request.getHeader("Authorization");

        if(auth !=null && auth.startsWith("Bearer ")){
            String adstr = auth.substring(7);
            if(!"".equals(adstr)){
                try {
                    Claims claims = jwtUtil.parseJWT(adstr);
                    if ("admin".equals(claims.get("rols"))){

                        request.setAttribute("claims_admin",adstr);

                    }else if("user".equals(claims.get("rols"))){

                        request.setAttribute("claims_user",adstr);

                    }else {
                        throw new Exception("令牌请求错误");
                    }
                }catch (Exception e){
                    throw new Exception("令牌请求错误");
                }
            }else {
                throw new Exception("令牌请求错误");
            }
        }
        return true;
    }

}
