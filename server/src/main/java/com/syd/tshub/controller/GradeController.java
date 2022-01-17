package com.syd.tshub.controller;

import com.syd.tshub.entity.GradeEntity;
import com.syd.tshub.entity.GradeEntity;
import com.syd.tshub.request.GradeListReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类描述：
 *
 * @ClassName GradeController
 * @Description TODO
 * @Author syd
 * @Date 2022/1/17 20:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/Grade")
public class GradeController {
    
    @Autowired
    private GradeService gradeService;

    /**
     * 创建成绩 支持批量创建
     * @param grades
     * @return
     */
    @PostMapping("/create")
    public BaseResponse createGrades(@RequestBody List<GradeEntity> grades) {
        return gradeService.createGrades(grades);

    }

    /**
     * 成绩列表
     * @param gradeListReq
     * @return
     */
    @PostMapping("/list")
    public BaseResponse listGrade(@RequestBody GradeListReq gradeListReq){
        return gradeService.listGrade(gradeListReq);
    }


    /**
     * 删除成绩 支持批量
     * @param gradeIds
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResponse deleteGrade(@RequestBody List<String> gradeIds) {
        return gradeService.deleteGrade(gradeIds);
    }

    /**
     * 更新课程
     * @param grade
     * @return
     */
    @PutMapping("/update")
    public BaseResponse updateGrade(@RequestBody GradeEntity grade) {
        return gradeService.updateGrade(grade);
    }
    
}
