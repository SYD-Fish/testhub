package com.syd.tshub.dao.intf;

import cn.org.atool.fluent.mybatis.base.IBaseDao;
import com.syd.tshub.entity.MenuEntity;

import java.util.List;

/**
 * MenuDao: 数据操作接口
 *
 * 这只是一个减少手工创建的模板文件
 * 可以任意添加方法和实现, 更改作者和重定义类名
 * <p/>@author Powered By Fluent Mybatis
 */
public interface MenuDao extends IBaseDao<MenuEntity> {
    List<MenuEntity> selectAllMenus();
}
