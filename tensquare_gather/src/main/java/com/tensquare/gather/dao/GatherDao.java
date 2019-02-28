package com.tensquare.gather.dao;

import com.tensquare.gather.pojo.Gather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatherDao extends JpaRepository<Gather,String> {
}
