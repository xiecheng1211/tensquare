package com.tensquare.gather.controller;

import com.tensquare.gather.pojo.Gather;
import com.tensquare.gather.service.GatherService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

/**
 * @author dongcheng
 * create date 2019/2/26
 **/
@RestController
@CrossOrigin
@RequestMapping("/gather")
public class GatherController {
    @Autowired
    private GatherService gatherService;
    @Autowired
    private IdWorker idWorker;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.Ok, "查询成功", gatherService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.Ok, "查询成功", gatherService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Gather gather){
        gather.setId(idWorker.nextId()+"");
        return new Result(true, StatusCode.Ok, "保存成功", gatherService.save(gather));
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public Result update(@PathVariable String id,@RequestBody Gather gather){
        gather.setId(id);
        return new Result(true, StatusCode.Ok, "修改成功", gatherService.save(gather));
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public Result delete(@PathVariable String id){
        gatherService.delete(id);
        return new Result(true, StatusCode.Ok, "删除成功");
    }

}
