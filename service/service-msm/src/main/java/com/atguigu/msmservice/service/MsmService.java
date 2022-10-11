package com.atguigu.msmservice.service;

import java.util.Map;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/17 0017
 * @描述
 **/
public interface MsmService {

    /**
     *@描述 调用阿里云的API发送短信
     *@参数 phone：手机号码，templateCode：模板CODE，param：参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/17 0017
     *@修改人和其它信息
     */
    boolean send(String phone, String templateCode, Map<String, Object> param);
}
