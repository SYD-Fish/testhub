package com.syd.tshub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类描述：
 *
 * @ClassName TestHubApplication
 * @Description TODO
 * @Author syd
 * @Date 2021/12/28 21:46
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.syd.tshub.mapper.mapper")
public class TestHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestHubApplication.class, args);
    }
}
