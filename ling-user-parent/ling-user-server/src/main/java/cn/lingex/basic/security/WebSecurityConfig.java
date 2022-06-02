package cn.lingex.basic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * WebSecurity配置
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @CreateTime: 2022-06-02  09:13
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
@Order(90)
public class WebSecurityConfig extends WebSecurityCommonConfig{

    /**
     * Security详细配置
     *
     * @param http http权限
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }


}
