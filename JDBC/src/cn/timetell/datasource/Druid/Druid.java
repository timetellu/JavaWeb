package cn.timetell.datasource.Druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Timetellu on 2019/9/25.
 */
public class Druid {
    public static void main(String[] args) throws Exception {
        Properties pro = new Properties();
        InputStream is = Druid.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);
        // 获取数据库连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        // 获取连接
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
