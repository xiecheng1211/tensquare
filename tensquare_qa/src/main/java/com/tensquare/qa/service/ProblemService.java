package com.tensquare.qa.service;

import com.tensquare.qa.dao.ProblemDao;
import com.tensquare.qa.pojo.Problem;
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
import java.util.List;

/**
 * @author dongcheng
 * create date 2019/2/24
 **/
@Service
@Transactional
public class ProblemService {

    @Autowired
    private ProblemDao problemDao;

    public List<Problem> newList(String labelid,Integer page,Integer size){
        if (page == null || page <0){
            page = 0;
        }
        if (size == null || size <1){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page-1,size);
        return problemDao.newList(labelid,pageable);
    }

    public List<Problem> hotList(String labelid,Integer page,Integer size){
        if (page == null || page <0){
            page = 0;
        }
        if (size == null || size <1){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page-1,size);
        return problemDao.hotList(labelid,pageable);
    }

    public List<Problem> waitList(String labelid,Integer page,Integer size){
        if (page == null || page <0){
            page = 0;
        }
        if (size == null || size <1){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page-1,size);
        return problemDao.waitList(labelid,pageable);
    }

    public List<Problem> findAll(){
        return problemDao.findAll();
    }

    public Problem findById(String id){
        return problemDao.findById(id).get();
    }

    public Problem save(Problem problem){
        return problemDao.save(problem);
    }

    public void delete(String id){
        problemDao.deleteById(id);
    }

    public List<Problem> getProblems(Problem problem){
        return problemDao.findAll(getSpecification(problem));
    }

    public Page getProblems(Problem problem,Integer page,Integer size){
        if (page == null || page <0){
            page = 0;
        }
        if (size == null || size <1){
            size = 10;
        }
        return problemDao.findAll(getSpecification(problem),PageRequest.of(page-1,size));
    }


    private Specification getSpecification(Problem problem){
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }


}
