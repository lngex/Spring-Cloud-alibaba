package cn.lingex.basic.filter;


import cn.lingex.basic.basePojo.AuthenticationRedisValue;
import cn.lingex.basic.config.RedisService;
import cn.lingex.basic.constant.BusinessConstant;
import cn.lingex.basic.security.token.BaseAuthenticationToken;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基于token拦截器
 * 用于获取已认证的权限对象
 * 赋值到当前的SecurityContext中
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisService redisService;

    /**
     * 获取Redis中的令牌设置到SecurityContext中
     *
     * @param request     请求对象
     * @param response    响应对象
     * @param filterChain 过滤器操作
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String attribute = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotEmpty(attribute)) {
            AuthenticationRedisValue o = redisService.get(BusinessConstant.LOGIN_INFO + attribute);
            if (o != null) {
                long expire = redisService.getExpire(attribute);
                if (expire < 60 * 10) {
                    // TOKEN过期时间续期
                    redisService.set(attribute, o, BusinessConstant.TOKEN_EXPIRATION_TIME);
                }
                BaseAuthenticationToken clientCode = new BaseAuthenticationToken(o.getLoginUserDto().getUsername(), null, request.getHeader("clientCode"), o.getAuthorities());
                clientCode.setDetails(o.getLoginUserDto());
                SecurityContextHolder.getContext().setAuthentication(clientCode);
            }
        }
        filterChain.doFilter(request, response);
    }

}
