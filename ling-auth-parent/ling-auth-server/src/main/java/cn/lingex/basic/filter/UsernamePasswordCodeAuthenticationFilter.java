package cn.lingex.basic.filter;


import cn.lingex.basic.constant.BusinessConstant;
import cn.lingex.basic.security.token.UsernamePasswordCodeToken;
import cn.lingex.basic.utils.Example;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * 用户名密码验证码登录
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class UsernamePasswordCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    public static final String SPRING_SECURITY_FORM_CODE_KEY = "code";
    @Autowired
    private AuthenticationManager authenticationManager;

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;

    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;

    private String codeParameter = SPRING_SECURITY_FORM_CODE_KEY;

    private boolean postOnly = true;


    public UsernamePasswordCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/pc/login", "POST"));
    }


    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        response.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 浏览器会先发送一个试探请求OPTIONS,然后才会发送真正的请求，为了避免拦截器拦截两次请求，所以不能让OPTIONS请求通过
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(200);
        }
        if (this.postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        byte[] bytes = Example.readAsBytes(request);
        String s = new String(bytes, StandardCharsets.UTF_8);
        if (!StringUtils.hasText(s)) {
            throw new UsernameNotFoundException("请填写登录信息");
        }
        JSONObject jsonObject = JSONObject.parseObject(s);
        UsernamePasswordCodeToken authRequest = new UsernamePasswordCodeToken(
                jsonObject.getString(usernameParameter),
                jsonObject.getString(passwordParameter),
                jsonObject.getString(codeParameter),
                request.getHeader(BusinessConstant.CLIENT_CODE)
        );
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return authenticationManager.authenticate(authRequest);
    }

    /**
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request     that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details
     *                    set
     */
    protected void setDetails(HttpServletRequest request, UsernamePasswordCodeToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

}

