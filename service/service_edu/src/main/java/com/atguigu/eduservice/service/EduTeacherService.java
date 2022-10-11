package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     *@描述 分页查询讲师列表页
     *@参数
     *@返回值 
     *@创建人 ruansl
     *@创建时间 2021/7/5 0005
     *@修改人和其它信息
     */
    public Map<String, Object> pageListWeb(Page<EduTeacher> pageParam);
    
}
