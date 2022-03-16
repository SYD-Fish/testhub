package com.syd.tshub.service.impl;

import com.syd.tshub.dao.intf.UserLogDao;
import com.syd.tshub.request.LogListReq;
import com.syd.tshub.request.base.ListBaseReq;
import com.syd.tshub.response.base.BaseResponse;
import com.syd.tshub.service.LogService;
import com.syd.tshub.wrapper.UserLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 类描述：
 *
 * @ClassName LogServiceImpl
 * @Description TODO
 * @Author syd
 * @Date 2022/3/14 20:48
 * @Version 1.0
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private UserLogDao userLogDao;


    @Override
    public BaseResponse queryList(LogListReq listBaseReq) {
        UserLogQuery query = new UserLogQuery();
        if (!StringUtils.isEmpty(listBaseReq.getContent())) {
            query.where.username().eq(listBaseReq.getContent()).end();
        }
        query.limit((listBaseReq.getPageIndex() - 1) * listBaseReq.getPageSize() , listBaseReq.getPageSize());
        return BaseResponse.success(userLogDao.mapper().stdPagedEntity(query));
    }
}
