package cn.timetell.jedis.service;

import cn.timetell.jedis.domain.Province;

import java.util.List;

/**
 * Created by Timetellu on 2019/9/30.
 */
public interface ProvinceDao {
    public List<Province> findAll();
}
