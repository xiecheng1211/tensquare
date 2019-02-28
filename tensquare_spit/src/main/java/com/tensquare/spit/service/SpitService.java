package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.poji.Spit;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * @author dongcheng
 * create date 2019/2/27
 **/
@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public Spit findById(String spitid){
        return spitDao.findById(spitid).get();
    }

    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spitDao.save(spit);
    }

    public Spit update(Spit spit){
        return spitDao.save(spit);
    }

    public void detele(String id){
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentid(String id,Integer page,Integer size){
        if(page == null || page<0){
            page = 0;
        }
        if(size == null || size<1){
            size = 10;
        }
        return spitDao.findByParentid(id, PageRequest.of(page,size));
    }

    public void thump(String id){
        //点赞
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is("1");
        query.addCriteria(criteria);
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }

}
