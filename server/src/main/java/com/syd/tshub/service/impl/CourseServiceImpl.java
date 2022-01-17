package com.syd.tshub.service.impl;

import com.syd.tshub.dao.intf.CourseDao;
import com.syd.tshub.entity.CourseEntity;
import com.syd.tshub.request.CourseListReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.CourseService;
import com.syd.tshub.wrapper.CourseQuery;
import com.syd.tshub.wrapper.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Override
    public BaseResponse createCourse(CourseEntity course) {
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
    public BaseResponse deleteCourse(List<String> courseIds) {
        courseDao.mapper().deleteByIds(courseIds);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse updateCourse(CourseEntity course) {
        courseDao.mapper().updateById(course);
        return BaseResponse.success();
    }
}
