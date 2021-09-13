package com.forum.ctrl;

import com.forum.dao.CommentDao;
import com.forum.dao.ReportDao;
import com.forum.entity.Admin;
import com.forum.entity.Comment;
import com.forum.entity.ForumUser;
import com.forum.entity.ReportComment;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddReportComment")
public class AddReportComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cid=Integer.parseInt(request.getParameter("cid"));//评论Id
        String userId=request.getParameter("userId"); //举报人
        String repContent=request.getParameter("repContent"); //举报内容

        Admin admin = (Admin) request.getSession().getAttribute("admin");
        ForumUser user = new ForumUser();
        user.setUserId(userId);
        Comment comment = new Comment();
        comment.setCid(cid);
        ReportComment reportComment = new ReportComment(-1,admin,user,comment,repContent,0);
        ReportDao reportDao = new ReportDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        //System.out.println(cid);
        try {
            CommentDao commentDao = new CommentDao();
            boolean bool = commentDao.getCommentByCid(cid);
            if (bool){
                reportDao.addReportForum(reportComment);
                map.put("state",true);
                map.put("msg","反馈已提交,请等待管理员核实并处理");
            }else {
                map.put("state",false);
                map.put("msg","已经提交过该条评论的信息,请勿重复提交!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        String repJson = gson.toJson(map);
        response.getWriter().append(repJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
