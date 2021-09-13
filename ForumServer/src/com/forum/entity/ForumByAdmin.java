package com.forum.entity;

public class ForumByAdmin {
    private int fid;
    private String title;
    private String userId;
    private String content;
    private String state;

    public ForumByAdmin() {
    }

    public ForumByAdmin(int fid, String title, String userId, String content, String state) {
        this.fid = fid;
        this.title = title;
        this.userId = userId;
        this.content = content;
        this.state = state;
    }
}
