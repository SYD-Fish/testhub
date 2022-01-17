package com.syd.tshub.service.impl;

import com.syd.tshub.dao.intf.ScheduleDao;
import com.syd.tshub.entity.ScheduleEntity;
import com.syd.tshub.request.ScheduleListReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.ScheduleService;
import com.syd.tshub.wrapper.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Override
    public BaseResponse listSchedule(ScheduleListReq scheduleListReq) {
        UserQuery userQuery = new UserQuery();
        if (!StringUtils.isEmpty(scheduleListReq.getContent())) {
            userQuery.where
                    .userName().like(scheduleListReq.getContent())
                    .end();
        }
        userQuery.orderBy().createTime().desc()
                .end()
                .limit(( scheduleListReq.getPageIndex() -1) * scheduleListReq.getPageSize(), scheduleListReq.getPageSize());

        return BaseResponse.success(scheduleDao.mapper().stdPagedEntity(userQuery));
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
        int insert = scheduleDao.mapper().insert(schedule);
        return BaseResponse.success();
    }
}
