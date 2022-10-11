package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @创建人 ruansl
 * @创建时间 2021/6/14 0014
 * @描述
 **/
public interface VideoService {

    /**
     *@描述 视频上传阿里云
     *@参数 视频文件
     *@返回值 上传后的视频ID
     *@创建人 ruansl
     *@创建时间 2021/6/14 0014
     *@修改人和其它信息
     */
    String uploadVideo(MultipartFile file);

    /**
     *@描述 删除阿里云视频
     *@参数 视频ID
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/14 0014
     *@修改人和其它信息
     */
    void removeVideo(String videoId);

    /**
     *@描述 批量删除阿里云视频
     *@参数 视频ID集合
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/14 0014
     *@修改人和其它信息
     */
    void removeVideoList(List<String> videoIdList);

}
