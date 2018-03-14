package com.facebank.usersupport.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * user_primary读库数据源
 * Created by pengyuming on 2017/9/7.
 */
@MapperScan(basePackages = "com.facebank.usersupport.mapper.usersupport.p2p",sqlSessionTemplateRef = "sqlSessionTemplateP2PRead")
@Component
public class DataSourceP2PPrimaryRead {

    @Bean(name = "dataSourcePropertiesP2PRead")
    @ConfigurationProperties("p2p.datasource.tomcat")
    public DataSourceProperties dataSourcePropertiesUserPrimaryRead(){
        return new DataSourceProperties();
    }

    @Bean(name= "dsP2PRead")
    public DruidDataSource dsUserPrimaryRead(@Qualifier("dataSourcePropertiesP2PRead") DataSourceProperties properties) {
        DruidDataSource dataSource = (DruidDataSource) properties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
        DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }

        return dataSource;
    }

    @Bean(name = "sqlSessionFactoryP2PRead")
    public SqlSessionFactory sqlSessionFactoryUserPrimaryRead(@Qualifier("dsP2PRead") DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/facebank/usersupport/mapper/usersupport/p2p/*.xml"));
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));

        //分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        pageInterceptor.setProperties(properties);
        bean.setPlugins(new Interceptor[]{pageInterceptor});
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplateP2PRead")
    public SqlSessionTemplate sqlSessionTemplateUserPrimaryRead(@Qualifier("sqlSessionFactoryP2PRead") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
