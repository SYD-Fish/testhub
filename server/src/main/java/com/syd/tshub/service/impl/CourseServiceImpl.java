package com.syd.tshub.service.impl;

import com.syd.tshub.dao.intf.CourseDao;
import com.syd.tshub.dao.intf.StudentCourseDao;
import com.syd.tshub.entity.CourseEntity;
import com.syd.tshub.entity.StudentCourseEntity;
import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.request.CourseListReq;
import com.syd.tshub.request.StudentCourseListReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.CourseService;
import com.syd.tshub.wrapper.CourseQuery;
import com.syd.tshub.wrapper.StudentCourseQuery;
import com.syd.tshub.wrapper.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述：
 *
 * @ClassName CourseServiceImpl
 * @Description TODO
 * @Author syd
 * @Date 2022/1/17 20:06
 * @Version 1.0
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private StudentCourseDao studentCourseDao;

    @Override
    public BaseResponse createCourse(CourseEntity course) {
        course.setEnable(1);
        course.setCreateTime(new Date());
        int insert = courseDao.mapper().insert(course);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse listCourse(CourseListReq courseListReq) {
        CourseQuery courseQuery = new CourseQuery();
        if (!StringUtils.isEmpty(courseListReq.getContent())) {
            courseQuery.where
                    .courseName().like(courseListReq.getContent())
                    .end();
        }
        courseQuery.orderBy().createTime().desc()
                .end()
                .limit(( courseListReq.getPageIndex() -1) * courseListReq.getPageSize(), courseListReq.getPageSize());

        return BaseResponse.success(courseDao.mapper().stdPagedEntity(courseQuery));
    }

    @Override
    public BaseResponse deleteCourse(List<Integer> courseIds) {
        courseDao.mapper().deleteByIds(courseIds);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse updateCourse(CourseEntity course) {
        courseDao.mapper().updateById(course);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse studentSelectCourse(StudentCourseEntity studentCourse) {
        studentCourseDao.save(studentCourse);
        return BaseResponse.success(studentCourse);
    }

    @Override
    public BaseResponse teacherConfirmCourse(StudentCourseEntity studentCourse) {
        studentCourseDao.updateById(studentCourse);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse listStudentCourse(StudentCourseListReq req, UserEntity user) {
        List<CourseEntity> courses = courseDao.mapper().listEntity(new CourseQuery().where
                .teacherId().eq(user.getUserId())
                .and.enable().eq(1).end());
        if (CollectionUtils.isEmpty(courses)) {
            return BaseResponse.success();
        }
        List<Integer> courseIds = courses.stream().map(CourseEntity::getId).collect(Collectors.toList());
        StudentCourseQuery query = new StudentCourseQuery();
        query.where.courseId().in(courseIds).end();
        if (req.getStatus() != null) {
            query.where.status().eq(req.getStatus());
        }
        query.limit((req.getPageIndex()-1) * req.getPageSize(), req.getPageSize());

        return BaseResponse.success(studentCourseDao.mapper().stdPagedEntity(query));
    }
}
