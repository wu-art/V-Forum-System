package com.forum.entity;

public class CollectList {
    private int cid;
    private String userPic;
    private String nickName;
    private String title;
    private String typeName;
    private String collTime;

    public CollectList() {
    }

    public CollectList(int cid,String userPic, String nickName, String title, String typeName,String collTime) {
        this.cid=cid;
        this.userPic = userPic;
        this.nickName = nickName;
        this.title = title;
        this.typeName = typeName;
        this.collTime=collTime;
    }
}
