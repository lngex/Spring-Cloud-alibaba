package cn.lingex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 库存服务
 *
 * @author LiaoJianbo
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.lingex.mapper")
public class StockApp {
    public static void main(String[] args) {
        SpringApplication.run(StockApp.class,args);
    }
}
