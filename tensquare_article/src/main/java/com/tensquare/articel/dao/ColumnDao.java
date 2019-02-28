package com.tensquare.articel.dao;

import com.tensquare.articel.poji.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author dongcheng
 * create date 2019/2/25
 **/
public interface ColumnDao extends JpaRepository<Column,String>, JpaSpecificationExecutor<Column> {
}
