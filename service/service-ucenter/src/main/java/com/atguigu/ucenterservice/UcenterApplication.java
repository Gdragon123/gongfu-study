package com.atguigu.ucenterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/17 0017
 * @描述
 **/
@ComponentScan({"com.atguigu"})
@SpringBootApplication
@EnableDiscoveryClient  //启用nacos注册中心
@MapperScan(value = "com.atguigu.ucenterservice.mapper")
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }

}
