package com.forum.ctrl;

import com.forum.dao.AdminDao;
import com.forum.dao.MessageDao;
import com.forum.entity.ForumUser;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FindAllUser")
public class FindAllUserByPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage=Integer.parseInt(request.getParameter("currentPage"));
        int pageSize=Integer.parseInt(request.getParameter("pageSize"));
        //System.out.println(currentPage+"--"+pageSize);
        AdminDao adminDao = new AdminDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            List<ForumUser> userList = adminDao.findAllForumUser(currentPage,pageSize);
            int userCount = adminDao.findUserCount();
            if (userList.size()!=0){
                msg.setState(true);
                map.put("state",msg.isState());
                map.put("userList",userList);
                map.put("totals",userCount);
            }else {
                msg.setState(false);
                msg.setMsg("暂无数据");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
                map.put("totals",userCount);
            }
            String findUser = gson.toJson(map);
            response.getWriter().append(findUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
