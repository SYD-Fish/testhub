package com.syd.tshub.controller;

import com.syd.tshub.entity.CourseEntity;
import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.request.CourseListReq;
import com.syd.tshub.request.UserCreateReq;
import com.syd.tshub.request.UserListReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类描述：
 *
 * @ClassName CourseController
 * @Description TODO
 * @Author syd
 * @Date 2022/1/17 20:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 创建课程
     * @param course
     * @return
     */
    @PostMapping("/create")
    public BaseResponse createCourse(@RequestBody CourseEntity course) {
        return courseService.createCourse(course);

    }

    /**
     * 课程列表
     * @param courseListReq
     * @return
     */
    @PostMapping("/list")
    public BaseResponse listCourse(@RequestBody CourseListReq courseListReq){
        return courseService.listCourse(courseListReq);
    }


    /**
     * 删除课程 支持批量
     * @param courseIds
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResponse deleteCourse(@RequestBody List<String> courseIds) {
        return courseService.deleteCourse(courseIds);
    }

    /**
     * 更新课程
     * @param course
     * @return
     */
    @PutMapping("/update")
    public BaseResponse updateCourse(@RequestBody CourseEntity course) {
        return courseService.updateCourse(course);
    }
}
