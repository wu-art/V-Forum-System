package com.forum.ctrl;

import com.forum.dao.AdminDao;
import com.forum.dao.BaseDao;
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

@WebServlet(name = "UpdateUserState")
public class UpdateUserState extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId=request.getParameter("userId");
        int userState=Integer.parseInt(request.getParameter("userState"));
        AdminDao adminDao = new AdminDao();
        Map map1=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            boolean b = adminDao.banUser(userId, userState);
            if (b){
                msg.setState(true);
                msg.setMsg("状态修改成功");
                map1.put("state",msg.isState());
                map1.put("msg",msg.getMsg());
            }else {
                msg.setState(false);
                msg.setMsg("状态修改失败");
                map1.put("state",msg.isState());
                map1.put("msg",msg.getMsg());
            }
            String updateJson = gson.toJson(map1);
            response.getWriter().append(updateJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
