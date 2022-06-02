package cn.lingex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 *
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.lingex.mapper")
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserApp {
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class,args);
    }
}
