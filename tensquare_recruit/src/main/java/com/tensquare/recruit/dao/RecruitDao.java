package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author dongcheng
 * create date 2019/2/20
 **/
public interface RecruitDao extends JpaRepository<Recruit,String>, JpaSpecificationExecutor {

    public List<Recruit> findByStateNotOrderByCreatetimeDesc(String state);

    public List<Recruit> findAllByType(String type);

}
