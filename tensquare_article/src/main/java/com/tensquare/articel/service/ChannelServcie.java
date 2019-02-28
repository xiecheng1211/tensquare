package com.tensquare.articel.service;

import com.tensquare.articel.dao.ChannelDao;
import com.tensquare.articel.poji.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author dongcheng
 * create date 2019/2/25
 **/
@Service
@Transactional
public class ChannelServcie {

    @Autowired
    private ChannelDao channelDao;

    public List<Channel> findAll(){
        return channelDao.findAll();
    }

    public Channel findById(String id){
        Channel Channel = channelDao.findById(id).get();
        return Channel;
    }

    public Channel save(Channel channel){
        return channelDao.save(channel);
    }

    public void delete(String id){
        channelDao.deleteById(id);
    }

    public List<Channel> findSearch(Channel channel){
        return channelDao.findAll(getSpecification(channel));
    }

    public Page findSearch(Channel channel, Integer page, Integer size){
        if (page==null || page<0){
            page = 0;
        }
        if (size == null || size<1 ){
            size = 10;
        }
        return channelDao.findAll(getSpecification(channel), PageRequest.of(page,size));
    }

    private Specification getSpecification(Channel channel){
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }
    
}
