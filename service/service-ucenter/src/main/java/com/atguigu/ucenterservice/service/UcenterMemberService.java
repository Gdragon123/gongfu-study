package com.atguigu.ucenterservice.service;

import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.vo.LoginInfoVo;
import com.atguigu.ucenterservice.vo.LoginVo;
import com.atguigu.ucenterservice.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-17
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     *@描述 用户登录
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/17 0017
     *@修改人和其它信息
     */
    String login(LoginVo loginVo);

    /**
     *@描述 用户注册
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/17 0017
     *@修改人和其它信息
     */
    void register(RegisterVo registerVo);

    /**
     *@描述 根据ID获取用户信息
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/17 0017
     *@修改人和其它信息
     */
    LoginInfoVo getLoginInfo(String memberId);

    /**
     *@描述 根据微信openId获取用户信息
     *@参数
     *@返回值 
     *@创建人 ruansl
     *@创建时间 2021/7/5 0005
     *@修改人和其它信息
     */
    UcenterMember getByOpenid(String openid);

    /**
     *@描述 统计某一天的注册人数
     *@参数
     *@返回值 
     *@创建人 ruansl
     *@创建时间 2021/7/7 0007
     *@修改人和其它信息
     */
    Integer countRegisterByDay(String day);
}
