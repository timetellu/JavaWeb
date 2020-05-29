package cn.timetell.jedis.service.impl;

import cn.timetell.jedis.domain.Province;
import cn.timetell.jedis.service.ProvinceDao;
import cn.timetell.jedis.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by Timetellu on 2019/9/30.
 */
public class ProvinceDaoImpl implements ProvinceDao{

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSourse());



    @Override
    public List<Province> findAll() {
        String sql = "select * from province";
        List<Province> list = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return list;

    }
}
