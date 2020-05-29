package cn.timetell.domain;

// import java.security.Timestamp;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Timetellu on 2019/9/24.
 * 封装person表数据的JavaBean
 */
public class Person {
    private Integer id;
    private String name;
    private Integer age;
    private Integer score;
    private Date birthday;       //这里使用java.sql.Timestamp，而不是java.security.Timestamp
    private Timestamp cre_time;

    public Person() {
    }

    public Person(Integer id, String name, Integer age, Integer score, Date birthday, Timestamp cre_time) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.birthday = birthday;
        this.cre_time = cre_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Timestamp getCre_time() {
        return cre_time;
    }

    public void setCre_time(Timestamp cre_time) {
        this.cre_time = cre_time;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", birthday=" + birthday +
                ", cre_time=" + cre_time +
                '}';
    }
}
