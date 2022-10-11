package com.atguigu.ucenterservice.controller;


import com.atguigu.commonutils.GuliException;
import com.atguigu.commonutils.R;
import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import com.atguigu.ucenterservice.utils.JwtUtils;
import com.atguigu.ucenterservice.vo.LoginInfoVo;
import com.atguigu.ucenterservice.vo.LoginVo;
import com.atguigu.ucenterservice.vo.RegisterVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-17
 */
@RestController
@RequestMapping("/ucenterservice/ucentermember")
//@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            LoginInfoVo loginInfoVo = memberService.getLoginInfo(memberId);
            return R.ok().data("item", loginInfoVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"error");
        }
    }

    //根据ID获取用户信息
    @PostMapping("getInfoUc/{id}")
    public com.atguigu.commonutils.vo.UcenterMember getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = memberService.getById(id);
        com.atguigu.commonutils.vo.UcenterMember memeber = new com.atguigu.commonutils.vo.UcenterMember();
        BeanUtils.copyProperties(ucenterMember,memeber);
        return memeber;
    }

    @ApiOperation(value = "统计某一天的注册人数")
    @GetMapping(value = "countregister/{day}")
    public R registerCount(@PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }
}

