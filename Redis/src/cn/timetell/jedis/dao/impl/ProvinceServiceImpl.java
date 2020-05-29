package cn.timetell.jedis.dao.impl;

import cn.timetell.jedis.dao.ProvinceService;
import cn.timetell.jedis.domain.Province;
import cn.timetell.jedis.service.ProvinceDao;
import cn.timetell.jedis.service.impl.ProvinceDaoImpl;
import cn.timetell.jedis.util.JedisPoolUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Timetellu on 2019/9/30.
 */
public class ProvinceServiceImpl implements ProvinceService {

    private ProvinceDao dao = new ProvinceDaoImpl();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    /*
    使用redis缓存
    1、先从redis中查询数据
    2、如果没有，从数据库中查询，将数据存入redis，返回数据
    3、如果有，直接返回数据
     */
    @Override
    public String findJson() {
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");

        if(province_json == null || province_json.length() == 0){
            System.out.println("redis中没有数据，查询数据库");
            List<Province> list = dao.findAll();   //从数据库中查询
            //将list序列化为json格式，进行传输
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("province",province_json);
        }else{
            System.out.println("redis中有数据，直接返回");
        }
        return province_json;
    }
}
