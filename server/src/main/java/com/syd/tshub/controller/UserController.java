package com.syd.tshub.controller;

import com.syd.tshub.factory.UserLoginFactory;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.request.UserReq;
import com.syd.tshub.service.UserLoginService;
import com.syd.tshub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    private HttpServletRequest request;

    @Autowired
    private UserLoginFactory userLoginFactory;

    @PostMapping("/register")
    public BaseResponse register() {

        return BaseResponse.success();
    }

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody UserReq userReq) {
        // 用户检验

        UserLoginService userService = userLoginFactory.getUserService();
        return userService.login(userReq);
    }

    @DeleteMapping("/logout")
    public BaseResponse logout() {
        String token = request.getHeader("token");
        UserLoginService userService = userLoginFactory.getUserService();
        return userService.logout(token);
    }




}
