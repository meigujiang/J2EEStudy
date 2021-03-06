package run.liuliuqiu.j2ee.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description:
 * @author: Tong
 * @date: 2019-12-16 21:13
 */
public class EncodingFilter implements Filter {

    private String encoding; // 定义变量接收初始化的值

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 设置字符编码链锁
        HttpServletRequest hsr = (HttpServletRequest)request;
        request.setCharacterEncoding(this.encoding);
        if(hsr.getMethod().equals("POST")){
            response.setContentType("application/json");
        }
        response.setCharacterEncoding(this.encoding);
        chain.doFilter(request, response);
        response.setCharacterEncoding(this.encoding);
    }

    // 初始化
    public void init(FilterConfig config) throws ServletException {
        // 接收web.xml配置文件中的初始参数
        this.encoding = config.getInitParameter("CharsetEncoding");

    }

}