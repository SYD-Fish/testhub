package com.syd.tshub.service.impl;

import com.syd.tshub.dao.intf.GradeDao;
import com.syd.tshub.dao.intf.UserDao;
import com.syd.tshub.entity.GradeEntity;
import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.request.GradeListReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.GradeService;
import com.syd.tshub.wrapper.CourseQuery;
import com.syd.tshub.wrapper.GradeQuery;
import com.syd.tshub.wrapper.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private UserDao userDao;

    @Override
    public BaseResponse createGrades(List<GradeEntity> grades) {
        Date date = new Date();
        grades.forEach(grade -> {
            grade.setEnbal(1);
            grade.setCreateTime(date);
        });
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
    public List<GradeEntity> exportGrades(Integer userId, Integer courseId) {
        GradeQuery gradeQuery = new GradeQuery();
        if (userId != null) {
            gradeQuery.where.userId().eq(userId).end();
        }
        if (courseId != null) {
            gradeQuery.where.courseId().eq(courseId).end();
        }
        return gradeDao.mapper().listEntity(gradeQuery);
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

    @Override
    public BaseResponse gradeStudents(GradeListReq gradeListReq) {
        UserQuery userQuery = new UserQuery().where.roleId().eq(1).end();
        List<UserEntity> students = userDao.mapper().listEntity(userQuery);
        GradeQuery gradeQuery = new GradeQuery();
        if (gradeListReq.getCourseId() != null) {
            gradeQuery.where
                    .courseId().eq(gradeListReq.getCourseId())
                    .end();
        }
        List<GradeEntity> list = gradeDao.mapper().listEntity(gradeQuery);
        List<Integer> userIds = list.stream().map(GradeEntity::getUserId).collect(Collectors.toList());
        List<UserEntity> collect = students.stream().filter(s -> !userIds.contains(s.getUserId())).collect(Collectors.toList());
        return BaseResponse.success(collect);
    }
}
