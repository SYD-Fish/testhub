package com.syd.tshub.service;

import com.syd.tshub.entity.CourseEntity;
import com.syd.tshub.request.CourseListReq;
import com.syd.tshub.response.base.BaseResponse;

import java.util.List;

/**
 * 描述：
 *
 * @ClassName CourseService
 * @Description TODO
 * @Author syd
 * @Date 2022/1/17 20:05
 * @Version 1.0
 */
public interface CourseService {
    BaseResponse createCourse(CourseEntity course);

    BaseResponse listCourse(CourseListReq courseListReq);

    BaseResponse deleteCourse(List<String> courseIds);

    BaseResponse updateCourse(CourseEntity course);
}
