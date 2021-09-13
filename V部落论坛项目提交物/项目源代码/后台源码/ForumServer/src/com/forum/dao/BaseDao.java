package com.forum.dao;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/*
 *处理加载驱动和获取连接两步的方法：
 * 1、可以使用工具类,让其继承此工具类,例如此BaseDao
 * 2、可将这两步写在一个静态类中，属性用静态属性public static fina,调用时用类去调用；
 */
//数据库连接的工具类
public class BaseDao {
    Connection conn;
    //获取连接
    public Connection getConnection() throws Exception {
        if (conn == null){
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
        }
        return conn;
    }
    //关闭连接
    /*
     *如果有一个方法，可以去关闭连接;如果有多个功能方法，则可以选择不关闭连接,可以重用连接，提升效率；
     */
    public void closeConn() throws SQLException {
        if (conn != null){
            conn.close();
        }
    }

    public Map FromJson(HttpServletRequest request) throws IOException {
        //接收 json数据
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String loginJsonData=sb.toString();
        //1、可使用ForumUser实体接收
        //2、使用map接收
        Gson gson=new Gson();
        Map map=gson.fromJson(loginJsonData,Map.class);
        return map;
    }
}
