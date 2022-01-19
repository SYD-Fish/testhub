package com.syd.tshub.controller;

import com.syd.tshub.entity.ScheduleEntity;
import com.syd.tshub.request.ScheduleListReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类描述：
 *
 * @ClassName ScheduleController
 * @Description TODO
 * @Author syd
 * @Date 2022/1/17 20:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 创建排课
     * @param schedule
     * @return
     */
    @PostMapping("/create")
    public BaseResponse createSchedule(@RequestBody ScheduleEntity schedule) {
        return scheduleService.createSchedule(schedule);

    }

    /**
     * 排课列表
     * @param scheduleListReq
     * @return
     */
    @PostMapping("/list")
    public BaseResponse listSchedule(@RequestBody ScheduleListReq scheduleListReq){
        return scheduleService.listSchedule(scheduleListReq);
    }

    /**
     * 可排课的课程列表
     * @param scheduleListReq
     * @return
     */
    @PostMapping("/courses")
    public BaseResponse listScheduleCourse(@RequestBody ScheduleListReq scheduleListReq){
        return scheduleService.listScheduleCourse(scheduleListReq);
    }


    /**
     * 删除排课 支持批量
     * @param scheduleIds
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResponse deleteSchedule(@RequestBody List<String> scheduleIds) {
        return scheduleService.deleteSchedule(scheduleIds);
    }

    /**
     * 更新排课
     * @param schedule
     * @return
     */
    @PutMapping("/update")
    public BaseResponse updateUser(@RequestBody ScheduleEntity schedule) {
        return scheduleService.updateSchedule(schedule);
    }
}
