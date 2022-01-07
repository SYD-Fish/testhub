package com.syd.tshub.dao.impl;

import com.syd.tshub.dao.base.UserBaseDao;
import com.syd.tshub.dao.intf.UserDao;
import com.syd.tshub.entity.UserEntity;
import com.syd.tshub.mapper.UserMapper;
import com.syd.tshub.request.UserReq;
import com.syd.tshub.wrapper.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDaoImpl: 数据操作接口实现
 *
 * 这只是一个减少手工创建的模板文件
 * 可以任意添加方法和实现, 更改作者和重定义类名
 * <p/>@author Powered By Fluent Mybatis
 */
@Repository
public class UserDaoImpl extends UserBaseDao implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity selectUserByLoginUser(UserReq userReq) {
        UserQuery userQuery = new UserQuery()
                .where
                .userName().eq(userReq.getUsername())
                .and
                .password().eq(userReq.getPassword())
                .and
                .enable().eq(1)
                .end();
        List<UserEntity> userEntities = userMapper.listEntity(userQuery);
        if (userEntities != null && userEntities.size() > 0) {
            return userEntities.get(0);
        }
        return null;
    }
}
