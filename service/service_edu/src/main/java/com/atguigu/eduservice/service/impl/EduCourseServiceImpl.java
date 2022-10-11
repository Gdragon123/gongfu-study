package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.GuliException;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoFormVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQueryVo;
import com.atguigu.eduservice.entity.vo.CourseWebVo;
import com.atguigu.eduservice.enums.CourseEnum;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService descriptionService;
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private EduChapterService chapterService;

    @Override
    public String saveCourseInfo(CourseInfoFormVo vo) {

        //保存课程基本信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(vo, course);
        Boolean saveCourseResult = this.save(course);
        if (!saveCourseResult) {
            throw new GuliException(20001, "添加课程信息失败");
        }

        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(vo.getDescription());
        courseDescription.setId(course.getId());
        Boolean saveDescriptionResult = descriptionService.save(courseDescription);
        if (!saveDescriptionResult) {
            throw new GuliException(20001, "添加课程详细信息失败");
        }

        return course.getId();
    }

    @Override
    public CourseInfoFormVo getCourseInfoFormById(String id) {
        EduCourse course = this.baseMapper.selectById(id);
        if (Objects.isNull(course)) {
            throw new GuliException(20001, "课程信息不存在");
        }
        CourseInfoFormVo vo = new CourseInfoFormVo();
        BeanUtils.copyProperties(course, vo);
        EduCourseDescription description = descriptionService.getById(vo.getId());
        if (!Objects.isNull(description)) {
            vo.setDescription(description.getDescription());
        }
        return vo;
    }

    @Override
    public void updateCourseInfoById(CourseInfoFormVo courseInfoForm) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, course);
        Boolean result = this.updateById(course);
        if (!result) {
            throw new GuliException(20001, "课程信息保存失败");
        }
        EduCourseDescription description = new EduCourseDescription();
        description.setId(course.getId());
        description.setDescription(courseInfoForm.getDescription());
        Boolean resultDescription = descriptionService.updateById(description);
        if(!resultDescription){
            throw new GuliException(20001, "课程详情信息保存失败");
        }
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.getCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus(CourseEnum.COURSE_NORMAL.getCode());
        Integer count = baseMapper.updateById(course);
        return null != count && count > 0;
    }

    @Override
    public Page<EduCourse> page(Long current, Long limit, CourseQueryVo vo) {

        if (Objects.isNull(current) || Objects.isNull(limit)) {
            throw new GuliException(20001, "分页参数不能为空");
        }
        Page<EduCourse> pageCourse = new Page<>(current, limit);
        QueryWrapper<EduCourse> qw = new QueryWrapper<>();
        if (!Objects.isNull(vo)) {
            if (!StringUtils.isEmpty(vo.getTitle())) {
                qw.like("title", vo.getTitle());
            }
            if (!StringUtils.isEmpty(vo.getSubjectId())) {
                qw.eq("subject_id", vo.getSubjectId());
            }
            if (!StringUtils.isEmpty(vo.getSubjectParentId())) {
                qw.eq("subject_parent_id", vo.getSubjectParentId());
            }
            if (!StringUtils.isEmpty(vo.getTeacherId())) {
                qw.like("teacher_id", vo.getTeacherId());
            }
            qw.eq("is_deleted", 0);
            qw.orderByDesc("gmt_create");
        }
        //调用框架提供的分页查询接口
        this.page(pageCourse, qw);
        //返回查询结果集
        return pageCourse;
    }

    @Override
    public boolean deleteCourseById(String id) {
        //根据id删除所有小节
        videoService.removeByCourseId(id);
        //根据id删除所有章节
        chapterService.removeByCourseId(id);
        //根据id删除所有课程详情
        descriptionService.removeById(id);
        //删除封面 TODO 独立完成

        return this.removeById(id);
    }

    @Override
    public List<EduCourse> selectByTeacherId(String teacherId) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<EduCourse>();

        queryWrapper.eq("teacher_id", teacherId);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");

        List<EduCourse> courses = baseMapper.selectList(queryWrapper);
        return courses;
    }

    @Override
    public Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQuery.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseQuery.getSubjectParentId());
        }

        if (!StringUtils.isEmpty(courseQuery.getSubjectId())) {
            queryWrapper.eq("subject_id", courseQuery.getSubjectId());
        }

        if (!StringUtils.isEmpty(courseQuery.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(courseQuery.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseQuery.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam, queryWrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public CourseWebVo selectInfoWebById(String id) {
        this.updatePageViewCount(id);
        return baseMapper.selectInfoWebById(id);
    }

    @Override
    public void updatePageViewCount(String id) {
        EduCourse course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }

}
