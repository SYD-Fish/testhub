package com.syd.tshub.service.impl;

import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.UserLoginService;
import org.springframework.stereotype.Service;

/**
 * 类描述：
 *
 * @ClassName RedisLoginServiceImpl
 * @Description TODO
 * @Author syd
 * @Date 2022/1/2 9:42
 * @Version 1.0
 */
@Service("redisLoginService")
public class RedisLoginServiceImpl extends AbstractUserLogin implements UserLoginService {



    @Override
    protected BaseResponse<String> storeAndReturnToken(String token, UserEntity user) {

        return null;
    }

    @Override
    protected String generateToken() {
        return null;
    }

    @Override
    public BaseResponse logout(String token) {
        return null;
    }
}
