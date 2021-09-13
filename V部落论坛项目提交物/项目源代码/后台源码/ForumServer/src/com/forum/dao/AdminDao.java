package com.forum.dao;

import com.forum.entity.Admin;
import com.forum.entity.ForumUser;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//管理员登录
public class AdminDao extends BaseDao{
    public Admin adminLogin(String loginId) throws Exception {
        Admin admin=null;
        Connection conn=getConnection();
        String sql="select * from admin where loginId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,loginId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            String loginPwd=rs.getString(2);
            admin=new Admin(loginId,loginPwd);
        }
        return admin;
    }

//    分页查询所有用户
    public List<ForumUser> findAllForumUser(int currPage,int pageSize) throws Exception {
        List<ForumUser> userList=new ArrayList<ForumUser>();
        Connection conn=getConnection();
        String sql="select f1.* from\n" +
                "(select f.*,rownum r from \n" +
                "(select * from forum_user order by REGIS_TIME) f where rownum<=?) f1 where r>=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,currPage*pageSize);
        ps.setInt(2,(currPage-1)*pageSize+1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String userId=rs.getString(1);
            String userPwd=rs.getString(2);
            String nickName=rs.getString(3);
            String tel=rs.getString(4);
            String userPic=rs.getString(5);
            Date regTime=rs.getDate(6);
            int userState=rs.getInt(7);
            ForumUser forumUser = new ForumUser(userId,userPwd,nickName,tel,userPic,regTime,userState);
            userList.add(forumUser);
        }
        return userList;
    }
    //总数
    public int findUserCount() throws Exception {
        int count=0;
        Connection conn=getConnection();
        String sql="select count(*) from forum_user";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            count=rs.getInt(1);
        }
        return count;
    }
    //修改用户信息
    public boolean updateUser(ForumUser u) throws Exception {
        Connection conn=getConnection();
        String sql="update forum_user set tel=?,nickName=? where userId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,u.getTel());
        ps.setString(2,u.getNickName());
        ps.setString(3,u.getUserId());
        int i = ps.executeUpdate();
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
    //删除用户
    public boolean delUser(String userId) throws Exception {
        Connection conn=getConnection();
        String sql="delete from forum_user where userId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,userId);
        int i = ps.executeUpdate();
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
    //(封禁)用户
    public boolean banUser(String userId,int userState) throws Exception {
        Connection conn=getConnection();
        String sql="update forum_user set user_State=? where userId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,userState);
        ps.setString(2,userId);
        int i = ps.executeUpdate();
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
    //获取所有用户信息
    public List<ForumUser> getUserList() throws Exception {
        List<ForumUser> userList=new ArrayList<ForumUser>();
        Connection conn=getConnection();
        String sql="select * from forum_user order by REGIS_TIME";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String userId=rs.getString(1);
            String nickName=rs.getString(3);
            ForumUser forumUser = new ForumUser();
            forumUser.setUserId(userId);
            forumUser.setNickName(nickName);
            userList.add(forumUser);
        }
        return userList;
    }

}
