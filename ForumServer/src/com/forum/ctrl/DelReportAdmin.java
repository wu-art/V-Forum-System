package com.forum.ctrl;

import com.forum.dao.ReportDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DelReportAdmin")
public class DelReportAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rid=Integer.parseInt(request.getParameter("rid"));
        ReportDao reportDao = new ReportDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            reportDao.delReport(rid);
            map.put("state",true);
            map.put("msg","删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String delJson = gson.toJson(map);
        response.getWriter().append(delJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
