package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoFormVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQueryVo;
import com.atguigu.eduservice.entity.vo.CourseWebVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 报错课程信息
     * @param vo
     * @return 新生成的课程id
     */
    String saveCourseInfo(CourseInfoFormVo vo);

    /**
     *@描述 根据课程ID获取课程基本信息
     *@参数 课程ID
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/11 0011
     *@修改人和其它信息
     */
    CourseInfoFormVo getCourseInfoFormById(String id);

    /**
     *@描述
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/11 0011
     *@修改人和其它信息
     */
    void updateCourseInfoById(CourseInfoFormVo courseInfoForm);

    /**
     *@描述 根据课程ID获取课程发布信息
     *@参数 课程ID
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    CoursePublishVo getCoursePublishVoById(String id);

    /**
     *@描述 根据课程ID发布课程
     *@参数
     *@返回值 
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    boolean publishCourseById(String id);

    /**
     *@描述 根据条件分页查询课程列表
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/11 0011
     *@修改人和其它信息
     */
    Page<EduCourse> page(Long current, Long limit, CourseQueryVo vo);

    /**
     *@描述
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/11 0011
     *@修改人和其它信息
     */
    boolean deleteCourseById(String id);

    /**
     *@描述 根据讲师ID查询当前讲师的课程列表
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/7/5 0005
     *@修改人和其它信息
     */
    List<EduCourse> selectByTeacherId(String teacherId);

    /**
     *@描述 
     *@参数 前端系统分页查询课程数据
     *@返回值 
     *@创建人 ruansl
     *@创建时间 2021/7/6 0006
     *@修改人和其它信息
     */
    Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery);

    /**
     * 获取课程信息
     * @param id
     * @return
     */
    CourseWebVo selectInfoWebById(String id);

    /**
     * 更新课程浏览数
     * @param id
     */
    void updatePageViewCount(String id);

}
