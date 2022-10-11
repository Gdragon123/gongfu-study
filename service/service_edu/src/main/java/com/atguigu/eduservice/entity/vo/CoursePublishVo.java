package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/10 0010
 * @描述
 **/
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo implements Serializable {

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
