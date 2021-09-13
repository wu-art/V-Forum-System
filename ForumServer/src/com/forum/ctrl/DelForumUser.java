package com.forum.ctrl;

import com.forum.dao.AdminDao;
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

@WebServlet(name = "DelForumUser")
public class DelForumUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //admin   删除帖子用户
        String userId = request.getParameter("userId");
        AdminDao adminDao = new AdminDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            boolean bool = adminDao.delUser(userId);
            if (bool){
                msg.setState(true);
                msg.setMsg("删除成功");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
            }else {
                msg.setState(false);
                msg.setMsg("删除失败");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
            }
            String delJson = gson.toJson(map);
            response.getWriter().append(delJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
