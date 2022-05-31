package cn.lingex.basic.security.handler;

import cn.lingex.basic.result.JSONResult;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 认证失败处理器
 * 登录失败（用户名或者密码错误）
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            JSONResult<String> failure = JSONResult.failure(exception.getMessage());
            outputStream.write(JSONObject.toJSONString(failure).getBytes(StandardCharsets.UTF_8));
        }
    }
}
