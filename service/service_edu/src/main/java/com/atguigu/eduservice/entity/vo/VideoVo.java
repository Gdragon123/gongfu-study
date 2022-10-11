package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/9 0009
 * @描述
 **/
@ApiModel(value = "小结信息")
@Data
public class VideoVo implements Serializable {
    //小节ID
    private String id;

    //小节名称
    private String title;

    //是否免费
    private Boolean free;

    //云端视频资源ID
    private String videoSourceId;

    //视频名称
    private String videoOriginalName;

}
