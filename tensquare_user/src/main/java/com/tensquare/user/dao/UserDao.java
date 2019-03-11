package com.tensquare.user.dao;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dongcheng
 * create date 2019/3/5
 **/
public interface UserDao extends JpaRepository<User, String> , JpaSpecificationExecutor<User> {
    public User findByMobile(String mobile);
    @Modifying
    @Query("update tb_User u set u.fanscount= u.fanscount+#{x} where u.id=#{userid}")
    public void incFanscount(String userid, int x);

    @Modifying
    @Query("update tb_User u set u.fllowcount= u.fllowcount+#{x} where u.id=#{userid}")
    public void updatefollowcount(String userid, int x);
}
