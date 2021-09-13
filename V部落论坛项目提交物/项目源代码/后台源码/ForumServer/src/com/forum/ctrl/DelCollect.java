package com.forum.ctrl;

import com.forum.dao.ColForumDao;
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

@WebServlet(name = "DelCollect")
public class DelCollect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cid=Integer.parseInt(request.getParameter("cid"));
        ColForumDao colForumDao = new ColForumDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            colForumDao.delCollect(cid);
            msg.setState(true);
            msg.setMsg("删除成功");
            map.put("state",msg.isState());
            map.put("msg",msg.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setState(false);
            map.put("state",msg.isState());
            map.put("msg",e.getMessage());
        }
        String delJson = gson.toJson(map);
        response.getWriter().append(delJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
