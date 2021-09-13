package com.forum.dao;

import com.forum.entity.Admin;
import com.forum.entity.Comment;
import com.forum.entity.ForumUser;
import com.forum.entity.ReportComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReportDao extends BaseDao {
    //用户举报 -->添加举报信息
    public void addReportForum(ReportComment rf) throws Exception {
        Connection conn=getConnection();
        String sql="insert into report values(forum_seq.nextval,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"admin"); //获取当前登录的管理员rf.getAdmin().getLoginId()
        ps.setString(2,rf.getUser().getUserId());
        ps.setInt(3,rf.getComment().getCid());
        ps.setString(4,rf.getRepContent());
        ps.setInt(5,rf.getRepState());
        ps.executeUpdate();
    }

    //管理员获取举报信息(根据cid获取content)
    public List<ReportComment> getReportList(int currPage,int pageSize) throws Exception {
        List<ReportComment> repList=new ArrayList<>();
        Connection conn=getConnection();
        String sql="select e1.* from\n" +
                "(select e.*,rownum w from\n" +
                "(select r.*,c.com_content from report r,coment c where r.cid=c.cid order by r.rid) e\n" +
                "where rownum<=?) e1 where w>=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,currPage*pageSize);
        ps.setInt(2,(currPage-1)*pageSize+1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int rid=rs.getInt(1);
            String loginId=rs.getString(2);
            Admin admin = new Admin();
            admin.setLoginId(loginId);
            String userId=rs.getString(3);
            ForumUser forumUser = new ForumUser();
            forumUser.setUserId(userId);
            String repContent=rs.getString(5);
            int repState=rs.getInt(6);
            int cid=rs.getInt(4);
            String comContent=rs.getString(7);
            Comment comment = new Comment();
            comment.setCid(cid);
            comment.setComContent(comContent);
            ReportComment reportComment = new ReportComment(rid,admin,forumUser,comment,repContent,repState);
            repList.add(reportComment);
        }
        return repList;
    }
    //总数
    public int getCount() throws Exception {
        int count=0;
        Connection conn=getConnection();
        String sql="select count(*) from report";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            count=rs.getInt(1);
        }
        return count;
    }
    //管理员删除
    public void delReport(int rid) throws Exception {
        Connection conn=getConnection();
        String sql="delete from report where rid=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,rid);
        ps.executeUpdate();
    }
}
