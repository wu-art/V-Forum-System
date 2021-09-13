package com.forum.ctrl;

import com.forum.dao.ForumDao;
import com.forum.dao.MessageDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DelForumByUser")
public class DelForumByUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用户删除自己对应的帖子(登陆状态)
        int fid=Integer.parseInt(request.getParameter("fid"));
        ForumDao forumDao = new ForumDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            forumDao.delForum(fid);
            msg.setState(true);
            msg.setMsg("删除成功");
            map.put("state",msg.isState());
            map.put("msg",msg.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setState(false);
            msg.setMsg(e.getMessage());
        }
        String delForum = gson.toJson(map);
        response.getWriter().append(delForum);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
