package com.tensquare.articel.dao;

import com.tensquare.articel.poji.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dongcheng
 * create date 2019/2/25
 **/
public interface ArticleDao extends JpaRepository<Article,String> , JpaSpecificationExecutor<Article> {
    @Modifying
    @Query(nativeQuery = true,value = "update tb_article t set t.state='1' where t.id=?")
    public Integer updateState(String id);

    @Modifying
    @Query(nativeQuery = true,value = "update tb_article t set t.thumbup=t.thumbup+1 where t.id=?")
    public Integer addThumbup(String id);

}
