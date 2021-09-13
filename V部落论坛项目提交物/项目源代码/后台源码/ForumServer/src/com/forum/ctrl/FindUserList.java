package com.forum.ctrl;

import com.forum.dao.AdminDao;
import com.forum.entity.ForumUser;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FindUserList")
public class FindUserList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDao adminDao = new AdminDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            List<ForumUser> userList = adminDao.getUserList();
            if (userList.size()!=0){
                map.put("state",true);
                map.put("userList",userList);
            }else {
                map.put("state",false);
                map.put("msg","暂无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String fuJson = gson.toJson(map);
        response.getWriter().append(fuJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
