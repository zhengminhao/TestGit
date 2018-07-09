package com.example.demo.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * 获取连接池资源
 */
@Configuration
//配置mybatis中的mapper ，扫描该包下路径
@MapperScan("com.example.demo.dao")
public class DataSourceConfiguration {
    @Value("${datasource.driver-class-name}")
    private String jdbcDriver;
    @Value("${datasource.url}")
    private String jdbcUrl;
    @Value("${datasource.username}")
    private String jdbcUser;
    @Value("${datasource.password}")
    private String jdbcPassword;

    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUser);
        dataSource.setPassword(jdbcPassword);
        //设置不自动提交
        dataSource.setAutoCommitOnClose(false);
        return  dataSource;
    }
}
