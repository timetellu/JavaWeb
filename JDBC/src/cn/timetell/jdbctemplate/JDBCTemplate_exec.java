package cn.timetell.jdbctemplate;

import cn.timetell.datasource.JDBCUtils.JDBCUtils;
import cn.timetell.domain.Person;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by Timetellu on 2019/9/25.
 * 调用JdbcTemplate的方法来完成CRUD的操作  —— 练习
 * * 需求：
         1. 修改1号数据
         2. 添加一条记录
         3. 删除刚才添加的记录
         4. 查询id为1的记录，将其封装为Map集合
         5. 查询所有记录，将其封装为List
         6. 查询所有记录，将其封装为Emp对象的List集合
         7. 查询总记录数
 */
public class JDBCTemplate_exec {
    //1. 获取JDBCTemplate对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSourse());

    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSourse());
        //2. 定义sql
        String sql = "create database test2";
        //3. 执行sql
        int result = template.update(sql);
        System.out.println(result);
    }
    /**
     * 1. 修改1号数据的：update()
     */
    @Test
    public void test1(){
        //2. 定义sql
        String sql = "update person set score = 22 where id = 4";
        //3. 执行sql
        int count = template.update(sql);
        System.out.println(count);
    }

    @Test
    public void testCreateSchema(){
        //2. 定义sql
        String sql = "create database test2";
        //3. 执行sql
        int count = template.update(sql);
        System.out.println(count);
    }

    /**
     * 2. 添加一条记录：update()
     */
    @Test
    public void test2(){
        String sql = "insert into person(name,age) values(?,?)";
        int count = template.update(sql, "郭靖", 56);
        System.out.println(count);

    }

    /**
     * 3.删除刚才添加的记录：update()
     */
    @Test
    public void test3(){
        String sql = "delete from person where id = ?";
        int count = template.update(sql, 5);
        System.out.println(count);
    }

    /**
     * 4.查询id为1001的记录，将其封装为Map集合：queryForMap()
     * 注意：这个方法查询的结果集长度只能是1
     */
    @Test
    public void test4() {
        String sql = "select * from person where id = ?";
//        Map<String, Object> map = template.queryForMap(sql, 4, 7);   //注意这个方法的结果集长度只能为1，Incorrect result size: expected 1, actual 2
        Map<String, Object> map = template.queryForMap(sql, 7);
        System.out.println(map);
    }

    /**
     * 5. 查询所有记录，将其封装为List: queryForList()
    */
    @Test
    public void test5() {
        String sql = "select * from person";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }

    /**
     * 6. 查询所有记录，将其封装为Person对象的List集合:query()，封装为javabean对象
     */
    @Test
    public void test6(){
        String sql = "select * from person";
        List<Person> list = template.query(sql, new BeanPropertyRowMapper<Person>(Person.class));
        for (Person per : list) {
            System.out.println(per);
        }
    }

    /**
     * 7. 查询总记录数:  queryForObject，封装为对象， 一般用于聚合函数的查询
     */
    @Test
    public void test7(){
        String sql = "select count(id) from person";
        Long total = template.queryForObject(sql, Long.class);
        System.out.println(total);
    }



}