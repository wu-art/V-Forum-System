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

@WebServlet(name = "UpdateForumState")
public class UpdateForumState extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int fid=Integer.parseInt(request.getParameter("fid"));
        String state=request.getParameter("state");
        ForumDao forumDao = new ForumDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            forumDao.updateForumState(fid,state);
            map.put("state",true);
            map.put("msg","修改成功,此帖将不再显示!");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String upJson = gson.toJson(map);
        response.getWriter().append(upJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
