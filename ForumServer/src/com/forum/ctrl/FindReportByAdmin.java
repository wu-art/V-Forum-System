package com.forum.ctrl;

import com.forum.dao.ReportDao;
import com.forum.entity.ReportComment;
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

@WebServlet(name = "FindReportByAdmin")
public class FindReportByAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currPage=Integer.parseInt(request.getParameter("currPage"));
        int pageSize=Integer.parseInt(request.getParameter("pageSize"));
        ReportDao reportDao = new ReportDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        try {
            List<ReportComment> reportList = reportDao.getReportList(currPage, pageSize);
            int totals = reportDao.getCount();
            if (reportList!=null){
                map.put("state",true);
                map.put("reportList",reportList);
                map.put("totals",totals);
            }else {
                map.put("state",false);
                map.put("msg","暂无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String findRep = gson.toJson(map);
        response.getWriter().append(findRep);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
