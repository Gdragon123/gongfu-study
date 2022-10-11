package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传接口
 */
public interface FileService {
    //上传文件
    String upload(MultipartFile file);
}
