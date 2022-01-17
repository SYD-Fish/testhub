package com.syd.tshub.request.base;

import lombok.Data;

/**
 * 类描述：
 *
 * @ClassName ListBaseReq
 * @Description TODO
 * @Author syd
 * @Date 2022/1/14 21:01
 * @Version 1.0
 */
@Data
public abstract class ListBaseReq {
    private int pageIndex;
    private int pageSize;
    private String content;
}
