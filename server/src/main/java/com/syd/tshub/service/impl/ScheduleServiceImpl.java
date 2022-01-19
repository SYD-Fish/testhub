package com.syd.tshub.service.impl;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.JoinBuilder;
import cn.org.atool.fluent.mybatis.segment.model.Parameters;
import com.syd.tshub.dao.intf.CourseDao;
import com.syd.tshub.dao.intf.ScheduleDao;
import com.syd.tshub.entity.CourseEntity;
import com.syd.tshub.entity.ScheduleEntity;
import com.syd.tshub.request.ScheduleListReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.ScheduleService;
import com.syd.tshub.wrapper.CourseQuery;
import com.syd.tshub.wrapper.ScheduleQuery;
import com.syd.tshub.wrapper.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述：
 *
 * @ClassName ScheduleServiceImpl
 * @Description TODO
 * @Author syd
 * @Date 2022/1/17 20:19
 * @Version 1.0
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private CourseDao courseDao;

    @Override
    public BaseResponse listSchedule(ScheduleListReq scheduleListReq) {
        ScheduleQuery query = new ScheduleQuery();
        if (!StringUtils.isEmpty(scheduleListReq.getContent())) {
            query.where
                    .courseName().like(scheduleListReq.getContent())
                    .end();
        }
        query.orderBy().startDate().desc()
                .end()
                .limit(( scheduleListReq.getPageIndex() -1) * scheduleListReq.getPageSize(), scheduleListReq.getPageSize());

        return BaseResponse.success(scheduleDao.mapper().stdPagedEntity(query));
    }

    @Override
    public BaseResponse deleteSchedule(List<String> scheduleIds) {
        scheduleDao.mapper().deleteByIds(scheduleIds);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse updateSchedule(ScheduleEntity schedule) {
        scheduleDao.mapper().updateById(schedule);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse createSchedule(ScheduleEntity schedule) {
        schedule.setEnable(1);
//        CourseEntity course = courseDao.selectById(schedule.getCourseId());
//        Integer count = course.getCount();
//        Date startDate = schedule.getStartDate();
        int insert = scheduleDao.mapper().insert(schedule);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse listScheduleCourse(ScheduleListReq scheduleListReq) {
        List<CourseEntity> courses = courseDao.mapper().listEntity(new CourseQuery());
        List<ScheduleEntity> list = scheduleDao.mapper().listEntity(new ScheduleQuery());
        List<Integer> courseIds = list.stream().map(ScheduleEntity::getCourseId).collect(Collectors.toList());
        return BaseResponse.success(courses.stream().filter(c -> !courseIds.contains(c.getId())).collect(Collectors.toList()));
    }
}
