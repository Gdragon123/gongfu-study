package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.rmi.ServerException;
import java.util.Map;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/17 0017
 * @描述
 **/
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String phoneNumber, String templateCode, Map<String, Object> param) {
        if(StringUtils.isEmpty(phoneNumber)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI5tN8AaoSkXAaBKMsJTki", "ACX0RoQXeqE5MBmpI3zjKBI7oyOU7r");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2021-06-18");
        request.setAction("SendSms");

        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "功夫学院在线教育网站");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
