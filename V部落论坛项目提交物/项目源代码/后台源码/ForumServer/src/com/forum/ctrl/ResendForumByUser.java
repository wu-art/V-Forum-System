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

@WebServlet(name = "ResendForumByUser")
public class ResendForumByUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int fid=Integer.parseInt(request.getParameter("fid"));
        ForumDao forumDao = new ForumDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            forumDao.resetSendForum(fid);
            map.put("state",true);
            map.put("msg","重新发表成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String resetForum = gson.toJson(map);
        response.getWriter().append(resetForum);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
