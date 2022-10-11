package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseWebVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     *@描述 根据课程ID获取课程信息
     *@参数 课程ID
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    CoursePublishVo getCoursePublishVoById(String id);

    /**
     *@描述 根据课程ID查询课程详细信息
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/7/6 0006
     *@修改人和其它信息
     */
    CourseWebVo selectInfoWebById(String courseId);

}
