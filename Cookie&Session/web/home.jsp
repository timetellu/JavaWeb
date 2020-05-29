<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%
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
        out.write("<h1>欢迎回来，您上次访问时间为:"+value+"</h1>");

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

    out.write("<h1>您好，欢迎您首次访问</h1>");
  }
%>

</body>
</html>
