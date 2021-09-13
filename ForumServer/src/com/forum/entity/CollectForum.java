package com.forum.entity;

public class CollectForum {
    private int colId;
    private Forum forum;  //fid
    private ForumUser user;  //userId
    private String collTime;

    public CollectForum() {
    }

    public CollectForum(int colId, Forum forum, ForumUser user,String collTime) {
        this.colId = colId;
        this.forum = forum;
        this.user = user;
        this.collTime=collTime;
    }

    public int getColId() {
        return colId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public ForumUser getUser() {
        return user;
    }

    public void setUser(ForumUser user) {
        this.user = user;
    }

    public String getCollTime() {
        return collTime;
    }

    public void setCollTime(String collTime) {
        this.collTime = collTime;
    }
}
