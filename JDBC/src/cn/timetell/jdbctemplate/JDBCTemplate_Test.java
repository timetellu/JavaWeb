package cn.timetell.jdbctemplate;

import cn.timetell.datasource.JDBCUtils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by Timetellu on 2019/9/25.
 * 调用JdbcTemplate的方法来完成CRUD的操作  —— 入门
 * -- update():执行DML语句。增、删、改语句
 * -- query()
*      queryForMap():查询结果将结果集封装为map集合，将列名作为key，将值作为value 将这条记录封装为一个map集合
*               注意：这个方法查询的结果集长度只能是1
*      queryForList():查询结果将结果集封装为list集合
*               注意：将每一条记录封装为一个Map集合，再将Map集合装载到List集合中
*      query():查询结果，将结果封装为JavaBean对象
*          query的参数：RowMapper
*              一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
*              new BeanPropertyRowMapper<类型>(类型.class)
*      queryForObject：查询结果，将结果封装为对象
*          一般用于聚合函数的查询
 */
public class JDBCTemplate_Test {

    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSourse());   //注意是小写的Jdbc
//        String sql = "update login set password = ? where id = ? ";
//        int count = template.update(sql, 123789, 3);
//        System.out.println(count);
        String sql = "select * from form";
        List list = template.queryForList(sql);
        System.out.println(list);

    }
}
