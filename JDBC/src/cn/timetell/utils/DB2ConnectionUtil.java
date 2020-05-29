package cn.timetell.utils;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接工具
 */
public class DBConnectionUtil {

    public static void executeCreate(String url,String user,String password,String sql){
        Statement stmt = null;
        Connection conn = null;
        try {
            //1. 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            //2.获取Connection对象
            conn = DriverManager.getConnection(url, user, password);
            //3.获取执行sql的对象 Statement
            stmt = conn.createStatement();
            //5.执行sql
            int execute = stmt.executeUpdate(sql);   //影响的行数
            //6.处理结果
//            LOG.info("运行结果："+execute);
        } catch (ClassNotFoundException e) {
//            LOG.error("ClassNotFoundException,", e);
            throw new RuntimeException("ClassNotFoundException.", e);
        } catch (SQLException e) {
//            LOG.error("SQLException,", e);
            throw new RuntimeException("SQLException.", e);
        } catch (IllegalAccessException e) {
//            LOG.error("IllegalAccessException,", e);
            throw new RuntimeException("IllegalAccessException.", e);
        } catch (InstantiationException e) {
//            LOG.error("InstantiationException,", e);
            throw new RuntimeException("InstantiationException.", e);
        } finally {
            //7. 释放资源
            //避免空指针异常
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
//                    LOG.error("Failed to close stmt, ", e);
                    throw new RuntimeException("SQLException.", e);
                }
            }

            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
//                    LOG.error("Failed to close conn, ", e);
                    throw new RuntimeException("SQLException.", e);
                }
            }
        }
    }

    public static void main(String[] args) {
        //用于sbconnection的schema_name要写一个已存在的，保证连接成 功；也可以为空
        String url = "jdbc:mysql://localhost:3306";

        /**
         * 创建schema ，有返回值
         */
        //schema名字
        String schema_name = "hello2";
        String sql_create_schema = "create database "+schema_name+" default character set utf8 COLLATE utf8_general_ci";
        System.out.println(sql_create_schema);

        /**
         * 创建用户：无返回值
         * 授权用户：无返回值
         */
        //用户的用户名和密码
        String user = "timetell2";
        String password = "19950620";

        String sql_create_user = "CREATE USER '"+user+"'@'%' IDENTIFIED BY '"+password+"'";
        String sql_grant_user = "GRANT ALL ON "+schema_name+".* TO '"+user+"'@'%'";
        System.out.println(sql_create_user);
        System.out.println(sql_grant_user);

        // 数据库实例管理员用户名和密码
        DBConnectionUtil.executeCreate(url,"root","password",sql_create_schema);
        DBConnectionUtil.executeCreate(url,"root","password",sql_create_user);
        DBConnectionUtil.executeCreate(url,"root","password",sql_grant_user);
    }

//    private static final Logger LOG = LoggerFactory.getLogger(com.yeepay.cicd.env.initializer.db.common.utils.DBConnectionUtil.class);
}
