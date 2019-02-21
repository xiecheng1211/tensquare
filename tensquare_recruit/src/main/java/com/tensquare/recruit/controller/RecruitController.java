package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author dongcheng
 * create date 2019/2/21
 **/
@RestController
@CrossOrigin
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;
    @Autowired
    private IdWorker idWorker;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.Ok, "请求成功", recruitService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public Result findAll(@RequestBody Recruit recruit){
        return new Result(true, StatusCode.Ok, "请求成功", recruitService.findSearch(recruit));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search/{page}/{size}")
    public PageResult findAll(@RequestBody Recruit recruit, @PathVariable Integer page, @PathVariable Integer size){
        Page pagedate = recruitService.findSearch(recruit,page,size);
        return new PageResult(pagedate.getTotalElements(), pagedate.getContent());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Recruit recruit){
        recruit.setId(idWorker.nextId()+"");
        return new Result(true, StatusCode.Ok, "保存成功", recruitService.save(recruit));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Result update(@PathVariable String id, @RequestBody Recruit recruit){
        recruit.setId(id);
        return new Result(true, StatusCode.Ok, "更新成功", recruitService.save(recruit));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Result delete(String id){
        recruitService.delete(id);
        return new Result(true, StatusCode.Ok, "删除成功");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{state}")
    public Result findByState(@PathVariable String state){
        return new Result(true, StatusCode.Ok, "请求成功", recruitService.findByState(state));
    }

    @RequestMapping(method = RequestMethod.GET,value = "/search/type/{typeid}")
    public Result findByType(@PathVariable String typeid){
        return new Result(true, StatusCode.Ok, "请求成功", recruitService.findByType(typeid));
    }



}
