package com.tensquare.spit.dao;

import com.tensquare.spit.poji.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dongcheng
 * create date 2019/2/27
 **/
public interface SpitDao extends MongoRepository<Spit,String> {

    public Page<Spit> findByParentid(String id, Pageable pageable);

}
