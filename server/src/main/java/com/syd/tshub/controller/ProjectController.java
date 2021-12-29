package com.syd.tshub.controller;

import com.syd.tshub.common.response.BaseResponse;
import com.syd.tshub.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 类描述：
 *
 * @ClassName NameSpaceController
 * @Description TODO
 * @Author syd
 * @Date 2021/12/28 23:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/get/{id}")
    public BaseResponse getProject(@PathVariable("id") String projectId) {
        return null;
    }

    @GetMapping("/list/{namespaceId}")
    public BaseResponse listProject(@PathVariable("namespaceId") String namespaceId) {
        return null;
    }

    @PostMapping("/create")
    public BaseResponse createProject() {
        return null;
    }

    @PutMapping("/update")
    public BaseResponse updateProject() {
        return null;
    }

    @DeleteMapping("/del/{id}")
    public BaseResponse deleteProject(@PathVariable("id") String projectId) {
        return null;
    }


}
