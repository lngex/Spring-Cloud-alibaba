package cn.lingex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 权限服务启动类
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class,args);
    }
}
