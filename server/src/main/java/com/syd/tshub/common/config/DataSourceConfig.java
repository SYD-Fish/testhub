package com.syd.tshub.common.config;

import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 类描述：
 *
 * @ClassName DataSourceConfig
 * @Description TODO
 * @Author syd
 * @Date 2021/12/31 21:58
 * @Version 1.0
 */
//@ComponentScan(basePackages = "com.syd.tshub.mapper")

@Configuration
public class DataSourceConfig {
    /**
     * 设置dataSource属性
     *
     * @return
     */
//    @Bean
//    public DataSource dataSource() {
//
//
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/fluent_mybatis?useUnicode=true&characterEncoding=utf8");
//        dataSource.setUsername("root");
//        dataSource.setPassword("password");
//        return dataSource;
//    }
//
//    /**
//     * 定义mybatis的SqlSessionFactoryBean
//     *
//     * @param dataSource
//     * @return
//     */
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean;
//    }

    @Bean
    public MapperFactory mapperFactory() {
        return new MapperFactory();
    }
}
