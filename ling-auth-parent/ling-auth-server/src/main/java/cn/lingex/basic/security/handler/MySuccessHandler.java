package cn.lingex.basic.security.handler;


import cn.lingex.basic.basePojo.AuthenticationRedisValue;
import cn.lingex.basic.basePojo.LoginUserDto;
import cn.lingex.basic.config.RedisService;
import cn.lingex.basic.constant.AuthBusinessConstant;
import cn.lingex.basic.result.JSONResult;
import cn.lingex.basic.security.authority.BusinessAuthority;
import cn.lingex.basic.utils.StrUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 认证成功的结果处理
 * 登录成功
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class MySuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RedisService redisService;

    /**
     * 认证成功后跳转
     *
     * @param request        请求对象
     * @param response       响应对象
     * @param authentication the <tt>Authentication</tt> object which was created during
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        String uuid = StrUtils.uuid();
        // 获取用户登录信息并生成token
        LoginUserDto loginUserDto = (LoginUserDto) authentication.getDetails();
        loginUserDto.setToken(uuid);
        AuthenticationRedisValue authenticationRedisValue = new AuthenticationRedisValue();
        // 获取用户权限
        List<BusinessAuthority> authorities = (List<BusinessAuthority>) authentication.getAuthorities();
        // 封装到 authenticationRedisValue 储存到 redis 中
        authenticationRedisValue.setLoginUserDto(loginUserDto).setAuthorities(authorities);
        redisService.set(AuthBusinessConstant.LOGIN_INFO + uuid, authenticationRedisValue, AuthBusinessConstant.TOKEN_EXPIRATION_TIME);
        try (OutputStream outputStream = response.getOutputStream()) {
            // 写出用户登录信息
            outputStream.write(JSONObject.toJSONString(JSONResult.success(loginUserDto)).getBytes(StandardCharsets.UTF_8));
        }
    }
}
