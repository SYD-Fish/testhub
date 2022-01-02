package com.syd.tshub.service.impl;

import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.request.UserReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.UserLoginService;
import com.syd.tshub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 类描述：
 *
 * @ClassName AbstractUserLogin
 * @Description TODO
 * @Author syd
 * @Date 2022/1/2 9:38
 * @Version 1.0
 */
@Service
public abstract class AbstractUserLogin implements UserLoginService {

    @Autowired
    private UserService userService;

    
    @Override
    public BaseResponse<String> login(UserReq userReq) {
        String password = this.encryptPassword(userReq.getPassword());
        userReq.setPassword(password);
        UserEntity user = getUserInfo(userReq);
        if (user == null) {
            return BaseResponse.fail("用户或密码错误");
        }
        String token = generateToken();
        if (StringUtils.isEmpty(token)) {
            return BaseResponse.fail("生成token失败，请联系管理员");
        }
        return storeAndReturnToken(token, user);
    }

    private UserEntity getUserInfo(UserReq userReq) {
        return userService.getUserByLoginUser(userReq);
    }

    protected abstract BaseResponse<String> storeAndReturnToken(String token, UserEntity user);

    /**
     * 生成token
     * TODO 待优化
     * @return
     */
    protected abstract String generateToken();

    /**
     * 加密暂时使用md5加密 
     * TODO 可优化算法
     * @param password
     * @return
     */
    private String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
