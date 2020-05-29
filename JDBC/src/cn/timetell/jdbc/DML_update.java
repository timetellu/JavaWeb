package cn.timetell.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Timetellu on 2019/9/24.
 * update
 */
public class DML_update {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        //1. 注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 定义sql
            String sql = "select * from test";
            //3.获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql:///test", "root", "123456");
            //4.获取执行sql的对象 Statement
            stmt = conn.createStatement();
            //5.执行sql
            int count = stmt.executeUpdate(sql);//影响的行数
            //6.处理结果
            System.out.println("影响的行数："+count);
            if(count > 0){
                System.out.println("修改成功！");
            }else{
                System.out.println("修改失败！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7. 释放资源
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
    }

}

