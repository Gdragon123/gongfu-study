package com.atguigu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * excel中课程分类实体
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;  //一级分类

    @ExcelProperty(index = 1)
    private String twoSubjctName;   //二级分类

}
