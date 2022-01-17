package com.syd.tshub.service;

import com.syd.tshub.entity.ScheduleEntity;
import com.syd.tshub.request.ScheduleListReq;
import com.syd.tshub.response.base.BaseResponse;

import java.util.List;

/**
 * 描述：
 *
 * @ClassName ScheduleService
 * @Description TODO
 * @Author syd
 * @Date 2022/1/17 20:19
 * @Version 1.0
 */
public interface ScheduleService {
    BaseResponse listSchedule(ScheduleListReq scheduleListReq);

    BaseResponse deleteSchedule(List<String> scheduleIds);

    BaseResponse updateSchedule(ScheduleEntity schedule);

    BaseResponse createSchedule(ScheduleEntity schedule);
}
