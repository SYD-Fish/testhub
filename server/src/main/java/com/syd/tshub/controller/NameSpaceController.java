package com.syd.tshub.controller;

import com.syd.tshub.common.response.BaseResponse;
import com.syd.tshub.service.NameSpaceService;
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
@RequestMapping("/namespace")
public class NameSpaceController {

    @Autowired
    private NameSpaceService nameSpaceService;



    @GetMapping("/get/{id}")
    public BaseResponse getNameSpace(@PathVariable("id") String nameSpaceId) {
        return null;
    }

    @GetMapping("/list/{userId}")
    public BaseResponse listNameSpace(@PathVariable("userId") String userId) {
        return null;
    }

    @PostMapping("/create")
    public BaseResponse createNameSpace() {
        return null;
    }

    @PutMapping("/update")
    public BaseResponse updateNameSpace() {
        return null;
    }

    @DeleteMapping("/del/{id}")
    public BaseResponse deleteNameSpace(@PathVariable("id") String nameSpaceId) {
        return null;
    }


}
