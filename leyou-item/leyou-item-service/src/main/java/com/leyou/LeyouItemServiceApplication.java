package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * item-service启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.item.mapper")        //接口的包扫描
public class LeyouItemServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(LeyouItemServiceApplication.class);
    }
}
