package com.forum.ctrl;

import com.forum.dao.BaseDao;
import com.forum.dao.MessageDao;
import com.forum.dao.UserDao;
import com.forum.entity.ForumUser;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class LoginUserCtrl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseDao baseDao = new BaseDao();
        Map map = baseDao.FromJson(request);
        String userId=(String) map.get("userId");
        String userPwd=(String) map.get("userPwd");
        String code=(String)map.get("code");
        String session_code=(String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //System.out.println(userId+"--"+userPwd);
        //每个验证码只用一次
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        UserDao userDao=new UserDao();
        MessageDao m=new MessageDao();
        Map map1=new HashMap();
        Gson gson=new Gson();
        try {
            ForumUser user = userDao.login(userId);
            if (session_code!=null&&!session_code.equals("") && code.equalsIgnoreCase(session_code)){
                if (user!=null &&userPwd.equals(user.getUserPwd())){
                    if (user.getUserState()==1){
                        request.getSession().setAttribute("user",user);
                        m.setState(true);
                        m.setMsg("登录成功");
                        map1.put("state",m.isState());
                        map1.put("msg",m.getMsg());
                        map1.put("user",user);
                    }else {
                        map1.put("state",false);
                        map1.put("msg","账号存在异常,请等待管理员反馈!");
                    }
                }else {
                    m.setState(false);
                    m.setMsg("用户不存在或密码错误");
                    map1.put("state",m.isState());
                    map1.put("msg",m.getMsg());
                }
            }else {
                m.setState(false);
                m.setMsg("验证码错误");
                map1.put("state",m.isState());
                map1.put("msg",m.getMsg());
            }

        } catch (Exception e) {
            e.printStackTrace();
            map1.put("state",false);
            map1.put("msg",e.getMessage());
        }
        String resLogin = gson.toJson(map1);
        response.getWriter().append(resLogin);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
