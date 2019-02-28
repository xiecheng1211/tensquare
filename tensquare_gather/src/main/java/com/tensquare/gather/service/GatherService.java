package com.tensquare.gather.service;

import com.tensquare.gather.dao.GatherDao;
import com.tensquare.gather.pojo.Gather;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author dongcheng
 * create date 2019/2/26
 **/
@Service
@Transactional
public class GatherService implements Serializable {
    @Autowired
    private GatherDao gatherDao;

    public List<Gather> findAll(){
        return gatherDao.findAll();
    }
    @Cacheable(key = "#id",value = "gather")
    public Gather findById(String id){
        return gatherDao.findById(id).get();
    }
    @CachePut(key = "#gather.id",value = "gather")
    public Gather save(Gather gather){
        return gatherDao.save(gather);
    }
    @CacheEvict(value="gather",key="#id")
    public void delete(String id){
        gatherDao.deleteById(id);
    }

}
