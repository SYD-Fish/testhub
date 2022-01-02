package com.syd.tshub.service;

import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.request.UserReq;

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
}
