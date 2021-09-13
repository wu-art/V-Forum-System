package com.forum.entity;

import javax.persistence.Table;
import java.sql.Date;

/**
 * 对应评论表
 * coment
 * 数据库中comment无效,用coment
 */
public class Comment {
    private int cid;
    //帖子ID int fid
    private Forum forum;
    //用户ID String userId
    private ForumUser forumUser;
    //评论内容
    private String comContent;
    //评论时间
    private String comTime;
    //评论图片
    private String comFile;

    public Comment() {
    }

    public Comment(int cid, Forum forum, ForumUser forumUser, String comContent, String comTime,String comFile) {
        this.cid = cid;
        this.forum = forum;
        this.forumUser = forumUser;
        this.comContent = comContent;
        this.comTime = comTime;
        this.comFile=comFile;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public ForumUser getForumUser() {
        return forumUser;
    }

    public void setForumUser(ForumUser forumUser) {
        this.forumUser = forumUser;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public String getComTime() {
        return comTime;
    }

    public void setComTime(String comTime) {
        this.comTime = comTime;
    }

    public String getComFile() {
        return comFile;
    }

    public void setComFile(String comFile) {
        this.comFile = comFile;
    }
}
