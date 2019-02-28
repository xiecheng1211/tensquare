package com.tensquare.articel.service;

import com.tensquare.articel.dao.ColumnDao;
import com.tensquare.articel.poji.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author dongcheng
 * create date 2019/2/25
 **/
public class ColumnService {

    @Autowired
    private ColumnDao columnDao;

    public List<Column> findAll(){
        return columnDao.findAll();
    }

    public Column findById(String id){
        Column Column = columnDao.findById(id).get();
        return Column;
    }

    public Column save(Column Column){
        return columnDao.save(Column);
    }

    public void delete(String id){
        columnDao.deleteById(id);
    }

    public List<Column> findSearch(Column Column){
        return columnDao.findAll(getSpecification(Column));
    }

    public Page findSearch(Column column, Integer page, Integer size){
        if (page==null || page<0){
            page = 0;
        }
        if (size == null || size<1 ){
            size = 10;
        }
        return columnDao.findAll(getSpecification(column), PageRequest.of(page,size));
    }

    private Specification getSpecification(Column column){
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }

}
