package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.SubjectNestedVo;
import com.atguigu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-29
 */
@Api("课程分类管理")
@RestController
@RequestMapping("/eduservice/subject")
//@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService service;

    /**
     * 添加课程分类
     *
     * @param file
     * @return
     */
    @ApiOperation("添加课程分类")
    @PostMapping("/save")
    @ResponseBody
    public R saveSubject(MultipartFile file) {
        if (Objects.isNull(file)) {
            return R.error().message("文件不能为空！");
        }
        service.saveSubject(file, service);
        return R.ok().message("上传成功");
    }

    /**
     * 嵌套数据列表
     * @return
     */
    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("/list")
    @ResponseBody
    public R nestedList(){
        List<SubjectNestedVo> nestedList = service.nestedList();
        return R.ok().data("items", nestedList);
    }

}

