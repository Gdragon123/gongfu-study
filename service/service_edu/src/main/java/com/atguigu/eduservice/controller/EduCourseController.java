package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.OrderClient;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.*;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/eduservice/course")
//@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService service;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    @ApiOperation("新增课程")
    @PostMapping("/addCourseInfo")
    @ResponseBody
    public R addCourseInfo(@ApiParam(name = "CourseInfoForm", value = "课程基本信息")
                               @RequestBody CourseInfoFormVo vo) {
        if (Objects.isNull(vo)) {
            return R.error().message("课程信息不能为空");
        }
        String courseId = service.saveCourseInfo(vo);
        if (!StringUtils.isEmpty(courseId)) {
            return R.ok().data("courseId", courseId);
        } else {
            return R.error().message("课程信息添加失败");
        }
    }

    @ApiOperation(value = "根据ID获取课程基本信息")
    @GetMapping("courseinfo/{id}")
    public R getById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        CourseInfoFormVo courseInfoForm = service.getCourseInfoFormById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("updatecourseinfo/{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "courseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoFormVo courseInfoForm,

            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        service.updateCourseInfoById(courseInfoForm);
        return R.ok();
    }



    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("coursepublishinfo/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        CoursePublishVo courseInfoForm = service.getCoursePublishVoById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publishcourse/{id}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        service.publishCourseById(id);
        return R.ok();
    }

    @ApiOperation(value = "分页按条件查询课程列表")
    @PostMapping("pagecoursecondition/{current}/{limit}")
    public R pageList(@PathVariable long current,@PathVariable long limit, CourseQueryVo queryVo) {
        Page<EduCourse> pageCourse = service.page(current,limit, queryVo);
        Long total = pageCourse.getTotal();//总记录数
        List<EduCourse> records = pageCourse.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }


    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("deleteCourse/{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        boolean result = service.deleteCourseById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "pagelistweb/{page}/{limit}")
    public R pageListWeb(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery){
        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);
        Map<String, Object> map = service.pageListWeb(pageParam, courseQuery);
        return  R.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "frontgetbyId/{courseId}")
    public R frontGetById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId, HttpServletRequest request){

        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = service.selectInfoWebById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.nestedListByCourseId(courseId);

        //远程调用，判断课程是否被购买
        boolean buyCourse = orderClient.isBuyCourse(JwtUtils.getMemberIdByJwtToken(request), courseId);

        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList).data("isbuy",buyCourse);
    }

    //根据课程id查询课程信息
    @GetMapping("getDto/{courseId}")
    public com.atguigu.commonutils.vo.CourseInfoFormVo getCourseInfoDto(@PathVariable String courseId) {
        CourseInfoFormVo courseInfoForm = service.getCourseInfoFormById(courseId);
        com.atguigu.commonutils.vo.CourseInfoFormVo courseInfo = new com.atguigu.commonutils.vo.CourseInfoFormVo();
        BeanUtils.copyProperties(courseInfoForm,courseInfo);
        return courseInfo;
    }

}

