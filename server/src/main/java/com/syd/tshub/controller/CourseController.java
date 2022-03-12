package com.syd.tshub.controller;

import com.syd.tshub.entity.CourseEntity;
import com.syd.tshub.entity.StudentCourseEntity;
import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.factory.UserLoginFactory;
import com.syd.tshub.request.CourseListReq;
import com.syd.tshub.request.StudentCourseListReq;
import com.syd.tshub.request.UserCreateReq;
import com.syd.tshub.request.UserListReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.CourseService;
import com.syd.tshub.service.UserLoginService;
import com.syd.tshub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程控制器
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

    @Autowired
    private UserLoginFactory userLoginFactory;

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
     * 学生选课
     * @param courseId
     * @return
     */
    @PostMapping("/student/{courseId}")
    public BaseResponse selectCousre(@PathVariable("courseId") Integer courseId) {
        UserLoginService userService = userLoginFactory.getUserService();
        BaseResponse<UserEntity> currentLoginUser = userService.getCurrentLoginUser();
        if (currentLoginUser.isFail()) return currentLoginUser;
        UserEntity user = currentLoginUser.getData();
        if (user.getRoleId() != 1) {
            return BaseResponse.fail(101, "没有权限");

//            courseService.updateUserCourse();
        }
        StudentCourseEntity studentCourse = new StudentCourseEntity();
        studentCourse.setCourseId(courseId);
        studentCourse.setStudentId(user.getUserId());
        studentCourse.setStudentName(user.getUserName());
        studentCourse.setStatus(0);
        return courseService.studentSelectCourse(studentCourse);
    }

    /**
     * 教室确认选课
     * @param studentCourseId
     * @return
     */
    @PostMapping("/teacher/{courseId}")
    public BaseResponse confirmCousre(@PathVariable("studentCourseId") Integer studentCourseId) {
        UserLoginService userService = userLoginFactory.getUserService();
        BaseResponse<UserEntity> currentLoginUser = userService.getCurrentLoginUser();
        if (currentLoginUser.isFail()) return currentLoginUser;
        UserEntity user = currentLoginUser.getData();
        if (user.getRoleId() != 2) {
            return BaseResponse.fail(101, "没有权限");

//            courseService.updateUserCourse();
        }
        StudentCourseEntity studentCourse = new StudentCourseEntity();
        studentCourse.setId(studentCourseId);
        studentCourse.setStatus(1);
        return courseService.teacherConfirmCourse(studentCourse);
    }


    @PostMapping("/list/student/")
    public BaseResponse<List<StudentCourseEntity>> listStudentCourse(@RequestBody StudentCourseListReq req) {
        UserLoginService userService = userLoginFactory.getUserService();
        BaseResponse<UserEntity> currentLoginUser = userService.getCurrentLoginUser();
        if (currentLoginUser.isFail()) return BaseResponse.fail();
        UserEntity user = currentLoginUser.getData();
        if (user.getRoleId() != 2) {
            return BaseResponse.fail(101, "没有权限");

//            courseService.updateUserCourse();
        }
        return courseService.listStudentCourse(req, user);
    }



    /**
     * 删除课程 支持批量
     * @param courseIds
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResponse deleteCourse(@RequestBody List<Integer> courseIds) {
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
