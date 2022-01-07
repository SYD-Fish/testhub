package com.syd.tshub.common.util;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis 操作
 *
 * @ClassName RedisOperateUtil
 * @Description TODO
 * @Author syd
 * @Date 2022/1/5 11:20
 * @Version 1.0
 */
@Component
public class RedisUtil {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private AESUtil aesUtil;

    public void setValue(String key, String value) throws Exception {
        // 对value 进行加密
        String encode = aesUtil.encode(value);
        // 24小时
        long timeout = 60 * 60 * 24;
        stringRedisTemplate.opsForValue().set(key, encode, timeout);
    }

    public String getValue(String key) throws Exception {
        // 对value 进行解密
        return aesUtil.decode(stringRedisTemplate.opsForValue().get(key));
    }


    public void setValue(String key, Object value) throws Exception {
        setValue(key, JSON.toJSONString(value));
    }

    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }
}
