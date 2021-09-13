package com.forum.ctrl;

import com.forum.dao.AreaTypeDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddTypeName")
public class AddTypeName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeName=request.getParameter("typeName");
        AreaTypeDao atd = new AreaTypeDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        //验证类型名是否存在
        try {
            int total = atd.findByName(typeName);
            if (total==0){
                atd.addTypeName(typeName);
                map.put("state",true);
                map.put("msg","添加成功");
            }else {
                map.put("state",false);
                map.put("msg","该类型已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String addType = gson.toJson(map);
        response.getWriter().append(addType);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
