package com.forum.ctrl;

import com.forum.dao.CommentDao;
import com.forum.dao.MessageDao;
import com.forum.entity.Comment;
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

@WebServlet(name = "FindCommentByUserId")
public class FindCommentByUserId extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get传参
        int fid=Integer.parseInt(request.getParameter("fid"));
        //String userId=request.getParameter("userId");
        CommentDao commentDao = new CommentDao();
        Map map=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            List<Comment> comList = commentDao.findComment(fid);
            if (comList.size()!=0){
                msg.setState(true);
                map.put("state",msg.isState());
                map.put("comList",comList);
            }else {
                msg.setState(false);
                msg.setMsg("暂无评论");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
            }
            String comJson = gson.toJson(map);
            response.getWriter().append(comJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
