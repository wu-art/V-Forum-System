package com.forum.entity;

//管理员发送消息
public class AdminMessage {
    private int amId;
    private Admin admin; //loginId 发送
    private ForumUser user; //userId 接收
    private String msgContent;
    private String msgTime;

    public AdminMessage() {
    }

    public AdminMessage(int amId, Admin admin, ForumUser user, String msgContent, String msgTime) {
        this.amId = amId;
        this.admin = admin;
        this.user = user;
        this.msgContent = msgContent;
        this.msgTime = msgTime;
    }

    public int getAmId() {
        return amId;
    }

    public void setAmId(int amId) {
        this.amId = amId;
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

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }
}
