package com.atguigu.cmsservice.service;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-15
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     *@描述 获取Banner分页列表
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/15 0015
     *@修改人和其它信息
     */
    void pageBanner(Page<CrmBanner> pageParam, Object o);

    /**
     *@描述
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/15 0015
     *@修改人和其它信息
     */
    CrmBanner getBannerById(String id);

    /**
     *@描述
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/15 0015
     *@修改人和其它信息
     */
    void saveBanner(CrmBanner banner);

    /**
     *@描述
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/15 0015
     *@修改人和其它信息
     */
    void updateBannerById(CrmBanner banner);

    /**
     *@描述
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/15 0015
     *@修改人和其它信息
     */
    void removeBannerById(String id);

    /**
     *@描述 
     *@参数 
     *@返回值 
     *@创建人 ruansl
     *@创建时间 2021/6/15 0015
     *@修改人和其它信息
     */
    List<CrmBanner> selectIndexList();
}
