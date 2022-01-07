package com.syd.tshub.service.impl;

import com.syd.tshub.dao.intf.MenuDao;
import com.syd.tshub.entity.MenuEntity;
import com.syd.tshub.response.MenuResp;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 *
 * @ClassName MenuServiceImpl
 * @Description TODO
 * @Author syd
 * @Date 2022/1/3 12:02
 * @Version 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    /**
     * 获取所有的菜单
     * @return
     */
    @Override
    public BaseResponse<List<MenuResp>> getAllMenus() {
        List<MenuEntity> menuList = menuDao.selectAllMenus();
        if (menuList != null && !menuList.isEmpty()) {
            List<MenuResp> menuResps = new ArrayList<>();
            menuList.forEach(menu -> {
                if (menu.getParentId() == null) {
                    MenuResp menuResp = buildMenu(menu);
//                    menuList.remove(menu);
                    recursionMenu(menuResp, menuList);
                    menuResps.add(menuResp);
                }
            });
            return BaseResponse.success(menuResps);
        } else {
            return BaseResponse.fail("未配置菜单");
        }
    }

    /**
     * 递归算法获取菜单的子菜单
     * @param menuResp
     * @param menuList
     */
    private void recursionMenu(MenuResp menuResp, List<MenuEntity> menuList) {
        List<MenuResp> childMenus = new ArrayList<>();
        menuList.forEach(menu -> {
            if (ObjectUtils.nullSafeEquals(menu.getParentId(), menuResp.getMenuId())) {
                // menuResp的
                MenuResp childMenu = buildMenu(menu);
//                menuList.remove(menu);
                recursionMenu(childMenu, menuList);
                childMenus.add(childMenu);
            }
        });
        menuResp.setChilds(childMenus);
    }

    private MenuResp buildMenu(MenuEntity menu) {
        MenuResp menuResp = new MenuResp();
        menuResp.setMenuId(menu.getMenuId());
        menuResp.setMenuName(menu.getMenuName());
        menuResp.setIndex(menu.getIndex());
        menuResp.setPath(menu.getPath());
        menuResp.setIcon(menu.getIcon());
        return menuResp;
    }
}
