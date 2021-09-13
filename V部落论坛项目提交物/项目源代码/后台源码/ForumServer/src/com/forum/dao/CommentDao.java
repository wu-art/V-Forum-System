package com.forum.dao;


import com.forum.entity.Comment;
import com.forum.entity.Forum;
import com.forum.entity.ForumUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends BaseDao {
    //发表评论(添加)
    public void addComment(Comment comment) throws Exception {
        Connection conn=getConnection();
        //ID可使用序列
        String sql="insert into coment values(forum_seq.nextval,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setInt(1,comment.getCid());
        ps.setInt(1,comment.getForum().getFid());
        ps.setString(2,comment.getForumUser().getUserId());
        ps.setString(3,comment.getComContent());
        ps.setString(4,comment.getComTime());
        ps.setString(5,comment.getComFile());
        ps.executeUpdate();
    }
    //删除评论
    public boolean delComment(int cid) throws Exception {
        Connection conn=getConnection();
        String sql="delete from coment where cid=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,cid);
        int i = ps.executeUpdate(); //i=1
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
    //根据fid和userId查询评论，根据评论时间排序
    public List<Comment> findComment(int fid) throws Exception {
        List<Comment> comList=new ArrayList<Comment>();
        Connection conn=getConnection();
        String sql="select c.*,f.nickName from coment c,forum_user f where c.userId=f.userId and c.fid=? order by c.com_time";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,fid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int cid=rs.getInt(1);
            int fid1=rs.getInt(2);
            String userId1=rs.getString(3);
            String comContent=rs.getString(4);
            String comTime=rs.getString(5);
            String comFile=rs.getString(6);
            String nickName=rs.getString(7);
            Forum forum = new Forum();
            forum.setFid(fid1);
            ForumUser forumUser = new ForumUser();
            forumUser.setUserId(userId1);
            forumUser.setNickName(nickName);
            Comment comment = new Comment(cid,forum,forumUser,comContent,comTime,comFile);
            comList.add(comment);
        }
        return comList;
    }
    //根据cid查询
    public boolean getCommentByCid(int cid) throws Exception {
        Connection conn=getConnection();
        String sql="select * from report where cid=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,cid);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return false;
        }else {
            return true;
        }
    }


}
