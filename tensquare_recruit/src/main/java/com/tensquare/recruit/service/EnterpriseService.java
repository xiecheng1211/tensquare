package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.ldap.PagedResultsControl;
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
public class EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;

    public List<Enterprise> findAll(){
        return enterpriseDao.findAll();
    }

    public Enterprise findbyId(String id){
        return (Enterprise) enterpriseDao.findById(id).get();
    }

    public Enterprise save(Enterprise enterprise){
        return (Enterprise) enterpriseDao.save(enterprise);
    }

    public List<Enterprise> findByIshot(){
        return enterpriseDao.findAllByIshot("1");
    }

    public List<Enterprise> findSearch(Enterprise enterprise){
        Specification specification = getSpecification(enterprise);
        return enterpriseDao.findAll(specification);
    }

    private Specification getSpecification(final Enterprise enterprise){
        return new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if(enterprise.getName()!=null && "".equals(enterprise.getName())){
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class),"%"+enterprise.getName()+"%");
                    predicateList.add(predicate);
                }
                if(enterprise.getLogo()!=null && "".equals(enterprise.getLogo())){
                    Predicate predicate = criteriaBuilder.like(root.get("logo").as(String.class),"%"+enterprise.getLogo()+"%");
                    predicateList.add(predicate);
                }
                if(enterprise.getSummary()!=null && "".equals(enterprise.getSummary())){
                    Predicate predicate = criteriaBuilder.like(root.get("summary").as(String.class),"%"+enterprise.getSummary()+"%");
                    predicateList.add(predicate);
                }
                Predicate[] predicates = new Predicate[predicateList.size()];
                predicateList.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        };
    }

    public Page findSearch(Enterprise enterprise, Integer page, Integer size){
        Specification specification = getSpecification(enterprise);
        if(page == null || page < 0){
            page = 0;
        }
        if(size == null || size < 0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page-1,size);
        return enterpriseDao.findAll(specification,pageable);
    }

    public void delete(String id){
        enterpriseDao.deleteById(id);
    }


}
