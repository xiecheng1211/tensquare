package com.tensquare.spit.controller;

import com.tensquare.spit.poji.Spit;
import com.tensquare.spit.service.SpitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author dongcheng
 * create date 2019/2/27
 **/
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.Ok, "请求成功", spitService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.Ok, "保存成功");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Result update(@PathVariable String id,@RequestBody Spit spit){
        spit.set_id(id);
        return new Result(true, StatusCode.Ok, "修改成功", spitService.update(spit));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Result delete(@PathVariable String id){
        spitService.detele(id);
        return new Result(true, StatusCode.Ok, "删除成功");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.Ok, "查找成功",spitService.findById(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sons/{id}/{page}/{size}")
    public Result findByParentsId(@PathVariable String id, Integer page, Integer size){
        return new Result(true, StatusCode.Ok, "查找成功",spitService.findByParentid(id, page, size));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/thumup/{id}")
    public Result thumup(@PathVariable String id){
        if(redisTemplate.opsForValue().get("thump_"+id)!=null){
            return new Result(true, StatusCode.Ok, "已经点赞成功");
        }
        spitService.thump(id);
        redisTemplate.opsForValue().set("thump_"+id,1);
        return new Result(true, StatusCode.Ok, "点赞成功");
    }
}
