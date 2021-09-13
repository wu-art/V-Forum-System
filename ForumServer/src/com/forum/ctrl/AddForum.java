package com.forum.ctrl;

import com.forum.dao.BaseDao;
import com.forum.dao.ForumDao;
import com.forum.dao.MessageDao;
import com.forum.entity.AreaType;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddForum")
//发表帖子
public class AddForum extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //3、接收FormData数据(文件上传)
        String path=getServletContext().getRealPath("/upload");
        FileItemFactory factory =new DiskFileItemFactory();
        ServletFileUpload upload =new ServletFileUpload(factory);
        String title=null;
        String typeId=null;
        String userId=null;
        String content=null;
        String releaseTime=null;
        String pic=null;
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (int i=0;i<items.size();i++) {
                //获取元素
                FileItem item = items.get(i);
                //判断元素类型，true普通表单元素，false文件元素
                if (item.isFormField()) {
                    //通过获取元素名称获取数据
                    String fieldName = item.getFieldName();
                    if (fieldName.equals("title")){
                        title=item.getString("utf-8");
                    }
                    if (fieldName.equals("typeId")){
                        typeId=item.getString("utf-8");
                    }
                    if (fieldName.equals("userId")){
                        userId=item.getString("utf-8");
                    }
                    if (fieldName.equals("content")){
                        content=item.getString("utf-8");
                    }
                    if (fieldName.equals("releaseTime")){
                        releaseTime=item.getString("utf-8");
                    }
                }else{
                    //文件元素
                    //通过上传路径以及文件的名称创建一个实例
                    String picStr=item.getName();
                    //头像可以为空
                    if (!picStr.equals("")){
                        //System.out.println(picStr);
                        //自定义后缀名
                        String hou=picStr.substring(picStr.lastIndexOf('.'),picStr.length());
                        String pics="pic"+System.currentTimeMillis()+hou;

                        File saveFile = new File(path, pics);//文件名不能用汉字
                        //通过FileItem的方法实现文件上传
                        item.write(saveFile);
                        pic=pics;
                    }
                }
            }
            ForumUser forumUser = new ForumUser();
            forumUser.setUserId(userId);
            AreaType areaType = new AreaType();
            areaType.setTypeId(Integer.parseInt(typeId));
            Forum forum = new Forum(-1,title,forumUser,areaType,releaseTime,content,"通过审核",pic);
            ForumDao forumDao = new ForumDao();
            boolean bool = forumDao.addForum(forum);
            Map map=new HashMap();
            Gson gson = new Gson();
            MessageDao msg = new MessageDao();
            if (bool){
                msg.setState(true);
                msg.setMsg("发表成功");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
            }else {
                msg.setState(false);
                msg.setMsg("发表失败");
                map.put("state",msg.isState());
                map.put("msg",msg.getMsg());
            }
            String addForum = gson.toJson(map);
            response.getWriter().append(addForum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
