package com.syd.tshub.service.impl;

import com.syd.tshub.dao.intf.UserDao;
import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.request.UserCreateReq;
import com.syd.tshub.request.UserListReq;
import com.syd.tshub.request.UserReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.UserService;
import com.syd.tshub.wrapper.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

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
//    @Autowired
//    private RoleDao


    @Override
    public UserEntity getUserByLoginUser(UserReq userReq) {
        return userDao.selectUserByLoginUser(userReq);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse createUser(UserCreateReq userReq) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userReq.getUsername());
        userEntity.setPassword(DigestUtils.md5DigestAsHex(userReq.getPassword().getBytes()));
        userEntity.setPhone(userReq.getPhone());
        userEntity.setCreateTime(new Date());
        userEntity.setEnable(1);
        userEntity.setRoleId(userReq.getRoleId());
        userDao.mapper().insert(userEntity);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse listUser(UserListReq userListReq) {
        UserQuery userQuery = new UserQuery()
                .where
                .enable().eq(1)
                .userName().like(userListReq.getUsername())
                .end()
                .orderBy().createTime().desc()
                .end()
                .limit(userListReq.getPageIndex(), userListReq.getPageSize());
        List list = userDao.mapper().listEntity(userQuery);

        return BaseResponse.success(list);
    }

    @Override
    public BaseResponse deleteUsers(List<String> userIds) {
        userDao.mapper().deleteByIds(userIds);
        return BaseResponse.success();
    }
}
