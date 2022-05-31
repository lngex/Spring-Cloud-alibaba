package cn.lingex.basic.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 采用servlet拦截器解决访问跨域问题（貌似已经失效）
 *
 * @author 廖建波
 * @version 1.1
 */
@Component
public class HeadersCORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "access-control-allow-origin, " +
                        "Access-Control-Allow-Origin, " +
                        "authority, " +
                        "content-type, " +
                        "version-info, " +
                        "X-Requested-With, " +
                        "X-Firefox-Spdy, " +
                        "Connection, " +
                        "Authorization, " +
                        "accept, " +
                        "userclient, " +
                        "Origin");
        response.setHeader("Access-Control-Allow-Credentials", "false");
        // 浏览器会先发送一个试探请求OPTIONS,然后才会发送真正的请求，为了避免拦截器拦截两次请求，所以不能让OPTIONS请求通过
        if ("OPTIONS".equalsIgnoreCase(request1.getMethod())){
            response.setStatus(200);
        }
        chain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}