package com.syd.tshub.request;

import com.syd.tshub.request.base.ListBaseReq;
import lombok.Data;

/**
 * 类描述：
 *
 * @ClassName StudentCourseListReq
 * @Description TODO
 * @Author syd
 * @Date 2022/3/12 21:19
 * @Version 1.0
 */
@Data
public class StudentCourseListReq extends ListBaseReq {
    /** 0 未确认的选课, 1 已确认的选课 */
    private Integer status;
}
