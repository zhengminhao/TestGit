package com.example.demo.properties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SqlSessionFactoryConfiguration {
    @Value("${mybatis_config_file}")
    private  String mybatisConfigurationFilePath;
    @Value("${mapper_path}")
    private  String mapperPath;
    @Value("${entity_package}")
    private  String entityPackage;

    //这里的DataSource不加 @Qualifier("datasource")是会报错的，因为数据源有多个，它不知道引用到哪一个，
    //另外就是在 DataSourceConfiguration类中我们引用了DataSource,也就是存在多个的原因之一
    //故此这里加上别名标签 @Qualifier 来获取到具体的数据源
   @Autowired
   @Qualifier(value = "dataSource")
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatisConfigurationFilePath"));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+mapperPath;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
        return sqlSessionFactoryBean;
    }
}
