package com.forum.dao;

import com.forum.entity.Admin;
import com.forum.entity.AdminMessage;
import com.forum.entity.ForumUser;
import com.forum.entity.UserMsg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserMsgDao extends BaseDao {
    //分页获取消息列表
    public List<UserMsg> getMsgList(int currPage, int pageSize,String userId) throws Exception {
        List<UserMsg> msgList=new ArrayList<>();
        Connection conn=getConnection();
        String sql="select e.* from \n" +
                "(select a.*,rownum r from usermessage a \n" +
                "where a.userId=? and rownum<=? order by a.umid) e where r>=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,userId);
        ps.setInt(2,currPage*pageSize);
        ps.setInt(3,(currPage-1)*pageSize+1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int umId=rs.getInt(1);
            String loginId=rs.getString(2);
            String userId1=rs.getString(3);
            String msgContent=rs.getString(4);
            String msgTime=rs.getString(5);
            UserMsg userMsg = new UserMsg(umId,loginId,userId1,msgContent,msgTime);
            msgList.add(userMsg);
        }
        return msgList;
    }

    //总数
    public int getCount(String userId) throws Exception {
        int count=0;
        Connection conn=getConnection();
        String sql="select count(*) from usermessage where userId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            count=rs.getInt(1);
        }
        return count;
    }
    //删除
    public void delMsgFromUser(int umId) throws Exception {
        Connection conn=getConnection();
        String sql="delete from usermessage where umId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,umId);
        ps.executeUpdate();
    }
}
