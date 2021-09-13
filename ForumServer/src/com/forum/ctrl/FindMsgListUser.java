package com.forum.ctrl;

import com.forum.dao.UserMsgDao;
import com.forum.entity.UserMsg;
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

@WebServlet(name = "FindMsgListUser")
public class FindMsgListUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currPage=Integer.parseInt(request.getParameter("currPage"));
        int pageSize=Integer.parseInt(request.getParameter("pageSize"));
        String userId=request.getParameter("userId");
        UserMsgDao umd = new UserMsgDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            List<UserMsg> userMsgList = umd.getMsgList(currPage, pageSize,userId);
            int count = umd.getCount(userId);
            if (userMsgList!=null){
                map.put("state",true);
                map.put("userMsgList",userMsgList);
                map.put("totals",count);
            }else {
                map.put("state",false);
                map.put("msg","暂无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String fmLi = gson.toJson(map);
        response.getWriter().append(fmLi);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
