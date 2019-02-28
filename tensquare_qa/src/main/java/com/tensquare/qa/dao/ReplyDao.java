package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author dongcheng
 * create date 2019/2/24
 **/
public interface ReplyDao extends JpaRepository<Reply,String>, JpaSpecificationExecutor {
}
