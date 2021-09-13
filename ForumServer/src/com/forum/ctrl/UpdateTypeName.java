package com.forum.ctrl;

import com.forum.dao.AreaTypeDao;
import com.forum.dao.BaseDao;
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
import java.util.Map;

@WebServlet(name = "UpdateTypeName")
public class UpdateTypeName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseDao baseDao = new BaseDao();
        Map map = baseDao.FromJson(request);
        int typeId= ((Double)map.get("typeId")).intValue();
        String typeName=(String)map.get("typeName");
        //System.out.println(typeId+"--"+typeName);
        AreaType areaType = new AreaType(typeId,typeName);
        AreaTypeDao areaTypeDao = new AreaTypeDao();
        Map map1=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            boolean bool = areaTypeDao.updateTypeName(areaType);
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
