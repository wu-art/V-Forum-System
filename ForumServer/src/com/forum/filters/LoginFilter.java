package com.forum.filters;

import com.forum.dao.MessageDao;
import com.forum.entity.Admin;
import com.forum.entity.ForumUser;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter extends MessageDao implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse)resp;
        ForumUser forumUser=(ForumUser) request.getSession().getAttribute("user");
        Admin admin=(Admin) request.getSession().getAttribute("admin");
        if (forumUser != null || admin!=null){
            chain.doFilter(request, response);
        }else {
            Map map=new HashMap();
            setState(false);
            setMsg("请先登录");
            map.put("state",isState());
            map.put("msg",getMsg());
            Gson gson=new Gson();
            String loginRes = gson.toJson(map);
            response.getWriter().append(loginRes);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
