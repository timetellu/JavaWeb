package cn.timetell.dao;

import cn.timetell.util.JdbcUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Timetellu on 2019/9/28.
 */
public class TestDao {
    public static void main(String[] args) {
        JdbcTemplate temp = new JdbcTemplate(JdbcUtil.getDS());
//        String sql = "select * from form where username = ? and password = ?";
//
//        User user = temp.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), "wuji", "123");
//        System.out.println(user);
//        System.out.println("-----");

        String sql = "update form set name = ?,gender = ? where id = ?";
        int update = temp.update(sql, "赵敏", "女", 1);
        System.out.println(update);
    }

}
