package com.atguigu.statisticsservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @创建人 ruansl
 * @创建时间 2021/7/7 0007
 * @描述
 **/
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @GetMapping(value = "/ucenterservice/ucentermember/countregister/{day}")
    public R registerCount(@PathVariable("day") String day);
}
