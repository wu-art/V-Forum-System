package com.forum.dao;

import com.forum.entity.CollectForum;
import com.forum.entity.CollectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ColForumDao extends BaseDao {
    //添加收藏
    public void addColForum(CollectForum cf) throws Exception {
        Connection conn=getConnection();
        String sql="insert into collection values(forum_seq.nextval,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,cf.getForum().getFid());
        ps.setString(2,cf.getUser().getUserId());
        ps.setString(3,cf.getCollTime());
        ps.executeUpdate();
    }
    //用户收藏列表
    public List<CollectList> getCollectList(String userId,int currPage,int pageSize) throws Exception {
        List<CollectList> list=new ArrayList<CollectList>();
        Connection conn=getConnection();
        String sql="select e1.* from\n" +
                "(select e.*,rownum r from \n" +
                "(select c.col_id,u.userpic,u.nickname,f.title,a.typename,c.collTime from \n" +
                "       collection c,forum f,forum_user u,areatype a where\n" +
                "       c.fid=f.fid and f.userid=u.userid and f.typeid=a.typeid and c.userid=?\n" +
                "       order by f.release_time) e where rownum<=?) e1 where r>=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,userId);
        ps.setInt(2,currPage*pageSize);
        ps.setInt(3,(currPage-1)*pageSize+1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int cid=rs.getInt(1);
            String userPic=rs.getString(2);
            String nickName=rs.getString(3);
            String title=rs.getString(4);
            String typeName=rs.getString(5);
            String collTime=rs.getString(6);
            CollectList collist = new CollectList(cid,userPic,nickName,title,typeName,collTime);
            list.add(collist);
        }
        return list;
    }
    //总数
    public int getColCount(String userId) throws Exception {
        int counts=0;
        Connection conn=getConnection();
        String sql="select count(*) from collection where userId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,userId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            counts=rs.getInt(1);
        }
        return counts;
    }
    //删除收藏
    public void delCollect(int cid) throws Exception {
        Connection conn=getConnection();
        String sql="delete from collection where col_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,cid);
        ps.executeUpdate();
    }
    //根据fid和当前userId查询,去重，保证每个帖子收藏一次
    public int getColForumByFid(int fid,String userId) throws Exception {
        int count=0;
        Connection conn=getConnection();
        String sql="select count(*) from collection where fid=? and userId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,fid);
        ps.setString(2,userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            count=rs.getInt(1);
        }
        return count;
    }
}
