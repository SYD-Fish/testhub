package com.syd.tshub.response;

import com.syd.tshub.entity.UserEntity;
import lombok.Data;

import java.util.List;

/**
 * 类描述：
 *
 * @ClassName UserListResp
 * @Description TODO
 * @Author syd
 * @Date 2022/1/14 23:50
 * @Version 1.0
 */
@Data
public class UserListResp {
    private int total;
    private List<UserEntity> userList;

}
