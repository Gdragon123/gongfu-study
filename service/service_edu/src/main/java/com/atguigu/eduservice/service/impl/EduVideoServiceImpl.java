package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.GuliException;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.VideoInfoFormVo;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.mapper.EduVideoMapper;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-31
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public List<EduVideo> selectList(QueryWrapper<EduVideo> videoQw) {
        return baseMapper.selectList(videoQw);
    }

    @Override
    public Boolean saveVideoInfo(VideoInfoFormVo vo) {
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(vo, video);
        return this.save(video);
    }

    @Override
    public VideoInfoFormVo getVideoInfoFormById(String id) {
        EduVideo video = this.baseMapper.selectById(id);
        if (Objects.isNull(video)) {
            return null;
        }
        VideoInfoFormVo vo = new VideoInfoFormVo();
        BeanUtils.copyProperties(video, vo);
        return vo;
    }

    @Override
    public void updateVideoInfoById(VideoInfoFormVo vo) {
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(vo, video);
        Integer count = this.baseMapper.updateById(video);
        if (count < 0) {
            throw new GuliException(20001, "修改课时信息失败");
        }
    }

    @Override
    public boolean removeVideoById(String id) {
        //查询云端视频id
        EduVideo video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        //删除视频资源
        if(!StringUtils.isEmpty(videoSourceId)){
            R result = vodClient.removeVideo(videoSourceId);
            if (result.getCode() == 20001) {
                throw new GuliException(20001, "删除视频失败，熔断器执行。。。。。。");
            }
        }
        //删除课时信息
        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Override
    public boolean removeByCourseId(String courseId) {

        //根据课程id查询所有视频列表
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(queryWrapper);

        List<String> videoSourceIdList = null;
        if (!CollectionUtils.isEmpty(videoList)) {
            //得到所有视频列表的云端原始视频id
            videoSourceIdList = videoList.stream().map(x -> x.getVideoSourceId()).collect(Collectors.toList());
        }
        //调用vod服务删除远程视频
        if(!CollectionUtils.isEmpty(videoSourceIdList)){
            vodClient.removeVideoList(videoSourceIdList);
        }

        //删除课时信息
        QueryWrapper qw = new QueryWrapper();
        qw.eq("course_id", courseId);
        return this.remove(qw);
    }
}
