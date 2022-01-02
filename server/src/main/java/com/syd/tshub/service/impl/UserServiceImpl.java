package com.syd.tshub.service.impl;

import com.syd.tshub.dao.intf.UserDao;
import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.request.UserReq;
import com.syd.tshub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述：
 *
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author syd
 * @Date 2021/12/28 23:13
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public UserEntity getUserByLoginUser(UserReq userReq) {
        return userDao.selectUserByLoginUser(userReq);
    }
}
