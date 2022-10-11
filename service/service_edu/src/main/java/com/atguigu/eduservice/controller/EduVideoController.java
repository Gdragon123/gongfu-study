package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.VideoInfoFormVo;
import com.atguigu.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/eduservice/video")
//@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService service;

    @ApiOperation(value = "新增课时信息")
    @PostMapping("/savevideoinfo")
    @ResponseBody
    public R saveVideo(@ApiParam(name = "videoForm", value = "课时对象",required = true)
                           @RequestBody VideoInfoFormVo vo) {
        Boolean result = service.saveVideoInfo(vo);
        if (result) {
            return R.ok();
        } else {
            return R.error().message("添加课时失败");
        }
    }

    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("getvideoinfo/{id}")
    public R getVideInfoById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        VideoInfoFormVo videoInfoForm = service.getVideoInfoFormById(id);
        return R.ok().data("item", videoInfoForm);
    }

    @ApiOperation(value = "更新课时")
    @PutMapping("updatevideoinfo/{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "VideoInfoForm", value = "课时基本信息", required = true)
            @RequestBody VideoInfoFormVo vo,

            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        service.updateVideoInfoById(vo);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("/deleteVideoInfo/{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        boolean result = service.removeVideoById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}

