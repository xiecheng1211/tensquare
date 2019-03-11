package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author dongcheng
 * create date 2019/3/4
 **/
@Controller
@CrossOrigin
@RequestMapping("/search")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET, value = "/{key/{page}/{size}")
    public PageResult findByKey(@PathVariable String key, @PathVariable Integer page, @PathVariable Integer size){
        Page<Article> page1 = articleService.findbykey(key,page,size);
        return new PageResult<Article>(page1.getTotalElements(),page1.getContent());
    }


}
