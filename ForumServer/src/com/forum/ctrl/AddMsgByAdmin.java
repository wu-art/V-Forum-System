package com.forum.ctrl;

import com.forum.dao.AdminMsgDao;
import com.forum.entity.Admin;
import com.forum.entity.AdminMessage;
import com.forum.entity.ForumUser;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddMsgByAdmin")
public class AddMsgByAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //管理员
        Admin admin =(Admin) request.getSession().getAttribute("admin");
        String userId=request.getParameter("userId");
        ForumUser user = new ForumUser();
        user.setUserId(userId);
        String msgContent=request.getParameter("msgContent");
        String msgTime=request.getParameter("msgTime");
        AdminMessage adminMessage = new AdminMessage(-1,admin,user,msgContent,msgTime);
        AdminMsgDao amDao = new AdminMsgDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            amDao.addMsgToAdmin(adminMessage);
            amDao.addMsgToUser(adminMessage);
            map.put("state",true);
            map.put("msg","发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String sendJson = gson.toJson(map);
        response.getWriter().append(sendJson);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
