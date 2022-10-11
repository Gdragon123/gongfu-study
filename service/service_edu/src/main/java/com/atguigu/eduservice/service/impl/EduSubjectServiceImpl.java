package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.vo.SubjectNestedVo;
import com.atguigu.eduservice.entity.vo.SubjectVo;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-05-29
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //读取excel中的内容
            EasyExcel.read(file.getInputStream(), SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectNestedVo> nestedList() {
        //查询一级分类
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        wrapper.orderByAsc("sort", "id");
        List<EduSubject> oneSubjects = baseMapper.selectList(wrapper);
        //查询二级分类
        wrapper = new QueryWrapper<>();
        wrapper.ne("parent_id", "0");
        wrapper.orderByAsc("sort", "parent_id");
        List<EduSubject> twoSubjects = baseMapper.selectList(wrapper);
        //结果集
        List<SubjectNestedVo> nestedVos = new ArrayList<>();
        //组装数据
        if (oneSubjects != null && oneSubjects.size() > 0) {
            oneSubjects.stream().forEach(x -> {
                SubjectNestedVo vo = new SubjectNestedVo();
                BeanUtils.copyProperties(x, vo);
                List<SubjectVo> subjectVos = new ArrayList<>();
                if (twoSubjects != null && twoSubjects.size() > 0) {
                    List<EduSubject>  voItems = twoSubjects.stream().filter(y -> y.getParentId().equals(x.getId())).collect(Collectors.toList());
                    if (voItems != null && voItems.size() > 0) {
                        voItems.stream().forEach(y -> {
                            SubjectVo voItem = new SubjectVo();
                            BeanUtils.copyProperties(y, voItem);
                            subjectVos.add(voItem);
                        });
                    }
                }
                vo.setChildren(subjectVos);;
                nestedVos.add(vo);
            });
        }
        return nestedVos;
    }


}
