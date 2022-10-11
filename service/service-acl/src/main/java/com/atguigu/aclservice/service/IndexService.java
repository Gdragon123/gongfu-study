package com.atguigu.aclservice.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface IndexService {

    /**
     * 根据用户名获取用户登录信息
     * @param username
     * @return
     */
    Map<String, Object> getUserInfo(String username);

    /**
     * 根据用户名获取动态菜单（层级关系）
     * @param username
     * @return
     */
    List<JSONObject> getMenuWithLevel(String username);

    /**
     * 根据用户名获取所有权限菜单（非层级关系）
     * @param username
     * @return
     */
    List<String> getMenuWithoutLevel(String username);

}
