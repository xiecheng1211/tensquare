package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.getOne(id);
    }

    public Label update(Label label){
        return labelDao.save(label);
    }

    public Label save(Label label){
        label.setId(idWorker.nextId()+"");
        return labelDao.save(label);
    }

    public void delete(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(getSpecification(label));
    }

    public List<Label> findSearch(Map map) {
        return labelDao.findAll(getSpecification(map));
    }

    public Page<Label> pageQuery(Label label, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(getSpecification(label),pageable);
    }

    public Page<Label> pageQuery(Map map, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(getSpecification(map),pageable);
    }


    private Specification<Label> getSpecification(final Label label) {
       return new Specification<Label>(){
           public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
               List<Predicate> predicateList = new ArrayList<Predicate>();
               if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                   //labelname模糊查询
                   Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
                   predicateList.add(predicate);
               }
               if(label.getState()!=null && !"".equals(label.getState())){
                   Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class),label.getState());
                   predicateList.add(predicate);
               }
               Predicate[] predicates = new Predicate[predicateList.size()];
               predicateList.toArray(predicates);
               return criteriaBuilder.and(predicates);
           }
       };
    }

    private Specification<Label> getSpecification(final Map map) {
        return new Specification<Label>(){
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if(map.get("labelname")!=null && !"".equals(map.get("labelname"))){
                    //labelname模糊查询
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class),"%"+map.get("labelname")+"%");
                    predicateList.add(predicate);
                }
                if(map.get("state")!=null && !"".equals(map.get("state"))){
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class),map.get("state"));
                    predicateList.add(predicate);
                }
                Predicate[] predicates = new Predicate[predicateList.size()];
                predicateList.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        };
    }
}
