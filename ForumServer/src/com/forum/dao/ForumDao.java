package com.forum.dao;

import com.forum.entity.AreaType;
import com.forum.entity.Forum;
import com.forum.entity.ForumByAdmin;
import com.forum.entity.ForumUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ForumDao extends BaseDao {
    //发表帖子
    public boolean addForum(Forum forum) throws Exception {
        Connection conn=getConnection();
        String sql="insert into forum values(forum_seq.nextval,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,forum.getTitle());
        ps.setString(2,forum.getForumUser().getUserId());
        ps.setInt(3,forum.getAreaType().getTypeId());
        ps.setString(4,forum.getReleaseTime());
        ps.setString(5,forum.getContent());
        ps.setString(6,forum.getState());
        ps.setString(7,forum.getUploadFile());
        int i = ps.executeUpdate();
        if (i>0){
            return true;
        }else {
            return false;
        }

    }

    //用户删帖(只能删自己的)
    public void delForum(int fid) throws Exception {
        Connection conn=getConnection();
        String sql="delete from forum where fid=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,fid);
        ps.executeUpdate();
    }
    /**
     * 分页查询所有帖子
     * @param maxPage 最大页码
     * @param minPage 最小页码
     * @param title  帖子标题搜索
     * @param typeName 帖子种类搜索
     * @return
     */
    public List<Forum> findAllForumByPage(int maxPage,int minPage,String title,String typeName) throws Exception {
        List<Forum> forumList=new ArrayList<Forum>();
        Connection conn=getConnection();
        String sql="";
        PreparedStatement ps;
        if (!title.equals("") &&!typeName.equals("")){
            //根据title和type联合查询
            sql="select f2.* from\n" +
                    "(select f1.*,rownum r from\n" +
                    "(select f.*,u.nickName,u.userPic,a.typename from forum f,forum_user u,areatype a \n" +
                    " where f.userid=u.userid and f.typeid=a.typeid and u.user_state=1 and f.state='通过审核' and \n" +
                    "f.title like ? and a.typename like ? order by f.fid)\n" +
                    "f1 where rownum<=?) f2 where r>=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+title+"%");
            ps.setString(2,"%"+typeName+"%");
            ps.setInt(3,maxPage);
            ps.setInt(4,minPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int fid=rs.getInt(1);
                String title1=rs.getString(2);
                String userId=rs.getString(3);
                int typeId=rs.getInt(4);
                String releaseTime=rs.getString(5);
                String content=rs.getString(6);
                String state=rs.getString(7);
                String uploadFile=rs.getString(8);
                String nickName=rs.getString(9);
                String userPic=rs.getString(10);
                //int userState=rs.getInt(11);
                String typeName1=rs.getString(11);
                //赋值
                AreaType areaType = new AreaType(typeId,typeName1);
                //赋值
                ForumUser forumUser=new ForumUser();
                forumUser.setUserId(userId);
                forumUser.setNickName(nickName);
                forumUser.setUserPic(userPic);
                //forumUser.setUserState(userState);
                Forum forum = new Forum(fid,title1,forumUser,areaType,releaseTime,content,state,uploadFile);
                forumList.add(forum);
            }
        }else if (!title.equals("") &&typeName.equals("")){
            //根据title查询
            sql="select f2.* from\n" +
                    "(select f1.*,rownum r from\n" +
                    "(select f.*,u.nickName,u.userPic,a.typename from forum f,forum_user u,areatype a \n" +
                    " where f.userid=u.userid and f.typeid=a.typeid and u.user_state=1 and f.state='通过审核' and \n" +
                    "f.title like ? order by f.fid)\n" +
                    "f1 where rownum<=?) f2 where r>=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+title+"%");
            ps.setInt(2,maxPage);
            ps.setInt(3,minPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int fid = rs.getInt(1);
                String title1 = rs.getString(2);
                String userId = rs.getString(3);
                int typeId = rs.getInt(4);
                String releaseTime = rs.getString(5);
                String content = rs.getString(6);
                String state = rs.getString(7);
                String uploadFile = rs.getString(8);
                String nickName = rs.getString(9);
                String userPic=rs.getString(10);
                //int userState=rs.getInt(11);
                String typeName1 = rs.getString(11);
                //赋值
                AreaType areaType = new AreaType(typeId,typeName1);
                //赋值
                ForumUser forumUser = new ForumUser();
                forumUser.setUserId(userId);
                forumUser.setNickName(nickName);
                forumUser.setUserPic(userPic);
                //forumUser.setUserState(userState);
                Forum forum = new Forum(fid, title1, forumUser, areaType, releaseTime, content, state, uploadFile);
                forumList.add(forum);
            }
        }else if (title.equals("") && !typeName.equals("")){
            //根据typename查询
            sql="select f2.* from\n" +
                    "(select f1.*,rownum r from\n" +
                    "(select f.*,u.nickName,u.userPic,a.typename from forum f,forum_user u,areatype a \n" +
                    " where f.userid=u.userid and f.typeid=a.typeid and u.user_state=1 and f.state='通过审核' and \n" +
                    "a.typename like ? order by f.fid)\n" +
                    "f1 where rownum<=?) f2 where r>=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+typeName+"%");
            ps.setInt(2,maxPage);
            ps.setInt(3,minPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int fid = rs.getInt(1);
                String title1 = rs.getString(2);
                String userId = rs.getString(3);
                int typeId = rs.getInt(4);
                String releaseTime = rs.getString(5);
                String content = rs.getString(6);
                String state = rs.getString(7);
                String uploadFile = rs.getString(8);
                String nickName = rs.getString(9);
                String userPic=rs.getString(10);
                //int userState=rs.getInt(11);
                String typeName1 = rs.getString(11);
                //赋值
                AreaType areaType = new AreaType(typeId,typeName1);
                //赋值
                ForumUser forumUser = new ForumUser();
                forumUser.setUserId(userId);
                forumUser.setNickName(nickName);
                forumUser.setUserPic(userPic);
                //forumUser.setUserState(userState);
                Forum forum = new Forum(fid, title1, forumUser, areaType, releaseTime, content, state, uploadFile);
                forumList.add(forum);
            }
        }else if (title.equals("") && typeName.equals("")){
            //分页查询所有
            sql="select f2.* from\n" +
                    "(select f1.*,rownum r from\n" +
                    "(select f.*,u.nickName,u.userPic,a.typename from forum f,forum_user u,areatype a \n" +
                    " where f.userid=u.userid and f.typeid=a.typeid and u.user_state=1 order by f.fid)\n"+
                    "f1 where rownum<=?) f2 where r>=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,maxPage);
            ps.setInt(2,minPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int fid = rs.getInt(1);
                String title1 = rs.getString(2);
                String userId = rs.getString(3);
                int typeId = rs.getInt(4);
                String releaseTime = rs.getString(5);
                String content = rs.getString(6);
                String state = rs.getString(7);
                String uploadFile = rs.getString(8);
                String nickName = rs.getString(9);
                String userPic=rs.getString(10);
                //int userState=rs.getInt(11);
                String typeName1 = rs.getString(11);
                //赋值
                AreaType areaType = new AreaType(typeId,typeName1);
                //赋值
                ForumUser forumUser = new ForumUser();
                forumUser.setUserId(userId);
                forumUser.setNickName(nickName);
                forumUser.setUserPic(userPic);
                //forumUser.setUserState(userState);
                Forum forum = new Forum(fid, title1, forumUser, areaType, releaseTime, content, state, uploadFile);
                forumList.add(forum);
            }
        }
        return forumList;
    }
//    查询总条数(用户状态正常)
    public int findCount() throws Exception {
        int count=0;
        Connection conn=getConnection();
        String sql="select count(*) from Forum f,forum_user u where f.userId=u.userId and u.user_state=1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            count=rs.getInt(1);
        }
        return count;
    }
    //分页查询部分信息
    public List<ForumByAdmin> getForumByAdmin(int currPage,int pageSize) throws Exception {
        List<ForumByAdmin> forumList=new ArrayList<>();
        Connection conn=getConnection();
        String sql="select f1.* from\n" +
                "(select f.fid,f.title,f.userid,f.content,f.state,rownum r from forum f\n" +
                "where rownum<=? order by f.fid) f1 where r>=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,currPage*pageSize);
        ps.setInt(2,(currPage-1)*pageSize+1);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int fid=rs.getInt(1);
            String title=rs.getString(2);
            String userId=rs.getString(3);
            String content=rs.getString(4);
            String state=rs.getString(5);
            ForumByAdmin forumByAdmin = new ForumByAdmin(fid,title,userId,content,state);
            forumList.add(forumByAdmin);
        }
        return forumList;
    }
    //修改帖子状态(admin)
    public void updateForumState(int fid,String state) throws Exception {
        Connection conn=getConnection();
        String sql="update forum set state=? where fid=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,state);
        ps.setInt(2,fid);
        ps.executeUpdate();
    }
    //用户重新发送失败的帖子
    public void resetSendForum(int fid) throws Exception {
        Connection conn=getConnection();
        String sql="update forum set state='通过审核' where fid=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,fid);
        ps.executeUpdate();

    }
}
