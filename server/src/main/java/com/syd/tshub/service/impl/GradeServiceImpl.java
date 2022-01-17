package com.syd.tshub.service.impl;

import com.syd.tshub.dao.intf.GradeDao;
import com.syd.tshub.entity.GradeEntity;
import com.syd.tshub.request.GradeListReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.GradeService;
import com.syd.tshub.wrapper.CourseQuery;
import com.syd.tshub.wrapper.GradeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 类描述：
 *
 * @ClassName GardeServiceImpl
 * @Description TODO
 * @Author syd
 * @Date 2022/1/17 20:20
 * @Version 1.0
 */
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;

    @Override
    public BaseResponse createGrades(List<GradeEntity> grades) {
        int i = gradeDao.mapper().insertBatch(grades);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse listGrade(GradeListReq gradeListReq) {
        GradeQuery gradeQuery = new GradeQuery();
        if (!StringUtils.isEmpty(gradeListReq.getContent())) {
            gradeQuery.where
                    .courseName().like(gradeListReq.getContent())
                    .end();
        }
        if (gradeListReq.getCourseId() != null) {
            gradeQuery.where
                    .courseId().eq(gradeListReq.getCourseId())
                    .end();
        }
        gradeQuery.orderBy().createTime().desc()
                .end()
                .limit(( gradeListReq.getPageIndex() -1) * gradeListReq.getPageSize(), gradeListReq.getPageSize());

        return BaseResponse.success(gradeDao.mapper().stdPagedEntity(gradeQuery));

    }

    @Override
    public BaseResponse deleteGrade(List<String> gradeIds) {
        int i = gradeDao.mapper().deleteByIds(gradeIds);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse updateGrade(GradeEntity grade) {
        gradeDao.mapper().updateById(grade);
        return BaseResponse.success();
    }
}
