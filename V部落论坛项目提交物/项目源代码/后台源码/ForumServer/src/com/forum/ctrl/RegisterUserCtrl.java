package com.forum.ctrl;

import com.forum.dao.BaseDao;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RegisterUserCtrl")
public class RegisterUserCtrl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收FormData数据(文件上传)
        String path=getServletContext().getRealPath("/upload");
        FileItemFactory factory =new DiskFileItemFactory();
        ServletFileUpload upload =new ServletFileUpload(factory);
        String userId=null;
        String userPwd=null;
        String nickName=null;
        String tel=null;
        String userPic=null;
        String regTime=null;
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (int i=0;i<items.size();i++) {
                //获取元素
                FileItem item = items.get(i);
                //判断元素类型，true普通表单元素，false文件元素
                if (item.isFormField()) {
                    //通过获取元素名称获取数据
                    String fieldName = item.getFieldName();
                    if (fieldName.equals("userId")){
                        userId=item.getString("utf-8");
                    }
                    if (fieldName.equals("userPwd")){
                        userPwd=item.getString("utf-8");
                    }
                    if (fieldName.equals("nickName")){
                        nickName=item.getString("utf-8");
                    }
                    if (fieldName.equals("tel")){
                        tel=item.getString("utf-8");
                    }
                    if (fieldName.equals("regTime")){
                        regTime=item.getString("utf-8");
                    }
                }else {
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
                        userPic=pics;
                    }
                }
            }
            SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date utilDate= f.parse(regTime);
            java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
            ForumUser forumUser = new ForumUser(userId,userPwd,nickName,tel,userPic,sqlDate,1);
            UserDao userDao = new UserDao();
            Map map=new HashMap();
            Gson gson = new Gson();
            MessageDao msg = new MessageDao();
            boolean register = userDao.register(forumUser);
            if (register){
                msg.setState(true);
                msg.setMsg("注册成功");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
            }else {
                msg.setState(false);
                msg.setMsg("注册失败");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
            }
            String regJson = gson.toJson(map);
            response.getWriter().append(regJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
