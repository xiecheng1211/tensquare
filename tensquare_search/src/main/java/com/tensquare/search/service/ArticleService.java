package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

/**
 * @author dongcheng
 * create date 2019/3/4
 **/
@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private IdWorker idWorker;

    public Page<Article> findbykey(String key,Integer page, Integer size){
        if (page == null || page <0){
            page = 0;
        }
        if (size == null || size <1){
            size = 10;
        }
        return articleDao.findByTileLikeOrContentLike(key,key, PageRequest.of(page,size));
    }

}
