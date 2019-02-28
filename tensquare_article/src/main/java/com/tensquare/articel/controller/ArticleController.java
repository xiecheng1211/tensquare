package com.tensquare.articel.controller;

import com.tensquare.articel.poji.Article;
import com.tensquare.articel.service.ArticleService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

/**
 * @author dongcheng
 * create date 2019/2/25
 **/
@RestController
@CrossOrigin
@RequestMapping(value = "/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private IdWorker idWorker;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.Ok,"请求成功",articleService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{articelId}")
    public Result findById(@PathVariable String articelId){
        return new Result(true, StatusCode.Ok,"查询成功",articleService.findById(articelId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        article.setId(idWorker.nextId()+"");
        return new Result(true, StatusCode.Ok,"保存成功",articleService.save(article));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Result delete(@PathVariable String articelId){
        articleService.delete(articelId);
        return new Result(true, StatusCode.Ok,"删除成功");
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@PathVariable String id,@RequestBody Article article){
        article.setId(id);
        return new Result(true, StatusCode.Ok,"修改成功",articleService.save(article));
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/exmine/{articelId}")
    public Result updateState(@PathVariable String articelId){
        articleService.updateState(articelId);
        return new Result(true, StatusCode.Ok,"审核成功");
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/thumbup/{articelId}")
    public Result addThumbup(@PathVariable String articelId){
        articleService.addThumbup(articelId);
        return new Result(true, StatusCode.Ok,"点赞成功");
    }

}
