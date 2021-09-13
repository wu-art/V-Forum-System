package com.forum.ctrl;

import com.forum.dao.MessageDao;
import com.forum.dao.UserDao;
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

@WebServlet(name = "UpdateUserPic")
public class UpdateUserPic extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收FormData数据(文件上传)
        String path=getServletContext().getRealPath("/upload");
        FileItemFactory factory =new DiskFileItemFactory();
        ServletFileUpload upload =new ServletFileUpload(factory);
        String userPic=null;
        String userId=null;
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (int i=0;i<items.size();i++) {
                //获取元素
                FileItem item = items.get(i);
                //判断元素类型，true普通表单元素，false文件元素
                if (item.isFormField()){
                    String fieldName = item.getFieldName();
                    if (fieldName.equals("userId")){
                        userId=item.getString("utf-8");
                    }
                }else {
                    String pic=item.getName();
                    if (!pic.equals("")){
                        //自定义后缀名
                        String hou=pic.substring(pic.lastIndexOf('.'),pic.length());
                        String pics="pic"+System.currentTimeMillis()+hou;

                        File saveFile = new File(path, pics);//文件名不能用汉字
                        //通过FileItem的方法实现文件上传
                        item.write(saveFile);
                        userPic=pics;
                    }
                }
            }
            ForumUser forumUser = new ForumUser();
            forumUser.setUserId(userId);
            forumUser.setUserPic(userPic);
            UserDao userDao = new UserDao();
            Map map=new HashMap();
            Gson gson = new Gson();
            MessageDao msg = new MessageDao();
            userDao.updatePic(userPic,userId);

            msg.setState(true);
            msg.setMsg("修改成功");
            map.put("state",msg.isState());
            map.put("msg",msg.getMsg());

            String upJson = gson.toJson(map);
            response.getWriter().append(upJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
