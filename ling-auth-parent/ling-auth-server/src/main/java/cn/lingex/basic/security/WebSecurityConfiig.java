package cn.lingex.basic.security;


import cn.lingex.basic.filter.UsernamePasswordCodeAuthenticationFilter;
import cn.lingex.basic.security.handler.MyAuthenticationFailureHandler;
import cn.lingex.basic.security.handler.MySuccessHandler;
import cn.lingex.basic.security.provider.UsernamePasswordCodeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring-Security核心配置类
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
// 优先级
@Order(90)
@EnableWebSecurity
public class WebSecurityConfiig extends WebSecurityCommonConfig {

    @Autowired
    private MySuccessHandler mySuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private UsernamePasswordCodeAuthenticationFilter usernamePasswordAuthenticationFilter;

    @Autowired
    private UsernamePasswordCodeProvider usernamePasswordCodeProvider;


    /**
     * Security详细配置
     *
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        // 设置AuthenticationManager
        usernamePasswordAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        // 设置认证成功处理Handler
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(mySuccessHandler);
        // 设置认证失败处理Handler
        usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        // 配置Provider
        http.authenticationProvider(usernamePasswordCodeProvider)
                .addFilterAfter(usernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 密码编码器
     *
     * @return 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入AuthenticationManager
     *
     * @throws Exception 可能抛出的异常
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
