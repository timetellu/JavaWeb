package cn.timetell.jdbc;

import java.sql.*;

/**
 * Created by Timetellu on 2019/9/24.
 * DQL -- resultSet
 * 使用步骤：
     1. 游标向下移动一行
     2. 判断是否有数据
     3. 获取数据
 */
public class DQL_2_std {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        //1. 注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 定义sql
            String sql = "select * from person";
            //3.获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql:///test", "root", "123456");
            //4.获取执行sql的对象 Statement
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            //6.处理结果
            while(rs.next()){    //next()执行2个操作：①向下移动一行  ②判断是否在最后一行
                //循环判断游标是否是最后一行末尾
                //获取数据
                int id = rs.getInt(1);
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Timestamp cre_time = rs.getTimestamp("cre_time");
                System.out.println(id+"---"+name+"---"+age+"---"+cre_time);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7. 释放资源
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

}

