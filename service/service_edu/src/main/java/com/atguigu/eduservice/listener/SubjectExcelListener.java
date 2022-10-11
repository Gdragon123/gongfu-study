package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.commonutils.GuliException;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;
import java.util.Objects;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (Objects.isNull(subjectData)) {
            throw new GuliException(20001, "文件数据为空");
        }

        //判断一级分类是否已存在
        EduSubject oneSubject = existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (Objects.isNull(oneSubject)) {   //不存在，就添加
            oneSubject = new EduSubject();
            oneSubject.setTitle(subjectData.getOneSubjectName());
            oneSubject.setParentId("0");
            subjectService.save(oneSubject);
        }

        String pid = oneSubject.getId();
        EduSubject twoSubject = existTwoSubject(subjectService, subjectData.getTwoSubjctName(), pid);
        if (Objects.isNull(twoSubject)) {   //不存在，就添加
            twoSubject = new EduSubject();
            twoSubject.setTitle(subjectData.getTwoSubjctName());
            twoSubject.setParentId(pid);
            subjectService.save(twoSubject);
        }
    }

    //判断一级分类是否已存在，不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject subject = subjectService.getOne(wrapper);
        return subject;
    }

    //判断二级分类是否已存在，不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject subject = subjectService.getOne(wrapper);
        return subject;
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
