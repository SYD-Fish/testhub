package com.syd.tshub.factory;

import com.syd.tshub.service.UserLoginService;
import com.syd.tshub.service.impl.SessionLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 类描述：
 *
 * @ClassName UserLoginFactory
 * @Description TODO
 * @Author syd
 * @Date 2022/1/2 9:35
 * @Version 1.0
 */
@Component
public class UserLoginFactory {

    @Value("${user.login.type: sessionLoginService}")
    private String userLoginType;

    @Resource
    private ApplicationContext applicationContext;


    /**
     * 工厂模式获取用户登录服务
     * @return
     */
    public UserLoginService getUserService() {
        return applicationContext.getBean(userLoginType, UserLoginService.class);
    }
}
