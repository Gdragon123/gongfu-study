package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * 文件上传
 */
@Api("文件上传")
@RestController
@RequestMapping("/oss/file")
//@CrossOrigin    //解决跨域问题
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    @ResponseBody
    public R upload(MultipartFile file) {
        if (Objects.isNull(file)) {
            return R.error().message("请上传文件");
        }
        //返回文件的访问路径
        String url = fileService.upload(file);
        return R.ok().message("文件上传成功").data("url", url);
    }

}
