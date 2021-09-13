package com.forum.dao;

import com.forum.entity.AreaType;

import java.awt.print.Pageable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AreaTypeDao extends BaseDao {
    //分页查询所有分类
    public List<AreaType> findAllType(int size,int currPage) throws Exception {
        List<AreaType> typeList=new ArrayList<AreaType>();
        Connection conn=getConnection();
        String sql="select t1.* from\n" +
                "(select t.*,rownum r from\n" +
                "(select * from areatype order by typeId) t where rownum<=?) t1 where r>=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,size*currPage);
        ps.setInt(2,(currPage-1)* size+1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int typeId=rs.getInt(1);
            String typeName=rs.getString(2);
            AreaType areaType = new AreaType();
            areaType.setTypeId(typeId);
            areaType.setTypeName(typeName);
            typeList.add(areaType);
        }
        return typeList;
    }
    //总数
    public int findTypeCount() throws Exception {
        int count=0;
        Connection conn=getConnection();
        String sql="select count(*) from areatype";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            count=rs.getInt(1);
        }
        return count;
    }
    //修改
    public boolean updateTypeName(AreaType type) throws Exception {
        Connection conn=getConnection();
        String sql="update areatype set typeName=? where typeId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,type.getTypeName());
        ps.setInt(2,type.getTypeId());
        int i = ps.executeUpdate();
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
    //删除
    public boolean delType(int typeId) throws Exception {
        Connection conn=getConnection();
        String sql="delete from areatype where typeId=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,typeId);
        int i = ps.executeUpdate();
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
    //添加
    public void addTypeName(String name) throws Exception {
        Connection conn=getConnection();
        String sql="insert into areatype values(forum_seq.nextval,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,name);
        ps.executeUpdate();
    }
    //所有分类
    public List<AreaType> findAllTypeList() throws Exception {
        List<AreaType> typeList=new ArrayList<AreaType>();
        Connection conn=getConnection();
        String sql="select * from areatype";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int typeId=rs.getInt(1);
            String typeName=rs.getString(2);
            AreaType areaType = new AreaType();
            areaType.setTypeId(typeId);
            areaType.setTypeName(typeName);
            typeList.add(areaType);
        }
        return typeList;
    }
    //根据typeName模糊查询（验证添加时保证唯一性）
    public int findByName(String name) throws Exception {
        int total=0;
        Connection conn=getConnection();
        String sql="select count(*) from areatype where typeName like ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"%"+name+"%");
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            total=rs.getInt(1);
        }
        return total;
    }
}
