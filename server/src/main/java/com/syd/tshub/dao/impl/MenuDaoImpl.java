package com.syd.tshub.dao.impl;

import com.syd.tshub.dao.base.MenuBaseDao;
import com.syd.tshub.dao.intf.MenuDao;
import com.syd.tshub.entity.MenuEntity;
import com.syd.tshub.mapper.MenuMapper;
import com.syd.tshub.wrapper.MenuQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * MenuDaoImpl: 数据操作接口实现
 *
 * 这只是一个减少手工创建的模板文件
 * 可以任意添加方法和实现, 更改作者和重定义类名
 * <p/>@author Powered By Fluent Mybatis
 */
@Repository
public class MenuDaoImpl extends MenuBaseDao implements MenuDao {
    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<MenuEntity> selectAllMenus() {
        MenuQuery menuQuery = new MenuQuery()
                .where
                .enable().eq(1)
                .end();
        return menuMapper.listEntity(menuQuery);
    }
}
