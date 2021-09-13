package com.forum.ctrl;

import com.forum.dao.BaseDao;
import com.forum.dao.CommentDao;
import com.forum.dao.MessageDao;
import com.forum.entity.Comment;
import com.forum.entity.Forum;
import com.forum.entity.ForumUser;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//发表评论
@WebServlet(name = "AddComment")
public class AddComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收FormData
        int fid=0;
        String userId=null;
        String comContent=null;
        String comTime=null;
        String pic=null;
        String path=getServletContext().getRealPath("upload");
        FileItemFactory factory =new DiskFileItemFactory();
        ServletFileUpload upload =new ServletFileUpload(factory);
        Map map=new HashMap();
        Gson gson=new Gson();
        MessageDao msg = new MessageDao();
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (int i=0;i<items.size();i++) {
                //获取元素
                FileItem item = items.get(i);
                //判断元素类型，true普通表单元素，false文件元素
                if (item.isFormField()) {
                    //通过获取元素名称获取数据
                    String fieldName = item.getFieldName();
                    if (fieldName.equals("fid")){
                        fid=Integer.parseInt(item.getString("utf-8"));
                    }
                    if (fieldName.equals("userId")){
                        userId=item.getString("utf-8");
                    }
                    if (fieldName.equals("comContent")){
                        comContent=item.getString("utf-8");
                    }
                    if (fieldName.equals("comTime")){
                        comTime=item.getString("utf-8");
                    }
                }else{
                    //文件元素
                    //通过上传路径以及文件的名称创建一个实例
                    String picStr=item.getName();
                    //头像可以为空
                    if (!picStr.equals("")){
                        //自定义后缀名
                        String houZhui=picStr.substring(picStr.lastIndexOf('.'),picStr.length());
                        String pics="pic"+System.currentTimeMillis()+houZhui;

                        File saveFile = new File(path, pics);//文件名不能用汉字
                        //通过FileItem的方法实现文件上传
                        item.write(saveFile);
                        pic=pics;
                    }
                }
            }
            Forum forum = new Forum();
            forum.setFid(fid);
            ForumUser forumUser = new ForumUser();
            forumUser.setUserId(userId);
            CommentDao commentDao = new CommentDao();
            Comment comment = new Comment(-1,forum,forumUser,comContent,comTime,pic);
            commentDao.addComment(comment);
            msg.setState(true);
            msg.setMsg("发表成功");
            map.put("state",msg.isState());
            map.put("msg",msg.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setState(false);
            msg.setMsg("发表失败");
            map.put("state",msg.isState());
            map.put("msg",msg.getMsg()+","+e.getMessage());
        }
        String addCom = gson.toJson(map);
        response.getWriter().append(addCom);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
