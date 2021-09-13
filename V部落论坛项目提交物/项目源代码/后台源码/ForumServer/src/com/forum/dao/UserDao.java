package com.forum.dao;

import com.forum.entity.ForumUser;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao extends BaseDao{
    //普通用户登录
    public ForumUser login(String userId) throws Exception {
        ForumUser forumUser=null;
        Connection conn=getConnection();
        String sql="select * from forum_user where userId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            String pwd=rs.getString(2);
            String nickName=rs.getString(3);
            String tel=rs.getString(4);
            String userPic=rs.getString(5);
            Date regTime=rs.getDate(6);
            int userState=rs.getInt(7);
            forumUser=new ForumUser(userId,pwd,nickName,tel,userPic,regTime,userState);
        }
        return forumUser;
    }
//    注册
    public boolean register(ForumUser user) throws Exception {
        Connection conn=getConnection();
        String sql="insert into forum_user values(?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,user.getUserId());
        ps.setString(2,user.getUserPwd());
        ps.setString(3,user.getNickName());
        ps.setString(4,user.getTel());
        ps.setString(5,user.getUserPic());
        ps.setDate(6,user.getRegisterTime());
        ps.setInt(7,user.getUserState());
        int i = ps.executeUpdate();
        if (i!=0){
            return true;
        }else {
            return false;
        }
    }

    //修改头像
    public void updatePic(String pic,String userId) throws Exception {
        Connection conn=getConnection();
        String sql="update forum_user set userPic=? where userId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,pic);
        ps.setString(2,userId);
        ps.executeUpdate();
    }
}
