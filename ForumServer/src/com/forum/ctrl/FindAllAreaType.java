package com.forum.ctrl;

import com.forum.dao.AreaTypeDao;
import com.forum.dao.MessageDao;
import com.forum.entity.AreaType;
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

@WebServlet(name = "FindAllAreaType")
public class FindAllAreaType extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage=Integer.parseInt(request.getParameter("currentPage"));
        int pageSize=Integer.parseInt(request.getParameter("pageSize"));
        AreaTypeDao areaTypeDao = new AreaTypeDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            List<AreaType> allType = areaTypeDao.findAllType(pageSize,currentPage);
            int total = areaTypeDao.findTypeCount();
            if (allType.size()!=0){
                msg.setState(true);
                map.put("state",msg.isState());
                map.put("typeList",allType);
                map.put("total",total);
            }else {
                msg.setState(false);
                msg.setMsg("暂无数据");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
            }
            String typeJson = gson.toJson(map);
            response.getWriter().append(typeJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
