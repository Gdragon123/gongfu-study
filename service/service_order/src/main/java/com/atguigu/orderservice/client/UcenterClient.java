package com.atguigu.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @创建人 ruansl
 * @创建时间 2021/7/7 0007
 * @描述
 **/
@Component
@FeignClient(name = "service-ucenter", fallback = UcenterDegradeFeignClient.class)
public interface UcenterClient {
    //根据ID获取用户信息
    @PostMapping("/ucenterservice/ucentermember/getInfoUc/{id}")
    public com.atguigu.commonutils.vo.UcenterMember getInfo(@PathVariable String id);
}
