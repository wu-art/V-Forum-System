package com.forum.ctrl;

import com.forum.dao.BaseDao;
import com.forum.dao.ForumDao;
import com.forum.dao.MessageDao;
import com.forum.entity.Forum;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FindForumByPage")
//分页查询帖子
public class FindForumByPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseDao baseDao = new BaseDao();
        //接收JSON
        Map map = baseDao.FromJson(request);
        Double currPage=(Double)map.get("currentPage");
        int currentPage=new Double(currPage).intValue();
        Double size=(Double)map.get("pageSize");
        int pageSize=new Double(size).intValue();
        String title=(String)map.get("forumTitle");
        String typeName=(String)map.get("typeName");  //帖子类型
        if (title==null||title.equals("")){
            title="";
        }
        if (typeName==null||typeName.equals("")){
            typeName="";
        }
        //System.out.println(currentPage+"--"+pageSize+"--"+title+"--"+typeName);
        int minTotal=(currentPage-1)*pageSize+1; //（min,max）条数据
        int maxTotal=currentPage*pageSize;
        ForumDao forumDao = new ForumDao();
        MessageDao m = new MessageDao();
        Map map1=new HashMap();
        Gson gson=new Gson();
        try {
            List<Forum> forumList = forumDao.findAllForumByPage(maxTotal, minTotal, title, typeName);
            int total = forumDao.findCount(); //总数(u.state=1)
            if (forumList.size()!=0){
                m.setState(true);
                m.setMsg("查询成功");
                map1.put("forumList",forumList);
                map1.put("total",total);
                map1.put("state",m.isState());
                map1.put("msg",m.getMsg());
            }else {
                m.setState(false);
                m.setMsg("没有符合该条件的数据");
                map1.put("total",total);
                map1.put("state",m.isState());
                map1.put("msg",m.getMsg());
            }
            String findJson = gson.toJson(map1);
            response.getWriter().append(findJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
