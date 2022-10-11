package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.VideoInfoFormVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     *@描述 查询小节列表
     *@参数 查询条件
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    List<EduVideo> selectList(QueryWrapper<EduVideo> videoQw);

    /**
     *@描述 新增课时信息
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    Boolean saveVideoInfo(VideoInfoFormVo vo);

    /**
     *@描述
     *@参数 根据ID获取课时详情
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    VideoInfoFormVo getVideoInfoFormById(String id);

    /**
     *@描述 修改课时信息
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    void updateVideoInfoById(VideoInfoFormVo vo);

    /**
     *@描述 删除课时信息
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    boolean removeVideoById(String id);

    /**
     *@描述 
     *@参数
     *@返回值 
     *@创建人 ruansl
     *@创建时间 2021/6/11 0011
     *@修改人和其它信息
     */
    boolean removeByCourseId(String courseId);
}
