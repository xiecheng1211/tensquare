package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String>, JpaSpecificationExecutor<Friend> {
    /**
     * 根据用户ID与被关注用户ID查询记录个数
     * @param userid 用户id
     * @param friendid 好友id
     * */
    @Query(value = "select count (f) from tb_friend f where f.userid=#{userid} and f.friendid=#{friendid}",nativeQuery = true)
    public int selectCount(String userid, String friendid);

    /**
     * 更新为互为喜欢
     * @param userid 用户id
     * @param friendid 好友id
     * @param islike 喜欢状态
     * */
    @Query(value = "update tb_friend f set f.islike=#{islike} where f.userid=#{userid} and f.friendid=#{friendid}",nativeQuery = true)
    public void updateLike(String userid, String friendid, String islike);

}
