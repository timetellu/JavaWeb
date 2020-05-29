package cn.timetell.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Timetellu on 2019/9/28.
 * 在服务器中的Servlet判断是否有一个名为lastTime的cookie
     1. 有：不是第一次访问
        1. 响应数据：欢迎回来，您上次访问时间为:2019年9月28日10:46:34
        2. 写回Cookie：lastTime=2019年9月28日10:59:59
     2. 没有：是第一次访问
        1. 响应数据：您好，欢迎您首次访问
        2. 写回Cookie：lastTime=2019年9月28日10:46:34
     注意： 在tomcat 8 之后，cookie支持中文数据。
            但是，特殊字符（如空格）还是不支持，建议使用URL编码存储，URL解码解析
 */
@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的消息体的数据格式以及编码
        response.setContentType("text/html;charset=utf-8");

        //1.获取所有Cookie
        Cookie[] cookies = request.getCookies();
        boolean flag = false;     //没有cookie为lastTime

        //有ccokie
        if(cookies != null && cookies.length > 0){
            //2.遍历cookie数组
            for (Cookie cookie : cookies) {
                //3.获取cookie的名称
                String name = cookie.getName();
                //4.判断名称是否是：lastTime
                if("lastTime".equals(name)){   //有该Cookie，不是第一次访问
                    flag = true;    //有lastTime的cookie

                //①有cookie，先响应数据
                    //获取Cookie的value，时间
                    String value = cookie.getValue();
                    //URL解码：
                    value = URLDecoder.decode(value, "utf-8");
                    response.getWriter().write("<h1>欢迎回来，您上次访问时间为:"+value+"</h1>");

                //②响应完数据后，设置更新Cookie新的value
                    //获取当前时间的字符串，重新设置Cookie的值，重新发送cookie
                    Date date  = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");   //有空格，直接传输会报异常
                    String str_date = sdf.format(date);
                    //URL编码
                    str_date = URLEncoder.encode(str_date, "utf-8");
                    cookie.setValue(str_date);
                    //设置cookie的存活时间
                    cookie.setMaxAge(60 * 60 * 24 * 30);//一个月
                    response.addCookie(cookie);

                    break;

                }
            }

        }

        //没有cookie，第一次访问
        if(cookies == null || cookies.length == 0 || flag == false){
            //获取当前时间的字符串，重新设置Cookie的value，发送cookie
            Date date  = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str_date = sdf.format(date);
            //URL编码
            str_date = URLEncoder.encode(str_date,"utf-8");

            Cookie cookie = new Cookie("lastTime",str_date);
            //设置cookie的存活时间
            cookie.setMaxAge(60 * 60 * 24 * 30);//一个月
            response.addCookie(cookie);

            response.getWriter().write("<h1>您好，欢迎您首次访问</h1>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
