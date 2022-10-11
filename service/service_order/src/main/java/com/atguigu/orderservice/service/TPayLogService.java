package com.atguigu.orderservice.service;

import com.atguigu.orderservice.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-07
 */
public interface TPayLogService extends IService<TPayLog> {

    /**
     *@描述 根据订单号生成支付参数
     *@参数 订单号
     *@返回值 微信需要的支付参数集合
     *@创建人 ruansl
     *@创建时间 2021/7/7 0007
     *@修改人和其它信息
     */
    Map createNative(String orderNo);

    /**
     *@描述 根据订单号查询微信订单支付状态
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/7/7 0007
     *@修改人和其它信息
     */
    Map<String, String> queryPayStatus(String orderNo);

    /**
     *@描述 修改本地订单状态
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/7/7 0007
     *@修改人和其它信息
     */
    void updateOrderStatus(Map<String, String> map);
}
