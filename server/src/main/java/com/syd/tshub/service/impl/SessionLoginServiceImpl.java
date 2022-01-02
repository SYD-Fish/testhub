package com.syd.tshub.service.impl;

import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 类描述：
 *
 * @ClassName SessionLoginServiceImpl
 * @Description TODO
 * @Author syd
 * @Date 2022/1/2 9:37
 * @Version 1.0
 */
@Service("sessionLoginService")
public class SessionLoginServiceImpl extends AbstractUserLogin implements UserLoginService {

    @Autowired
    private HttpServletRequest request;

    private static final String session_token = "se_token";


    @Override
    protected BaseResponse<String> storeAndReturnToken(String token, UserEntity user) {
        request.getSession().setAttribute(token, user);
        return BaseResponse.success(token);
    }

    @Override
    protected String generateToken() {
        return session_token;
    }

    @Override
    public BaseResponse logout(String token) {
        request.getSession().removeAttribute(token);
        return BaseResponse.success();
    }
}
