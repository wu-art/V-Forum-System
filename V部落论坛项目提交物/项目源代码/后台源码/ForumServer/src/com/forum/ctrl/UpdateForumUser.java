package com.forum.ctrl;

import com.forum.dao.AdminDao;
import com.forum.dao.BaseDao;
import com.forum.dao.MessageDao;
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

@WebServlet(name = "UpdateForumUser")
public class UpdateForumUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseDao baseDao = new BaseDao();
        Map map = baseDao.FromJson(request);
        String tel=(String)map.get("tel");
        String nickName=(String)map.get("nickName");
        String userId=(String)map.get("userId");
        //System.out.println(tel+"--"+nickName+"--"+userState);
        ForumUser forumUser = new ForumUser();
        forumUser.setUserId(userId);
        forumUser.setNickName(nickName);
        forumUser.setTel(tel);
        //forumUser.setUserState(userState);
        AdminDao adminDao = new AdminDao();
        Map map1=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            boolean bool = adminDao.updateUser(forumUser);
            if (bool){
                msg.setState(true);
                msg.setMsg("修改成功");
                map1.put("state",msg.isState());
                map1.put("msg",msg.getMsg());
            }else {
                msg.setState(false);
                msg.setMsg("修改失败");
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
