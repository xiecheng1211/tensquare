package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author dongcheng
 * create date 2019/2/24
 **/
public interface ProblemDao extends JpaRepository<Problem,String>, JpaSpecificationExecutor<Problem> {

    @Query(nativeQuery = true,value = "select * from tb_problem t , tb_pl tb where t.id = tb.problemid and tb.labelid=? order by t.replytime desc")
    public List<Problem> newList(String labelid, Pageable pageable);

    @Query(nativeQuery = true,value = "select * from tb_problem t , tb_pl tb where t.id = tb.problemid and tb.labelid=? order by t.reply desc")
    public List<Problem> hotList(String labelid, Pageable pageable);

    @Query(nativeQuery = true,value = "select * from tb_problem t , tb_pl tb where t.id = tb.problemid and tb.labelid=? and reply=0 order by t.createtime desc")
    public List<Problem> waitList(String labelid, Pageable pageable);
}
