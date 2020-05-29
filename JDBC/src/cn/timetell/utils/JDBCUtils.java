package cn.timetell.utils;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * 目的：抽取JDBC工具类 —— JDBCUtils，简化书写
 * 分析：
     1. 注册驱动也抽取
     2. 抽取一个方法获取连接对象
     * 需求：不想传递参数（麻烦），还得保证工具类的通用性。
     * 解决：配置文件
          jdbc.properties
          url=
          user=
          password=
 */
public class JDBCUtils {
     private static String url;
     private static String user;
     private static String password;
     private static String driver;

     /**
      * 文件的读取，只需要读取一次即可拿到这些值
      * 注册驱动
      */
     static{
          // 创建Properties集合类
          Properties pro = new Properties();
          //获取配置文件路径
          ClassLoader classLoader = JDBCUtils.class.getClassLoader();
          BufferedInputStream bis = new BufferedInputStream(classLoader.getResourceAsStream("jdbc.properties"));//获取流的简单方法
          //加载文件
          try {
               pro.load(bis);
          } catch (IOException e) {
               e.printStackTrace();
          }
          //获取数据，赋值
          url = pro.getProperty("url");         //上边已经定义过了，private static String url; 所以这里直接使用即可，不需要再重新定义，否则会报空指针异常
          user = pro.getProperty("user");
          password = pro.getProperty("password");
          driver = pro.getProperty("driver");
          //注册驱动
          try {
               Class.forName(driver);
          } catch (ClassNotFoundException e) {
               e.printStackTrace();
          }
          if(bis != null){
               try {
                    bis.close();
               } catch (IOException e) {
                    e.printStackTrace();
               }
          }
     }


     /**
      * 获取数据库连接对象
      * @return
      */
     public static Connection getConnection() throws SQLException {
          return DriverManager.getConnection(url,user,password);
     }


     /**
      *  针对DML，释放资源
      * @param stmt
      * @param conn
      */
     public static void resfree(Statement stmt,Connection conn){
          //避免空指针异常
          if(stmt != null){
               try {
                    stmt.close();
               } catch (SQLException e) {
                    e.printStackTrace();
               }
          }

          if(conn != null){
               try {
                    conn.close();
               } catch (SQLException e) {
                    e.printStackTrace();
               }
          }
     }


     /**
      *  针对DQL，释放资源
      * @param rs
      * @param stmt
      * @param conn
      */
     public static void resfree(ResultSet rs,Statement stmt,Connection conn){
          //避免空指针异常
          if(rs != null){
               try {
                    rs.close();
               } catch (SQLException e) {
                    e.printStackTrace();
               }
          }
          if(stmt != null){
               try {
                    stmt.close();
               } catch (SQLException e) {
                    e.printStackTrace();
               }
          }

          if(conn != null){
               try {
                    conn.close();
               } catch (SQLException e) {
                    e.printStackTrace();
               }
          }
     }

}
