package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("用户信息")
@RestController
//@CrossOrigin    //解决跨域问题
@RequestMapping("/eduservice/user")
public class EduTeacherLoginController {

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
//    @ResponseBody
    public R login() {
        return R.ok().data("token","admin");
    }

    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
//    @ResponseBody
    public R info() {
        return R.ok().data("name", "admin").data("roles", "admin")
                .data("avatar", "http://www.xinhuanet.com/fashion/2018-07/19/c_1123147611_3.htm");
    }

}
