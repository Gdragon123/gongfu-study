package com.atguigu.statisticsservice.service;

import com.atguigu.statisticsservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-07
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     *@描述 
     *@参数 
     *@返回值 
     *@创建人 ruansl
     *@创建时间 2021/7/7 0007
     *@修改人和其它信息
     */
    void createStatisticsByDay(String day);

    /**
     *@描述 
     *@参数
     *@返回值 
     *@创建人 ruansl
     *@创建时间 2021/7/7 0007
     *@修改人和其它信息
     */
    Map<String, Object> getChartData(String begin, String end, String type);
}
