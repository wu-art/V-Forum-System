package com.forum.ctrl;

import com.forum.dao.UserMsgDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DelMsgFromUser")
public class DelMsgFromUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int umId=Integer.parseInt(request.getParameter("umId"));
        UserMsgDao userMsgDao = new UserMsgDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            userMsgDao.delMsgFromUser(umId);
            map.put("state",true);
            map.put("msg","删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String delMsg = gson.toJson(map);
        response.getWriter().append(delMsg);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
