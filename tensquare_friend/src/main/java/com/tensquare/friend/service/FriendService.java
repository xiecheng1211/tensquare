package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dongcheng
 * create date 2019/3/11
 **/
@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    public int addFriend(String userid, String friendid){
        //如果已经存在好友，则不需要添加，不操作
        if (friendDao.selectCount(userid,friendid) > 0){
            return 0;
        }
        Friend friend = new Friend();
        friend.setFriendid(friendid);
        friend.setUserid(userid);
        friend.setIslike("0");
        friendDao.save(friend);
        //如果互为好友
        if (friendDao.selectCount(friendid,userid) >0){
            friendDao.updateLike(userid,friendid,"1");
            friendDao.updateLike(friendid,userid,"1");
        }
        return 1;
    }

}
