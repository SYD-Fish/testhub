package com.syd.tshub.service;

import com.syd.tshub.response.MenuResp;
import com.syd.tshub.response.base.BaseResponse;

import java.util.List;

/**
 * 描述：
 *
 * @ClassName MenuService
 * @Description TODO
 * @Author syd
 * @Date 2022/1/3 12:02
 * @Version 1.0
 */
public interface MenuService {
    BaseResponse<List<MenuResp>> getAllMenus();
}
