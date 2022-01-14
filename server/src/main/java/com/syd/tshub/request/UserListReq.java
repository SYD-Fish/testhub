package com.syd.tshub.request;

import com.syd.tshub.request.base.ListBaseReq;
import lombok.Data;

/**
 * 类描述：
 *
 * @ClassName UserListReq
 * @Description TODO
 * @Author syd
 * @Date 2022/1/14 21:01
 * @Version 1.0
 */
@Data
public class UserListReq extends ListBaseReq {
    private String username;
}
