package com.tensquare.articel.service;

import com.tensquare.articel.dao.ArticleDao;
import com.tensquare.articel.poji.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author dongcheng
 * create date 2019/2/25
 **/
@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private RedisTemplate redisTemplate;
    public List<Article> findAll(){
        return articleDao.findAll();
    }

    public Article findById(String id){
        Article article = (Article) redisTemplate.opsForValue().get("article_id"+id);
        if (article == null){
            article = articleDao.findById(id).get();
            //有效期60秒
            redisTemplate.opsForValue().set("article_id"+id,article,60, TimeUnit.SECONDS);
        }
        return article;
    }

    public Article save(Article article){
        article = articleDao.save(article);
        redisTemplate.opsForValue().set("article_id"+article.getId(),article);
        return article;
    }

    public void delete(String id){
        redisTemplate.delete("article_id"+id);
        articleDao.deleteById(id);
    }

    public List<Article> findSearch(Article article){
        return articleDao.findAll(getSpecification(article));
    }

    public Page findSearch(Article article, Integer page, Integer size){
        if (page==null || page<0){
            page = 0;
        }
        if (size == null || size<1 ){
            size = 10;
        }
        return articleDao.findAll(getSpecification(article), PageRequest.of(page,size));
    }

    private Specification getSpecification(Article article){
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }

    public void updateState(String id){
        articleDao.updateState(id);
    }

    public void addThumbup(String id){
        articleDao.addThumbup(id);
    }

}
