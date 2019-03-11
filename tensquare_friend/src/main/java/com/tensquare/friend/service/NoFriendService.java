package com.tensquare.friend.service;

import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dongcheng
 * create date 2019/3/11
 **/
@Service
@Transactional
public class NoFriendService {
    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userid, String friendid){
        //如果已经存在好友，则不需要添加，不操作
        if (noFriendDao.selectCount(userid,friendid) > 0){
            return 0;
        }
        NoFriend noFriend = new NoFriend();
        noFriend.setFriendid(friendid);
        noFriend.setUserid(userid);
        noFriendDao.save(noFriend);
        return 1;
    }
}
