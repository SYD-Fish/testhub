package com.syd.tshub.request;

import lombok.Data;

/**
 * 类描述：
 *
 * @ClassName UserCreateReq
 * @Description TODO
 * @Author syd
 * @Date 2022/1/14 20:36
 * @Version 1.0
 */
@Data
public class UserCreateReq extends UserReq{
    private int roleId;
}
