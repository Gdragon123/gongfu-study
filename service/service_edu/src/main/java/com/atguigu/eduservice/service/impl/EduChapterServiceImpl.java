package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.GuliException;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.atguigu.eduservice.entity.vo.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> nestedListByCourseId(String courseId) {
        if (StringUtils.isEmpty(courseId)) {
            return new ArrayList<>();
        }
        List<ChapterVo> results = new ArrayList<>();
        //查询章节信息
        QueryWrapper<EduChapter> chapterQw = new QueryWrapper<>();
        chapterQw.eq("course_id", courseId);
        chapterQw.orderByAsc("sort", "id");
        List<EduChapter> eduChapters = baseMapper.selectList(chapterQw);
        //封装章节数据
        if (!CollectionUtils.isEmpty(eduChapters)) {
            eduChapters.stream().forEach(x -> {
                ChapterVo vo = new ChapterVo();
                BeanUtils.copyProperties(x, vo);
                results.add(vo);
            });
        }
        //查询小结信息
        QueryWrapper<EduVideo> videoQw = new QueryWrapper<>();
        videoQw.eq("course_id", courseId);
        videoQw.orderByAsc("sort", "id");
        List<EduVideo> eduVideos = eduVideoService.selectList(videoQw);
        //封装小结数据
        if (!CollectionUtils.isEmpty(results)) {
            results.stream().forEach(x -> {
                List<VideoVo> items = new ArrayList<>();
                if (!CollectionUtils.isEmpty(eduVideos)) {
                    eduVideos.stream().forEach(y -> {
                        if (y.getChapterId().equals(x.getId())) {
                            VideoVo videoVo = new VideoVo();
                            BeanUtils.copyProperties(y, videoVo);
                            items.add(videoVo);
                        }
                    });
                }
                x.setVideoVos(items);
            });
        }
        return results;
    }

    @Override
    public void addChapter(EduChapter eduChapter) {
        Integer result = this.baseMapper.insert(eduChapter);
        if (result < 0) {
            throw new GuliException(20001, "添加章节信息失败");
        }
    }

    @Override
    public EduChapter getChapterById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        return this.baseMapper.selectById(id);
    }

    @Override
    public void updateChapter(EduChapter eduChapter) {
        this.baseMapper.updateById(eduChapter);
    }

    @Override
    public void deleteChapter(String id) {
        //查询章节下是否有小节
        QueryWrapper<EduVideo> qw = new QueryWrapper<>();
        qw.eq("chapter_id", id);
        Integer count = eduVideoService.count(qw);
        if (count > 0) { //如果有，则不能删除
            throw new GuliException(20001, "该章节包含小节，无法删除");
        } else { //如果没有，可以删除
            this.baseMapper.deleteById(id);
        }

    }

    @Override
    public boolean removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        return this.remove(queryWrapper);
    }


}
