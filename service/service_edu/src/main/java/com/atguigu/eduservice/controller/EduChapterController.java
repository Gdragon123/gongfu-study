package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/eduservice/chapter")
//@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService service;

    /**
     *@描述 根据课程ID获取章节列表（包含小节）
     *@参数 课程ID
     *@返回值 章节列表（包含小节）
     *@创建人 ruansl
     *@创建时间 2021/6/9 0009
     *@修改人和其它信息
     */
    @ApiOperation(value = "根据课程ID获取章节列表")
    @GetMapping("/getnestlist/{courseId}")
    @ResponseBody
    public R nestedListByCourseId(@ApiParam(name = "courseId", value = "课程ID")
                                      @PathVariable String courseId) {
        List<ChapterVo> chapterVos = service.nestedListByCourseId(courseId);
        return R.ok().data("chapters", chapterVos);
    }

    /**
     *@描述
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    @ApiOperation(value = "新增章节信息")
    @PostMapping("/addChapter")
    @ResponseBody
    public R saveChapter(@ApiParam(name = "eduChapter", value = "章节信息")
                                     @RequestBody EduChapter eduChapter) {
        service.addChapter(eduChapter);
        return R.ok();
    }

    /**
     *@描述
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    @ApiOperation(value = "获取章节信息")
    @GetMapping("/getChapter/{chapterId}")
    @ResponseBody
    public R getChapter(@ApiParam(name = "chapterId", value = "章节ID")
                           @PathVariable String chapterId) {
        EduChapter chapter = service.getChapterById(chapterId);
        return R.ok().data("chapter", chapter);
    }



    /**
     *@描述
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    @ApiOperation(value = "修改章节信息")
    @PutMapping("/updateChapter")
    @ResponseBody
    public R updateChapter(@ApiParam(name = "eduChapter", value = "章节信息")
                         @RequestBody EduChapter eduChapter) {
        service.updateChapter(eduChapter);
        return R.ok();
    }

    /**
     *@描述
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    @ApiOperation(value = "删除章节信息")
    @DeleteMapping("/deleteChapter/{chapterId}")
    @ResponseBody
    public R deleteChapter(@ApiParam(name = "chapterId", value = "章节ID")
                               @PathVariable String chapterId) {
        service.deleteChapter(chapterId);
        return R.ok();
    }

}

