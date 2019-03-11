package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author dongcheng
 * create date 2019/3/4
 **/
public interface ArticleDao extends ElasticsearchRepository<Article,String>, ElasticsearchPersistentEntity<Article> {

    public Page<Article> findByTileLikeOrContentLike(String title, String content, Pageable pageable);


}
