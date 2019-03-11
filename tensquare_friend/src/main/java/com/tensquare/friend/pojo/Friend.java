package com.tensquare.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author dongcheng
 * create date 2019/3/11
 **/
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Friend {
    @Id
    private String userid;
    @Id
    private String friendid;
    //是否相互喜欢 0: 单向喜欢 1：互相喜欢
    private String islike;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }
}
