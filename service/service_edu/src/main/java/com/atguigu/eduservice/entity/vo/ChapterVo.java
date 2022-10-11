package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/9 0009
 * @描述  章节信息VO类
 **/
@ApiModel(value = "章节信息")
@Data
public class ChapterVo implements Serializable {

    //章节ID
    private String id;

    //章节名称
    private String title;

    //小结信息
    private List<VideoVo> videoVos;

}

