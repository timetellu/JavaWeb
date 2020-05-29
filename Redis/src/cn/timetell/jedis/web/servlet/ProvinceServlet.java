package cn.timetell.jedis.web.servlet;

import cn.timetell.jedis.dao.ProvinceService;
import cn.timetell.jedis.dao.impl.ProvinceServiceImpl;
import cn.timetell.jedis.domain.Province;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 案例需求：
     1. 提供index.html页面，页面中有一个省份 下拉列表
     2. 当 页面加载完成后 发送ajax请求，加载所有省份
 */
@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * 不加redis 时
         */
    //        //调用service查询
    //        ProvinceService service = new ProvinceServiceImpl();
    //        List<Province> list = service.findAll();
    //
    //        //序列化list为json
    //        ObjectMapper mapper = new ObjectMapper();
    //        String json = mapper.writeValueAsString(list);


        /**
         * 使用redis缓存后
         */
        //调用service查询
        ProvinceService service = new ProvinceServiceImpl();
        String json = service.findJson();

        System.out.println(json);

        //响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
