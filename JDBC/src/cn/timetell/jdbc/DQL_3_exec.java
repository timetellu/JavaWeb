package cn.timetell.jdbc;

import cn.timetell.domain.Person;
import java.sql.Timestamp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timetellu on 2019/9/24.
 * DQL -- resultSet
 * * 定义一个方法，查询emp表的数据将其封装为对象，然后装载集合，返回。
         1. 定义Person类
         2. 定义方法 public List<Emp> findAll(){}
         3. 实现方法 select * from person;
 */
public class DQL_3_exec {

    public List<Person> findall() {
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Person> list = null;
        try {
            //1. 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 定义sql
            String sql = "select * from person";
            //3.获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql://loacalhost:3306/test", "root", "123456");
            //4.获取执行sql的对象 Statement
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            //6.遍历结果集，封装对象，装载集合
            Person per = null;    //避免产生很多per的引用，占用栈内存
            list = new ArrayList<Person>();   //存储对象的集合
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int score = rs.getInt("score");
                Date birthday = rs.getDate("birthday");
                Timestamp cre_time = rs.getTimestamp("cre_time");

                per = new Person();
                per.setId(id);
                per.setName(name);
                per.setAge(age);
                per.setScore(score);
                per.setBirthday(birthday);
                per.setCre_time(cre_time);

                //装载集合
                list.add(per);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            //7. 释放资源
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
        return list;
    }

    public static void main(String[] args) {
        List<Person> obj_list = new DQL_3_exec().findall();
        for (Person li : obj_list) {
            System.out.println(li);
        }

    }
}

