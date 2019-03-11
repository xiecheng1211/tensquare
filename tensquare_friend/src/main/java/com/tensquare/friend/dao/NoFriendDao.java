package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dongcheng
 * create date 2019/3/11
 **/
public interface NoFriendDao extends JpaRepository<NoFriend,String>, JpaSpecificationExecutor<NoFriend> {
    /**
     * 根据用户ID与被关注用户ID查询记录个数
     * @param userid 用户id
     * @param friendid 好友id
     * */
    @Query("select count (f) from tb_nofriend f where f.userid=#{userid} and f.friendid=#{friendid}")
    public int selectCount(String userid, String friendid);
}
