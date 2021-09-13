package com.forum.entity;

import java.sql.Date;

public class ForumUser {
    private String userId;
    private String userPwd;
    private String nickName;
    private String tel;
    private String userPic;
    private Date registerTime;  //用户注册时间
    private int userState;  //用户状态 0封禁  1正常

    public ForumUser(){}

    public ForumUser(String userId, String userPwd, String nickName, String tel, String userPic, Date registerTime, int userState) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.nickName = nickName;
        this.tel = tel;
        this.userPic = userPic;
        this.registerTime = registerTime;
        this.userState = userState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }
}
