package com.forum.entity;

import java.sql.Date;

public class Forum {
    private int fid;
    private String title;
    //外键
    private ForumUser forumUser;  //userId
    private AreaType areaType; //typeId
    private String releaseTime; //发帖时间
    private String content;
    private String state; //帖子状态(审核中,审核已通过)
    private String uploadFile; //上传文件/图片

    public Forum() {
    }

    public Forum(int fid, String title, ForumUser forumUser, AreaType areaType, String releaseTime, String content, String state, String uploadFile) {
        this.fid = fid;
        this.title = title;
        this.forumUser = forumUser;
        this.areaType = areaType;
        this.releaseTime = releaseTime;
        this.content = content;
        this.state = state;
        this.uploadFile = uploadFile;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ForumUser getForumUser() {
        return forumUser;
    }

    public void setForumUser(ForumUser forumUser) {
        this.forumUser = forumUser;
    }

    public AreaType getAreaType() {
        return areaType;
    }

    public void setAreaType(AreaType areaType) {
        this.areaType = areaType;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }
}
