package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/14 0014
 * @描述
 **/
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("time out");
    }

    @Override
    public R removeVideoList(List<String> videoIdList) {
        return R.error().message("time out");
    }
}
