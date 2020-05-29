package cn.timetell.jedis.dao;

import cn.timetell.jedis.domain.Province;

import java.util.List;

/**
 * Created by Timetellu on 2019/9/30.
 */
public interface ProvinceService {
    public List<Province> findAll();

    public String findJson();
}


