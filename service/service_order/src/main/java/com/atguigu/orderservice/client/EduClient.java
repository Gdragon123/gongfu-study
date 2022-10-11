package com.atguigu.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @创建人 ruansl
 * @创建时间 2021/7/7 0007
 * @描述
 **/
@Component
@FeignClient(name = "service-edu", fallback = EduDegradeFeignClient.class)
public interface EduClient {
    //根据课程id查询课程信息
    @GetMapping("/eduservice/course/getDto/{courseId}")
    public com.atguigu.commonutils.vo.CourseInfoFormVo getCourseInfoDto(@PathVariable("courseId") String courseId);
}
