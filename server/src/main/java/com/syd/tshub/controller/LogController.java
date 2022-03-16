package com.syd.tshub.controller;

import com.syd.tshub.request.LogListReq;
import com.syd.tshub.request.base.ListBaseReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 日志管理
 *
 * @ClassName LogController
 * @Description TODO
 * @Author syd
 * @Date 2022/3/14 20:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 日志列表
     * @description 暂时只支持分页, 不支持content内容搜索
     * @param listBaseReq
     * @return
     */
    @PostMapping("/list")
    public BaseResponse listLogs(@RequestBody LogListReq listBaseReq) {

        return logService.queryList(listBaseReq);
    }
}
