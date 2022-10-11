package com.atguigu.orderservice.service;

import com.atguigu.orderservice.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-07
 */
public interface TOrderService extends IService<TOrder> {

    /**
     *@描述 创建订单
     *@参数 课程ID,会员token
     *@返回值 订单ID
     *@创建人 ruansl
     *@创建时间 2021/7/7 0007
     *@修改人和其它信息
     */
    String saveOrder(String courseId, String memberIdByJwtToken);

    /**
     *@描述 根据订单号查询订单信息
     *@参数 订单号
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/7/7 0007
     *@修改人和其它信息
     */
    TOrder getByOrderNo(String orderId);
}
