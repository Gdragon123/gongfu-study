package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/14 0014
 * @描述
 **/
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class) //指定要调用的服务名称，熔断回调
@Component
public interface VodClient {

    @DeleteMapping(value = "/vod/video/deletevideo/{videoId}") //指定调用的地址
    public R removeVideo(@PathVariable String videoId);

    @DeleteMapping(value = "/vod/video/deletebatch") //指定调用的地址
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);

}
