package cn.timetell.jedis.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid 实现JDBCUtils
 * Created by Timetellu on 2019/9/25.
 */
public class JDBCUtils {
    private static DataSource ds;

    static{
        try {
            Properties pro = new Properties();
            pro.load(new BufferedInputStream(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties")));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池的方法
     *
     */
    public static DataSource getDataSourse(){
        return ds;
    }


    /**
     * 获取连接的方法
     *
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
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
