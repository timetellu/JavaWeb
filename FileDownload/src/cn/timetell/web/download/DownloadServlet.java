package cn.timetell.web.download;

import cn.timetell.web.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Timetellu on 2019/9/28.
 */
@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数，文件名称
        String filename = request.getParameter("filename");
        //2.使用字节输入流加载文件进内存
            //2.1找到文件服务器路径
        ServletContext servletContext = this.getServletContext();   //获取上下文对象
        String realPath = servletContext.getRealPath("/img/" + filename);
        //2.2用字节流关联
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(realPath));

        //3.设置response的响应头
            //3.1设置响应头类型：content-type
        String mimeType = servletContext.getMimeType(filename);    //MimeType：大类型 / 小类型（获取文件对应的类型）
        response.setHeader("content-type",mimeType);
        
        //解决中文文件名问题
            //1.获取user-agent请求头
        String agent = request.getHeader("user-agent");
            //2.使用工具类方法编码文件名
        filename = DownLoadUtils.getFileName(agent, filename);

            //3.2设置响应头打开方式:content-disposition
        response.setHeader("content-disposition","attachment;filename="+filename);

        //4.将输入流的数据写出到输出流中
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while((len = bis.read(buff)) != -1 ){
            sos.write(buff,0,len);
        }

        bis.close();   //bis is not null  always true

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
