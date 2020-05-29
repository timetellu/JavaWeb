package cn.timetell.web.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Timetellu on 2019/9/27.
 * Servlet的生命周期方法
 * ---------------------------------------
 * 切记不要忘记  在web.xml中配置url-pattern
 */
public class Servlet_method implements Servlet{

    /**
     * 初始化方法，在被创建时执行一次
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init.........");
    }

    /**
     * 获取SServlet配置对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * Servlet提供服务的方法
     * 执行多次
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service..................");
    }

    /**
     * 获取Servlet信息，如作者、版本号等
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 服务器正常关闭时执行的销毁方法
     */
    @Override
    public void destroy() {
        System.out.println("destroy............");
    }
}
