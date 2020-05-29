package cn.timetell.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Timetellu on 2019/9/23.
 * JDBC快速入门
 */
public class FirstJDBC {
    public static void main(String[] args) throws Exception {
        //1、导入驱动jar包
        //2、注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");   //F:\IntelliJ IDEA 14.1.7\JavaWeb\JDBC\libs\mysql-connector-java-8.0.15.jar!\META-INF\services\java.sql.Driver
        //3、获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
        //4、定义sql语句
        String sql = "update person set age = 54 where id = 5";
        //5、获取执行sql的对象
        Statement stm = conn.createStatement();
        //6、执行sql语句
        int result = stm.executeUpdate(sql);
        System.out.println(result);

        //7、释放对象
        stm.close();
        conn.close();
    }
}
