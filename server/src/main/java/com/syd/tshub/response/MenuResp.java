package com.syd.tshub.response;

import lombok.Data;

import java.util.List;

/**
 * 类描述：
 *
 * @ClassName MenuResp
 * @Description TODO
 * @Author syd
 * @Date 2022/1/3 11:54
 * @Version 1.0
 */
@Data
public class MenuResp {
    private int menuId;
    private int index;
    private String menuName;
    private String path;
    private String icon;
    private List<MenuResp> childs;

}
