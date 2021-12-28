package com.syd.tshub.controller;

import com.syd.tshub.common.response.BaseResponse;
import com.syd.tshub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述：
 *
 * @ClassName UserController
 * @Description TODO
 * @Author syd
 * @Date 2021/12/28 23:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse register() {

        return BaseResponse.success();
    }

    @PostMapping("/login")
    public BaseResponse<String> login() {
        return null;
    }




}
