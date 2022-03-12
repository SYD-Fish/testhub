package com.syd.tshub.service;

import com.syd.tshub.entity.CourseEntity;
import com.syd.tshub.entity.StudentCourseEntity;
import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.request.CourseListReq;
import com.syd.tshub.request.StudentCourseListReq;
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

    BaseResponse deleteCourse(List<Integer> courseIds);

    BaseResponse updateCourse(CourseEntity course);

    BaseResponse studentSelectCourse(StudentCourseEntity studentCourse);

    BaseResponse teacherConfirmCourse(StudentCourseEntity studentCourse);

    BaseResponse<List<StudentCourseEntity>> listStudentCourse(StudentCourseListReq req, UserEntity user);
}
