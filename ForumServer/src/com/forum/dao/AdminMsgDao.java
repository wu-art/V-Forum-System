package com.forum.dao;


import com.forum.entity.Admin;
import com.forum.entity.AdminMessage;
import com.forum.entity.ForumUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminMsgDao extends BaseDao{
    //获取消息列表
    public List<AdminMessage> getMsgList(int currPage,int pageSize) throws Exception {
        List<AdminMessage> msgList=new ArrayList<>();
        Connection conn=getConnection();
        String sql="select e.* from \n" +
                "(select a.*,rownum r from adminmessage a \n" +
                "where rownum<=? order by amid) e where r>=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,currPage*pageSize);
        ps.setInt(2,(currPage-1)*pageSize+1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int amId=rs.getInt(1);
            String loginId=rs.getString(2);
            Admin admin = new Admin();
            admin.setLoginId(loginId);
            String userId=rs.getString(3);
            ForumUser forumUser = new ForumUser();
            forumUser.setUserId(userId);
            String msgContent=rs.getString(4);
            String msgTime=rs.getString(5);
            AdminMessage adminMessage = new AdminMessage(amId,admin,forumUser,msgContent,msgTime);
            msgList.add(adminMessage);
        }
        return msgList;
    }
    //查询总数
    public int getCount() throws Exception {
        int count=0;
        Connection conn=getConnection();
        String sql="select count(*) from adminmessage";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            count=rs.getInt(1);
        }
        return count;
    }
    //发送消息(管理员消息表)
    public void addMsgToAdmin(AdminMessage adminMsg) throws Exception {
        Connection conn=getConnection();
        String sql="insert into adminmessage values(forum_seq.nextval,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,adminMsg.getAdmin().getLoginId());
        ps.setString(2,adminMsg.getUser().getUserId());
        ps.setString(3,adminMsg.getMsgContent());
        ps.setString(4,adminMsg.getMsgTime());
        ps.executeUpdate();
    }
    //发送消息(用户消息表)
    public void addMsgToUser(AdminMessage adminMsg) throws Exception {
        Connection conn=getConnection();
        String sql="insert into usermessage values(forum_seq.nextval,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,adminMsg.getAdmin().getLoginId());
        ps.setString(2,adminMsg.getUser().getUserId());
        ps.setString(3,adminMsg.getMsgContent());
        ps.setString(4,adminMsg.getMsgTime());
        ps.executeUpdate();
    }
    //删除
    public void delMsgFromAdmin(int amId) throws Exception {
        Connection conn=getConnection();
        String sql="delete from adminmessage where amid=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,amId);
        ps.executeUpdate();
    }
}
