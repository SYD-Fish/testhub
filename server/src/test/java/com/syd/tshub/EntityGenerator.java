package com.syd.tshub;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
import org.junit.Test;

/**
 * 类描述：
 *
 * @ClassName EntityGenerator
 * @Description TODO
 * @Author syd
 * @Date 2021/11/9 17:42
 * @Version 1.0
 */
public class EntityGenerator {
    // 数据源 url
    static final String url = "jdbc:mysql://rm-uf6n6u176m4zxbd75xo.mysql.rds.aliyuncs.com:3306/test_hub?useUnicode=true&characterEncoding=utf8";
    // 数据库用户名
    static final String username = "root";
    // 数据库密码
    static final String password = "Root_1234";

    @Test
    public void generate() throws Exception {
        // 引用配置类，build方法允许有多个配置类
        FileGenerator.build(Empty.class);
    }

    @Tables(
            // 设置数据库连接信息
            url = url, username = username, password = password,
            // 设置entity类生成src目录, 相对于 user.dir
            srcDir = "src/main/java",
            // 设置entity类的package值
            basePack = "com.syd.tshub",
            // 设置dao接口和实现的src目录, 相对于 user.dir
            daoDir = "src/main/java",
            tablePrefix = "t_",
            // 设置哪些表要生成Entity文件
            tables = {@Table(value = {"t_user", "t_menu", "t_role"}, version = "lock_version")}
    )
    static class Empty { //类名随便取, 只是配置定义的一个载体
    }
}
