package com.syd.tshub.controller;

import com.syd.tshub.factory.UserLoginFactory;
import com.syd.tshub.request.UserCreateReq;
import com.syd.tshub.request.UserListReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.request.UserReq;
import com.syd.tshub.service.UserLoginService;
import com.syd.tshub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse register() {

        return BaseResponse.success();
    }

    /**
     * 登录
     * @param userReq
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody UserReq userReq) {
        UserLoginService userService = userLoginFactory.getUserService();
        return userService.login(userReq);
    }

    /**
     * 登出
     * @return
     */
    @DeleteMapping("/logout")
    public BaseResponse logout() {
        String token = request.getHeader("Authorization");
        UserLoginService userService = userLoginFactory.getUserService();
        return userService.logout(token);
    }

    /**
     * 创建用户
     * @param userReq
     * @return
     */
    @PostMapping("/create")
    public BaseResponse createUser(@RequestBody UserCreateReq userReq) {
        return userService.createUser(userReq);

    }

    /**
     * 用户列表
     * @param userListReq
     * @return
     */
    @PostMapping("/list")
    public BaseResponse listUser(@RequestBody UserListReq userListReq){
        return userService.listUser(userListReq);
    }


    /**
     * 删除用户 支持批量
     * @param userIds
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResponse deleteUser(@RequestBody List<String> userIds) {
        return userService.deleteUsers(userIds);
    }



}
