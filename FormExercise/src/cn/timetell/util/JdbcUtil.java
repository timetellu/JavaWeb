package cn.timetell.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Timetellu on 2019/9/28.
 */
public class JdbcUtil {
    public static DataSource ds;

    static{
        try {
            Properties pro = new Properties();
            pro.load(new BufferedInputStream(JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties")));
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
    public static DataSource getDS(){
        return ds;
    }

    /**
     * 获取连接的方法
     *
     */
    public static Connection getConn() throws SQLException {
        return ds.getConnection();
    }
}
