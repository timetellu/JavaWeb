package cn.timetell.ajax_json.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Timetellu on 2019/9/29.
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名
        String username = request.getParameter("username");

        /**
         * 服务器响应的数据，在客户端使用时，要想当做json数据格式使用
         * 第二种解决方案：在服务器端设置响应的 MIME类型（数据格式；编码格式） 为json，因为客户端会自动根据服务端设置的类型进行解析json
         */
        response.setContentType("application/json;charset=utf-8");

        //期望服务器响应回的数据格式：{"userExsit":true,"msg":"此用户名太受欢迎,请更换一个"}
        //                         {"userExsit":false,"msg":"用户名可用"}
        Map<String,Object> map = new HashMap<String,Object>();
        if("tom".equals(username)){             //此处应调用数据库查询校验
            //存在
            map.put("userExist",true);
            map.put("msg","此用户名太受欢迎,请更换一个");
        }else{
            //不存在
            map.put("userExist",false);
            map.put("msg","用户名可用");
        }
        // 将map（obj）对象转换为JSON字符串，并将json数据填充到字符输出流中，传递给客户端
        //writeValue(参数1，obj)
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
