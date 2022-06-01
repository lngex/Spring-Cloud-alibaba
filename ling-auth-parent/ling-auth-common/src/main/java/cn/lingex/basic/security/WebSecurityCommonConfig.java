package cn.lingex.basic.security;


import cn.lingex.basic.filter.TokenFilter;
import cn.lingex.basic.security.handler.AuthorizationAccessDeniedHandler;
import cn.lingex.basic.security.handler.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Spring-Security核心配置类
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class WebSecurityCommonConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private TokenFilter tokenFilter;

    @Autowired
    private AuthorizationAccessDeniedHandler myAccessDeniedHandler;


    /**
     * Security详细配置
     *
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 放行
                .antMatchers(
                        "/pc/login",
                        "/doc.html",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/upload/**",
                        "/webjars/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 拦截其他请求
                .anyRequest().authenticated()
                // 开启表单登录
                .and().formLogin()
                // 登出放行
                .and().logout().permitAll()
                // 跨域伪造
                .and()// 开启跨域配置
                .cors()
                .configurationSource(corsConfigurationSource()).and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 自定义授权失败处理
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                .authenticationEntryPoint(myAuthenticationEntryPoint);
        // 自定义Token拦截器
        http.addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 解决跨域问题
     *
     * @return CorsConfigurationSource
     */
    private CorsConfigurationSource corsConfigurationSource() {
        // 提供CorsConfiguration实例，并配置跨域信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfiguration.setAllowCredentials(false);
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
