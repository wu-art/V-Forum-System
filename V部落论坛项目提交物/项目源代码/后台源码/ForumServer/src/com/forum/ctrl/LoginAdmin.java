package com.forum.ctrl;

import com.forum.dao.AdminDao;
import com.forum.dao.BaseDao;
import com.forum.dao.MessageDao;
import com.forum.entity.Admin;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginAdmin")
public class LoginAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseDao baseDao = new BaseDao();
        Map map = baseDao.FromJson(request);
        String loginId=(String)map.get("loginId");
        String loginPwd=(String)map.get("loginPwd");
        AdminDao adminDao = new AdminDao();
        Map map1=new HashMap();
        Gson gson = new Gson();
        MessageDao m = new MessageDao();
        try {
            Admin admin = adminDao.adminLogin(loginId);
            if (admin!=null && loginPwd.equals(admin.getLoginPwd())){
                request.getSession().setAttribute("admin",admin);
                m.setState(true);
                m.setMsg("登录成功");
                map1.put("state",m.isState());
                map1.put("msg",m.getMsg());
                map1.put("admin",admin.getLoginId());
            }else {
                m.setState(false);
                m.setMsg("账号或密码错误");
                map1.put("state",m.isState());
                map1.put("msg",m.getMsg());
            }
            String adminJson = gson.toJson(map1);
            response.getWriter().append(adminJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
