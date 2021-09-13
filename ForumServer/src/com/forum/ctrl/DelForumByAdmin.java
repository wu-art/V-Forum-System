package com.forum.ctrl;

import com.forum.dao.ForumDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DelForumByAdmin")
public class DelForumByAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //管理员可删任意帖子
        int fid=Integer.parseInt(request.getParameter("fid"));
        ForumDao forumDao = new ForumDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            forumDao.delForum(fid);
            map.put("state",true);
            map.put("msg","删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String delForumAm = gson.toJson(map);
        response.getWriter().append(delForumAm);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
