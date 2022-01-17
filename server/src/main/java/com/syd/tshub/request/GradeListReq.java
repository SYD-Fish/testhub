package com.syd.tshub.request;

import com.syd.tshub.request.base.ListBaseReq;
import lombok.Data;

/**
 * 类描述：
 *
 * @ClassName GradeListReq
 * @Description TODO
 * @Author syd
 * @Date 2022/1/17 20:34
 * @Version 1.0
 */
@Data
public class GradeListReq extends ListBaseReq {
    private Integer courseId;
}
