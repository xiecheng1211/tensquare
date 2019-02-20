package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Label> labelList = labelService.findAll();
        return new Result(true, StatusCode.Ok,"查询成功",labelList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId){
        Label label = labelService.findById(labelId);
        return new Result(true, StatusCode.Ok,"查询成功",label);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.Ok,"保存成功");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{labelId}")
    public Result update(@PathVariable("labelId") String labelId, @RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.Ok,"修改成功");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{labelId}")
    public Result save(@PathVariable("labelId") String labelId){
        labelService.delete(labelId);
        return new Result(true, StatusCode.Ok,"删除成功");
    }

    /*@RequestMapping(method = RequestMethod.POST, value = "/search")
    public Result findAll(@RequestBody Label label){
        List<Label> labelList = labelService.findSearch(label);
        return new Result(true, StatusCode.Ok,"查询成功",labelList);
    }*/

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public Result findAll(@RequestBody Map map){
        List<Label> labelList = labelService.findSearch(map);
        return new Result(true, StatusCode.Ok,"查询成功",labelList);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label,@PathVariable Integer page,@PathVariable Integer size){
        Page<Label> labelList = labelService.pageQuery(label,page,size);
        return new Result(true, StatusCode.Ok,"查询成功",new PageResult<Label>(labelList.getTotalElements(),labelList.getContent()));
    }

}
