package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dongcheng
 * create date 2019/3/12
 **/
@Component
public class WebFilter extends ZuulFilter {

    /**
     * pre ：可以在请求被路由之前调用
     * route ：在路由请求时候被调用
     * post ：在route和error过滤器之后被调用
     * error ：处理请求时发生错误时被调用
     */
    @Override
    public String filterType() {
        return "pre";    //前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0;   //过滤级别  优先执行  最大为0
    }

    @Override
    public boolean shouldFilter() {
        return true;       //是否执行该过滤器
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("正在执行过滤器内部内容");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();

        String header = httpServletRequest.getHeader("Auth");
        if(header != null && !"".equals(header)){
            requestContext.addZuulRequestHeader("Auth",header);
        }
        return null;
    }
}
