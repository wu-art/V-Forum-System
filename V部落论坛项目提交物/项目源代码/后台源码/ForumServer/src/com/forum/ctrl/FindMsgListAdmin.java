package com.forum.ctrl;

import com.forum.dao.AdminMsgDao;
import com.forum.entity.AdminMessage;
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

@WebServlet(name = "FindMsgListAdmin")
public class FindMsgListAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //管理员获取消息列表
        int currPage=Integer.parseInt(request.getParameter("currentPage"));
        int pageSize=Integer.parseInt(request.getParameter("pageSize"));
        AdminMsgDao amMsg = new AdminMsgDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            List<AdminMessage> msgList = amMsg.getMsgList(currPage,pageSize);
            int count = amMsg.getCount();
            if (msgList!=null){
                map.put("state",true);
                map.put("msgList",msgList);
                map.put("totals",count);
            }else {
                map.put("state",false);
                map.put("msg","暂无消息");
                map.put("totals",count);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String findJson = gson.toJson(map);
        response.getWriter().append(findJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
