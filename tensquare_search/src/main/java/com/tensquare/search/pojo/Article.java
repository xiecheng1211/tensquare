package com.tensquare.search.pojo;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author dongcheng
 * create date 2019/3/4
 **/
@Document(indexName = "tensquare",type="article")
public class Article implements Serializable {
    @Id
    private String id;
    @Field(index = true, searchAnalyzer = "ik_max_work", analyzer="ik_max_work")
    private String tile;
    @Field(index = true, searchAnalyzer = "ik_max_work", analyzer="ik_max_work")
    private String content;

    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
