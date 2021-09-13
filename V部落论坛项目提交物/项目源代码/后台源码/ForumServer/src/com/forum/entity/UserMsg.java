package com.forum.entity;

public class UserMsg {
    private int umId;
    private String adminId; //loginId 发送
    private String userId; //userId 接收
    private String mesContent;
    private String msgTime;

    public UserMsg() {
    }

    public UserMsg(int umId, String adminId, String userId, String mesContent, String msgTime) {
        this.umId = umId;
        this.adminId = adminId;
        this.userId = userId;
        this.mesContent = mesContent;
        this.msgTime = msgTime;
    }
}
