package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dongcheng
 * create date 2019/2/21
 **/
@Service
@Transactional
public class RecruitService {
    @Autowired
    private RecruitDao recruitDao;

    public List<Recruit> findAll(){
        return recruitDao.findAll();
    }

    public Recruit save(Recruit recruit){
        return (Recruit) recruitDao.save(recruit);
    }

    public void delete(String id){
        recruitDao.deleteById(id);
    }

    public Recruit findById(String id){
        return (Recruit) recruitDao.findById(id).get();
    }

    public List<Recruit> findByState(String state){
        return recruitDao.findByStateNotOrderByCreatetimeDesc(state);
    }

    public List<Recruit> findSearch(Recruit recruit){
        return recruitDao.findAll(getSpecification(recruit));
    }

    public List<Recruit> findByType(String type){
        return recruitDao.findAllByType(type);
    }

    public Page findSearch(Recruit recruit, Integer page, Integer size){
        if(page==null || page<0){
            page = 0;
        }
        if (size == null || size<1){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page-1,size);
        return recruitDao.findAll(getSpecification(recruit),pageable);
    }

    private Specification getSpecification(final Recruit recruit){
        return new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if(recruit.getSalary()!=null && !"".equals(recruit.getSalary())){
                    Predicate predicate = criteriaBuilder.like(root.get("salary").as(String.class),recruit.getSalary());
                    predicateList.add(predicate);
                }
                if(recruit.getState()!=null && !"".equals(recruit.getState())){
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class),recruit.getState());
                    predicateList.add(predicate);
                }
                if(recruit.getAddress()!=null && !"".equals(recruit.getAddress())){
                    Predicate predicate = criteriaBuilder.like(root.get("address").as(String.class),recruit.getAddress());
                    predicateList.add(predicate);
                }
                if(recruit.getJobname()!=null && !"".equals(recruit.getJobname())){
                    Predicate predicate = criteriaBuilder.like(root.get("jobname").as(String.class),recruit.getJobname());
                    predicateList.add(predicate);
                }
                if(recruit.getEducation()!=null && !"".equals(recruit.getEducation())){
                    Predicate predicate = criteriaBuilder.like(root.get("education").as(String.class),recruit.getEducation());
                    predicateList.add(predicate);
                }
                if(recruit.getLabel()!=null && !"".equals(recruit.getLabel())){
                    Predicate predicate = criteriaBuilder.like(root.get("label").as(String.class),recruit.getLabel());
                    predicateList.add(predicate);
                }
                if(recruit.getType()!=null && !"".equals(recruit.getType())){
                    Predicate predicate = criteriaBuilder.equal(root.get("type").as(String.class),recruit.getType());
                    predicateList.add(predicate);
                }
                Predicate[] predicates = new Predicate[predicateList.size()];
                predicateList.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        };
    }

}
