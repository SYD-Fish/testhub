package com.syd.tshub.service.impl;

import com.syd.tshub.common.util.RedisUtil;
import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    private Logger logger = LoggerFactory.getLogger(RedisLoginServiceImpl.class);

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected BaseResponse<String> storeAndReturnToken(String token, UserEntity user) {
        try {
            redisUtil.setValue(token, user);
        } catch (Exception e) {
            logger.error("redis 上传错误：", e);
        }
        return BaseResponse.success(token);
    }

    @Override
    protected String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public BaseResponse logout(String token) {
        redisUtil.remove(token);
        return BaseResponse.success();
    }
}
