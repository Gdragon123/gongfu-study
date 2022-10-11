package com.atguigu.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/15 0015
 * @描述
 **/
@MapperScan("com.atguigu.cmsservice.mapper")
@ComponentScan({"com.atguigu"})
@EnableDiscoveryClient
@SpringBootApplication
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class);
    }

}
