package com.syd.tshub.service;

import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.request.UserReq;
import com.syd.tshub.response.base.BaseResponse;

/**
 * 描述：
 *
 * @ClassName UserLoginService
 * @Description TODO
 * @Author syd
 * @Date 2022/1/2 9:37
 * @Version 1.0
 */
public interface UserLoginService {
    BaseResponse<String> login(UserReq userReq);

    BaseResponse logout(String token);

    BaseResponse<UserEntity> getCurrentLoginUser();
}
