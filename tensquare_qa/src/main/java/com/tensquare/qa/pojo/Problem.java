package com.tensquare.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author dongcheng
 * create date 2019/2/24
 **/
@Entity
@Table(name = "tb_problem")
public class Problem {
    @Id
    private String id;
    //问题标题
    private String title;
    //问题内容
    private String content;
    //发布日期
    private Date createtime;
    //更新日期
    private Date updatetime;
    //发布人id
    private String userid;
    //发布人昵称
    private String nickname;
    //浏览量
    private long visits;
    //点赞数
    private long thumbup;
    //回复数
    private long reply;
    //是否解决
    private String solve;
    //最新回复人
    private String replyname;
    //最新回复时间
    private Date replytime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getVisits() {
        return visits;
    }

    public void setVisits(long visits) {
        this.visits = visits;
    }

    public long getThumbup() {
        return thumbup;
    }

    public void setThumbup(long thumbup) {
        this.thumbup = thumbup;
    }

    public long getReply() {
        return reply;
    }

    public void setReply(long reply) {
        this.reply = reply;
    }

    public String getSolve() {
        return solve;
    }

    public void setSolve(String solve) {
        this.solve = solve;
    }

    public String getReplyname() {
        return replyname;
    }

    public void setReplyname(String replyname) {
        this.replyname = replyname;
    }

    public Date getReplytime() {
        return replytime;
    }

    public void setReplytime(Date replytime) {
        this.replytime = replytime;
    }
}
