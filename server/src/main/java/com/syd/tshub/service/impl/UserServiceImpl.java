package com.syd.tshub.service.impl;

import cn.org.atool.fluent.mybatis.model.StdPagedList;
import com.syd.tshub.dao.intf.UserDao;
import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.request.UserCreateReq;
import com.syd.tshub.request.UserListReq;
import com.syd.tshub.request.UserReq;
import com.syd.tshub.response.UserListResp;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.UserService;
import com.syd.tshub.wrapper.UserQuery;
import com.syd.tshub.wrapper.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

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
        if (StringUtils.isEmpty(userReq.getPassword())) {
            userReq.setPassword("123456");
        }
        userEntity.setPassword(DigestUtils.md5DigestAsHex(userReq.getPassword().getBytes()));
        userEntity.setPhone(userReq.getPhone());
        userEntity.setEmail(userReq.getEmail());
        userEntity.setProfession(userReq.getProfession());
        userEntity.setStage(userReq.getStage());
        userEntity.setCreateTime(new Date());
        userEntity.setEnable(1);
        userEntity.setRoleId(userReq.getRoleId());
        userDao.mapper().insert(userEntity);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse listUser(UserListReq userListReq) {
        UserListResp userListResp = new UserListResp();
        UserQuery userQuery = new UserQuery();
        if (StringUtils.isEmpty(userListReq.getUsername())) {

        } else {
            userQuery.where
                    .userName().like(userListReq.getUsername())
                    .end();
        }
        userQuery.orderBy().createTime().desc()
                .end()
                .limit(( userListReq.getPageIndex() -1) * userListReq.getPageSize(), userListReq.getPageSize());

        return BaseResponse.success(userDao.mapper().stdPagedEntity(userQuery));

    }

    @Override
    public BaseResponse deleteUsers(List<String> userIds) {
        userDao.mapper().deleteByIds(userIds);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse updateUser(UserEntity user) {
//        UserUpdate update = new UserUpdate();
        int i = userDao.mapper().updateById(user);
        return i > 0 ? BaseResponse.success() : BaseResponse.fail();
    }
}
