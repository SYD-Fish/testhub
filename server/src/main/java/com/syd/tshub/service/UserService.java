package com.syd.tshub.service;

import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.request.UserCreateReq;
import com.syd.tshub.request.UserListReq;
import com.syd.tshub.request.UserReq;
import com.syd.tshub.response.base.BaseResponse;

import java.util.List;

/**
 * 描述：
 *
 * @ClassName UserService
 * @Description TODO
 * @Author syd
 * @Date 2021/12/28 23:13
 * @Version 1.0
 */
public interface UserService {
    UserEntity getUserByLoginUser(UserReq userReq);

    BaseResponse createUser(UserCreateReq userReq);

    BaseResponse listUser(UserListReq userListReq);

    BaseResponse deleteUsers(List<String> userIds);
}
