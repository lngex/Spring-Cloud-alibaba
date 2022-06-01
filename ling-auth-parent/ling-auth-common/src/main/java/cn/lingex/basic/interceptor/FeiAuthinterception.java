package cn.lingex.basic.interceptor;

import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 将当前请求请求头中的认证信息赋予下一请求中
 * @author liaojianbo
 */
@Component
public class FeiAuthinterception implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        /* 获取上下文对象 */
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        /* 获取请求 */
        HttpServletRequest request = requestAttributes.getRequest();
        /* 获取授权信息 */
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        /* 设置到feign请求中 */
        template.header(HttpHeaders.AUTHORIZATION, authorization);
    }
}