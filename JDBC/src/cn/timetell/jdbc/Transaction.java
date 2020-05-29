package cn.timetell.jdbc;

import cn.timetell.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Timetellu on 2019/9/25.
 * 事务
 * 使用Connection对象来管理事务
 *      开启事务：setAutoCommit(boolean autoCommit) ：调用该方法设置参数为false，即开启事务
 *               在执行sql之前开启事务
 *      提交事务：commit()
 *              当所有sql都执行完提交事务
 *      回滚事务：rollback()
 *              在catch中回滚事务
 */
public class Transaction {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);

            //2.定义sql
            //2.1 张三 - 500
            String sql1 = "update login set balance = balance - ? where id = ?";
            //2.2 李四 + 500
            String sql2 = "update login set balance = balance + ? where id = ?";
            //3.获取执行sql对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            //4. 设置参数
            pstmt1.setDouble(1,500);
            pstmt1.setInt(2,1);

            pstmt2.setDouble(1,500);
            pstmt2.setInt(2,2);
            //5.执行sql
            pstmt1.executeUpdate();
            // 手动制造异常
            int i = 3/0;

            pstmt2.executeUpdate();
            //提交事务
            conn.commit();
        } catch (Exception e) {       //这个异常要大一点，只要有异常就回滚
            //事务回滚
            try {
                if(conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.resfree(pstmt1,conn);
            JDBCUtils.resfree(pstmt2,null);
        }
    }

}
