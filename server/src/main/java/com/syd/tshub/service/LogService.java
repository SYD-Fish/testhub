package com.syd.tshub.service;

import com.syd.tshub.request.base.ListBaseReq;
import com.syd.tshub.response.base.BaseResponse;

/**
 * 描述：
 *
 * @ClassName LogService
 * @Description TODO
 * @Author syd
 * @Date 2022/3/14 20:47
 * @Version 1.0
 */
public interface LogService {
    BaseResponse queryList(ListBaseReq listBaseReq);
}
