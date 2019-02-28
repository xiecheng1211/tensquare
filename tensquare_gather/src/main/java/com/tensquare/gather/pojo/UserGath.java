package com.tensquare.gather.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author dongcheng
 * create date 2019/2/26
 **/
public class UserGath {
    //用户ID
    private String userid;
    //活动ID
    private String gathid;
    //点击时间
    private Date datetime;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGathid() {
        return gathid;
    }

    public void setGathid(String gathid) {
        this.gathid = gathid;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
