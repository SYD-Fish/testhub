package com.syd.tshub.request;

import lombok.Data;

/**
 * 类描述：
 *
 * @ClassName UserReq
 * @Description TODO
 * @Author syd
 * @Date 2021/12/31 21:46
 * @Version 1.0
 */
@Data
public class UserReq {
    private String username;
    private String password;
    private int type;
}
