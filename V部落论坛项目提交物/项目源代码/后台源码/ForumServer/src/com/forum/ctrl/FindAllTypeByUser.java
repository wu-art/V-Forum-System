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

@WebServlet(name = "FindAllTypeByUser")
public class FindAllTypeByUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端用户展现
        AreaTypeDao areaTypeDao = new AreaTypeDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            List<AreaType> allTypeList = areaTypeDao.findAllTypeList();
            if (allTypeList.size()!=0){
                msg.setState(true);
                map.put("state",msg.isState());
                map.put("typeList",allTypeList);
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
