package com.tensquare.qa.controller;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

/**
 * @author dongcheng
 * create date 2019/2/24
 **/
@RestController
@CrossOrigin
@RequestMapping(value = "problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private IdWorker idWorker;

    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}",method = RequestMethod.GET)
    public Result newList(@PathVariable String labelid, @PathVariable Integer page, @PathVariable Integer size){
        return new Result(true, StatusCode.Ok,"请求成功",new PageResult<>(problemService.newList(labelid,page,size).size(),problemService.newList(labelid,page,size)));
    }

    @RequestMapping(value = "/hotlist/{labelid}/{page}/{size}",method = RequestMethod.GET)
    public Result hotList(@PathVariable String labelid, @PathVariable Integer page, @PathVariable Integer size){
        return new Result(true, StatusCode.Ok,"请求成功",new PageResult<>(problemService.hotList(labelid,page,size).size(),problemService.hotList(labelid,page,size)));
    }

    @RequestMapping(value = "/waitList/{labelid}/{page}/{size}",method = RequestMethod.GET)
    public Result waitList(@PathVariable String labelid, @PathVariable Integer page, @PathVariable Integer size){
        return new Result(true, StatusCode.Ok,"请求成功",new PageResult<>(problemService.waitList(labelid,page,size).size(),problemService.waitList(labelid,page,size)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.Ok,"查询成功",problemService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{proid}")
    public Result findById(@PathVariable String proid){
        return new Result(true,StatusCode.Ok,"请求成功",problemService.findById(proid));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Problem problem){
        problem.setId(idWorker.nextId()+"");
        return new Result(true,StatusCode.Ok,"请求成功",problemService.save(problem));
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{proid}")
    public Result update(@PathVariable String proid,@RequestBody Problem problem){
        problem.setId(proid);
        return new Result(true,StatusCode.Ok,"请求成功",problemService.save(problem));
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{proid}")
    public Result delete(@PathVariable String proid){
        problemService.delete(proid);
        return new Result(true,StatusCode.Ok,"删除成功");
    }

}
