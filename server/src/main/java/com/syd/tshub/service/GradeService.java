package com.syd.tshub.service;

import com.syd.tshub.entity.GradeEntity;
import com.syd.tshub.request.GradeListReq;
import com.syd.tshub.response.base.BaseResponse;

import java.util.List;

/**
 * 描述：
 *
 * @ClassName GardeService
 * @Description TODO
 * @Author syd
 * @Date 2022/1/17 20:19
 * @Version 1.0
 */
public interface GradeService {

    BaseResponse createGrades(List<GradeEntity> grades);

    BaseResponse listGrade(GradeListReq gradeListReq);

    BaseResponse deleteGrade(List<String> gradeIds);

    BaseResponse updateGrade(GradeEntity grade);

    BaseResponse gradeStudents(GradeListReq gradeListReq);

    List<GradeEntity> exportGrades(Integer userId, Integer courseId);
}
