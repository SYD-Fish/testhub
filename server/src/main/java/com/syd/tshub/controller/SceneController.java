package com.syd.tshub.controller;

import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.SceneService;
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
@RequestMapping("/scene")
public class SceneController {

    @Autowired
    private SceneService sceneService;

    @GetMapping("/get/{id}")
    public BaseResponse getScene(@PathVariable("id") String sceneId) {
        return null;
    }

    @GetMapping("/list/{projectId}")
    public BaseResponse listScene(@PathVariable("projectId") String projectId) {
        return null;
    }

    @PostMapping("/create")
    public BaseResponse createScene() {
        return null;
    }

    @PutMapping("/update")
    public BaseResponse updateScene() {
        return null;
    }

    @DeleteMapping("/del/{id}")
    public BaseResponse deleteScene(@PathVariable("id") String sceneId) {
        return null;
    }


}
