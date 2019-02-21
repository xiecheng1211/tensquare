package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.List;

/**
 * @author dongcheng
 * create date 2019/2/21
 **/
@RestController
@CrossOrigin
@RequestMapping(value = "/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private IdWorker idWorker;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Enterprise> enterpriseList = enterpriseService.findAll();
        return new Result(true, StatusCode.Ok,"请求成功",enterpriseList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Enterprise enterprise){
        enterprise.setId(idWorker.nextId()+"");
        enterprise = enterpriseService.save(enterprise);
        return new Result(true, StatusCode.Ok,"保存成功",enterprise);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public Result update(@PathVariable(name = "id") String id,@RequestBody Enterprise enterprise){
        enterprise.setId(id);
        enterprise = enterpriseService.save(enterprise);
        return new Result(true, StatusCode.Ok,"修改成功",enterprise);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Result delete(@PathVariable String id){
        enterpriseService.delete(id);
        return new Result(true, StatusCode.Ok,"删除成功");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/search/hotlist")
    public Result findIshot(){
        List<Enterprise> enterpriseList = enterpriseService.findByIshot();
        return new Result(true, StatusCode.Ok,"请求成功",enterpriseList);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public Result findSearch(@RequestBody Enterprise enterprise){
        List<Enterprise> enterpriseList = enterpriseService.findSearch(enterprise);
        return new Result(true, StatusCode.Ok,"请求成功",enterpriseList);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search/{page}/{size}")
    public PageResult findSearch(@RequestBody Enterprise enterprise,@PathVariable Integer page,@PathVariable Integer size){
        Page page1 = enterpriseService.findSearch(enterprise, page, size);
        return new PageResult<Enterprise>(page1.getTotalElements(),page1.getContent());
    }

}
