package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     *@描述 根据课程ID获取章节和小结信息
     *@参数 课程ID
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/9 0009
     *@修改人和其它信息
     */
    List<ChapterVo> nestedListByCourseId(String courseId);

    /**
     *@描述 添加章节信息
     *@参数
     *@返回值 是否成功
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    void addChapter(EduChapter eduChapter);

    /**
     *@描述 根据ID获取章节信息
     *@参数
     *@返回值
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    EduChapter getChapterById(String id);

    /**
     *@描述 修改章节信息
     *@参数
     *@返回值 是否成功
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    void updateChapter(EduChapter eduChapter);

    /**
     *@描述 根据章节ID删除章节信息
     *@参数
     *@返回值 是否成功
     *@创建人 ruansl
     *@创建时间 2021/6/10 0010
     *@修改人和其它信息
     */
    void deleteChapter(String id);

    /**
     *@描述 
     *@参数 
     *@返回值 
     *@创建人 ruansl
     *@创建时间 2021/6/11 0011
     *@修改人和其它信息
     */
    boolean removeByCourseId(String courseId);
}
