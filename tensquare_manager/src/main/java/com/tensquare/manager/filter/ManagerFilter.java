package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;

/**
 * @author dongcheng
 * create date 2019/3/12
 **/
@Component
public class ManagerFilter extends ZuulFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("manager路由拦截器执行成功");

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();

        if (httpServletRequest.getMethod().equals("OPTIONS")){
            return null;
        }

        if (httpServletRequest.getRequestURI().indexOf("login") > 0){
            System.out.println("进入登录功能");
            return null;
        }

        String auth = (String) httpServletRequest.getHeader("Authorization");
        //进行权限教研
        if (auth!=null && auth.startsWith("Beare ")){
            String token = auth.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            //是管理員
            if (claims!=null && "admin".equals(claims.get("roles"))){
                requestContext.addZuulRequestHeader("Authorization",auth);
                System.out.println("头部信息验证通过");
                return null;
            }
        }
        //终止运行
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(401);
        requestContext.setResponseBody("无权限访问");
        return null;
    }
}
