package com.tensquare.user.controller;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dongcheng
 * create date 2019/3/5
 **/
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.Ok,"查询成功",userService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sendms/{mobile}")
    public Result sendCheckCode(@PathVariable String mobile){
        String code = userService.sendCheckCode(mobile);
        if (code == null){
            return new Result(true, StatusCode.Ok,"请求成功","验证码为:"+code);
        }
        return new Result(true, StatusCode.Ok,"已发送验证码，请稍后操作","验证码为:"+code);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result register(String mobile,String code) throws Exception{
        userService.register(mobile,code);
        return new Result(true, StatusCode.Ok,"注册成功");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/login")
    public Result login(@RequestBody User user){
        user = userService.login(user.getMobile(),user.getPassword());
        if (user == null){
            return new Result(true,StatusCode.LOGINERROR,"失败");
        }
        return new Result(true,StatusCode.Ok,"登录成功");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Result delete(@PathVariable String id){
        String auth = (String) httpServletRequest.getAttribute("auth");
        if(auth !=null && !"".equals(auth)){
             userService.delete(id);
             return new Result(true,StatusCode.Ok,"删除成功");
        }
        return new Result(true,StatusCode.ACCESSERROR,"权限不足");
    }

    @RequestMapping(value = "/{userid}/{friendid}/{x}",method = RequestMethod.PUT)
    public void updatefanscountandfllowcount(@PathVariable String userid,@PathVariable String friendid, int x){
        userService.updatefanscountandfollowcount(userid,friendid,x);
    }

}
