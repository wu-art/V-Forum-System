package com.forum.ctrl;

import com.forum.dao.ForumDao;
import com.forum.entity.ForumByAdmin;
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

@WebServlet(name = "FindForumByAdmin")
public class FindForumByAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currPage=Integer.parseInt(request.getParameter("currPage"));
        int pageSize=Integer.parseInt(request.getParameter("pageSize"));
        ForumDao forumDao = new ForumDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            List<ForumByAdmin> fbaList = forumDao.getForumByAdmin(currPage, pageSize);
            int count = forumDao.findCount();
            if (fbaList!=null){
                map.put("state",true);
                map.put("fbaList",fbaList);
                map.put("totals",count);
            }else {
                map.put("state",false);
                map.put("msg","暂无数据");
                map.put("totals",count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String fJson = gson.toJson(map);
        response.getWriter().append(fJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
