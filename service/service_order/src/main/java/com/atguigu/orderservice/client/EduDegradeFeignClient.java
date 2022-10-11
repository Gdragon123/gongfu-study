package com.atguigu.orderservice.client;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.vo.CourseInfoFormVo;
import org.springframework.stereotype.Component;

/**
 * @创建人 ruansl
 * @创建时间 2021/7/7 0007
 * @描述
 **/
@Component
public class EduDegradeFeignClient implements EduClient{

    @Override
    public CourseInfoFormVo getCourseInfoDto(String courseId) {
        return new CourseInfoFormVo();
    }
}
