package com.forum.ctrl;

import com.forum.dao.ColForumDao;
import com.forum.dao.MessageDao;
import com.forum.entity.CollectList;
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

@WebServlet(name = "FindCollectByUser")
public class FindCollectByUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get/post
        int currentPage=Integer.parseInt(request.getParameter("currentPage"));
        int pageSize=Integer.parseInt(request.getParameter("pageSize"));
        String userId=request.getParameter("userId");
        ColForumDao colForumDao = new ColForumDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            List<CollectList> clist=colForumDao.getCollectList(userId,currentPage,pageSize);
            int totals=colForumDao.getColCount(userId);
            if (clist.size()!=0){
                msg.setState(true);
                map.put("state",msg.isState());
                map.put("colList",clist);
                map.put("totals",totals);
            }else {
                msg.setState(false);
                msg.setMsg("暂无数据");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setState(false);
            map.put("state",msg.isState());
            map.put("msg",e.getMessage());
        }
        String colJson = gson.toJson(map);
        response.getWriter().append(colJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
