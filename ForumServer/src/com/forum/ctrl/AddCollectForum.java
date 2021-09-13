package com.forum.ctrl;

import com.forum.dao.BaseDao;
import com.forum.dao.ColForumDao;
import com.forum.dao.MessageDao;
import com.forum.entity.CollectForum;
import com.forum.entity.Forum;
import com.forum.entity.ForumUser;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddCollectForum")
public class AddCollectForum extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //JSON
        BaseDao baseDao = new BaseDao();
        Map map = baseDao.FromJson(request);
        Double f=(Double)map.get("fid");
        int fid=new Double(f).intValue();
        String userId=(String)map.get("userId"); //当前用户
        //System.out.println(f+"--"+userId);
        String collTime=(String)map.get("collTime");
        Forum forum = new Forum();
        forum.setFid(fid);
        ForumUser fu=new ForumUser();
        fu.setUserId(userId);
        CollectForum collectForum = new CollectForum(-1,forum,fu,collTime);
        ColForumDao colForumDao = new ColForumDao();
        Map map1=new HashMap();
        Gson gson = new Gson();
        MessageDao msg = new MessageDao();
        try {
            int counts = colForumDao.getColForumByFid(fid,userId);
            if (counts==1){
                msg.setState(false);
                msg.setMsg("该条已收藏,请至个人中心-->收藏夹查看");
                map1.put("state",msg.isState());
                map1.put("msg",msg.getMsg());
            }else if (counts==0){
                colForumDao.addColForum(collectForum);
                msg.setMsg("收藏成功");
                msg.setState(true);
                map1.put("state",msg.isState());
                map1.put("msg",msg.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setState(false);
            map1.put("state",msg.isState());
            map1.put("msg",e.getMessage());
        }
        String colJson = gson.toJson(map1);
        response.getWriter().append(colJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
