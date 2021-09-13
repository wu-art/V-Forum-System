package com.forum.ctrl;

import com.forum.dao.AreaTypeDao;
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

@WebServlet(name = "DelAreaType")
public class DelAreaType extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeId=Integer.parseInt(request.getParameter("typeId"));
        AreaTypeDao areaTypeDao = new AreaTypeDao();
        MessageDao msg = new MessageDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            boolean bool = areaTypeDao.delType(typeId);
            if (bool){
                msg.setState(true);
                msg.setMsg("删除成功");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
            }else {
                msg.setState(false);
                msg.setMsg("删除失败");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
            }
            String delJson = gson.toJson(map);
            response.getWriter().append(delJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
