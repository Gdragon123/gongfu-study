package com.atguigu.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @创建人 ruansl
 * @创建时间 2021/7/7 0007
 * @描述
 **/
@Component
public class OrderDegradeFeignClient implements OrderClient{
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        return false;
    }
}
