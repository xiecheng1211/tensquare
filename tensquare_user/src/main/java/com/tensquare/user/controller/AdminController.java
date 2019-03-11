package com.tensquare.user.controller;

import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

/**
 * @author dongcheng
 * create date 2019/3/7
 **/
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Admin admin){
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        return new Result(true, StatusCode.Ok,"注册成功");
    }

    @RequestMapping(method = RequestMethod.POST,value = "/login")
    public Result login(@RequestBody Admin admin){

        admin = adminService.login(admin);
        if (admin == null){
            return new Result(true,StatusCode.LOGINERROR,"失败");
        }
        //设置token
        String token = jwtUtil.createJWT(admin.getId(),admin.getLoginname(),"admin");
        return new Result(true,StatusCode.Ok,"登录成功",token);
    }
}
