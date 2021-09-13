package com.forum.entity;

//举报
public class ReportComment {
    private int rid;
    private Admin admin; //loginId
    private ForumUser user;//userId
    private Comment comment; //cid
    private String repContent; //举报内容
    private int repState; //状态

    public ReportComment() {
    }

    public ReportComment(int rid, Admin admin, ForumUser user, Comment comment, String repContent, int repState) {
        this.rid = rid;
        this.admin = admin;
        this.user = user;
        this.comment = comment;
        this.repContent = repContent;
        this.repState = repState;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ForumUser getUser() {
        return user;
    }

    public void setUser(ForumUser user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getRepContent() {
        return repContent;
    }

    public void setRepContent(String repContent) {
        this.repContent = repContent;
    }

    public int getRepState() {
        return repState;
    }

    public void setRepState(int repState) {
        this.repState = repState;
    }
}
