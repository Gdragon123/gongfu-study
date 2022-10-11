package com.atguigu.orderservice.client;

import com.atguigu.commonutils.vo.UcenterMember;
import org.springframework.stereotype.Component;

/**
 * @创建人 ruansl
 * @创建时间 2021/7/7 0007
 * @描述
 **/
@Component
public class UcenterDegradeFeignClient implements UcenterClient{
    @Override
    public UcenterMember getInfo(String id) {
        return new UcenterMember();
    }
}
