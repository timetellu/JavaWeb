package cn.timetell.jdbc;

import cn.timetell.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Timetellu on 2019/9/25.
 * 步骤：
     1. 导入驱动jar包 mysql-connector-java-5.1.37-bin.jar
     2. 注册驱动
     3. 获取数据库连接对象 Connection
     4. 定义sql
         * 注意：sql的参数使用？作为占位符。 如：select * from user where username = ? and password = ?;
     5. 获取执行sql语句的对象 PreparedStatement  Connection.prepareStatement(String sql)
     6. 给？赋值：
         * 方法： setXxx(参数1,参数2)
         * 参数1：？的位置编号 从1 开始
         * 参数2：？的值
     7. 执行sql，接受返回结果，不需要传递sql语句
     8. 处理结果
     9. 释放资源
 */
public class DQL_PreparedStatement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input username: ");
        String username = scanner.next();
        System.out.println("please input password: ");
        String password = scanner.next();
        boolean flag = login(username, password);
        if(flag){
            System.out.println("登录成功");
        }else{
            System.out.println("用户名或密码错误");
        }
    }

    public static boolean login(String username,String password){
        if(username == null || password == null){
            return false;
        }
        Connection conn = null;
        PreparedStatement ppstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from login where name = ? and password = ?";
            ppstmt = conn.prepareStatement(sql);
            ppstmt.setString(1,username);
            ppstmt.setString(2,password);
            rs = ppstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.resfree(rs,ppstmt,conn);
        }
        return false;
    }
}
