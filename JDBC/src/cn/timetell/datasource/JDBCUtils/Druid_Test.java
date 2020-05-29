package cn.timetell.datasource.JDBCUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * Created by Timetellu on 2019/9/25.
 */
public class Druid_Test {
    public static void main(String[] args) throws Exception {
        Connection conn = JDBCUtils.getConnection();

        String sql = "INSERT INTO login VALUES(NULL,?,?,?)";
        PreparedStatement ppstmt = conn.prepareStatement(sql);
        ppstmt.setString(1,"赵六");
        ppstmt.setInt(2,123);
        ppstmt.setInt(3,888);

        int count = ppstmt.executeUpdate();
        System.out.println(count);
    }
}
