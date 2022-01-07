package com.syd.tshub.controller;

import com.syd.tshub.response.MenuResp;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类描述：
 *
 * @ClassName MenuController
 * @Description TODO
 * @Author syd
 * @Date 2022/1/3 12:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/all")
    public BaseResponse<List<MenuResp>> listMenus() {
        return menuService.getAllMenus();
    }

}
