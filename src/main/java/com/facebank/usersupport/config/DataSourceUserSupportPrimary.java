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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * user_primary主库数据源
 * Created by pengyuming on 2017/9/7.
 */
@EnableTransactionManagement
@MapperScan(basePackages = "com.facebank.usersupport.mapper.usersupport",sqlSessionTemplateRef = "sqlSessionTemplateUserSupport")
@Component
public class DataSourceUserSupportPrimary {

    @Bean(name="dataSourceTransactionManagerUserSupport")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManagerUserPrimaryWrite(@Qualifier("dsUserSupport") DruidDataSource datasource){
        return new DataSourceTransactionManager(datasource);
    }

    @Bean(name = "dataSourcePropertiesUserSupport")
    @ConfigurationProperties("usersupport.datasource.tomcat")
    @Primary
    public DataSourceProperties dataSourcePropertiesUserPrimaryWrite(){
        return new DataSourceProperties();
    }

    @Bean(name="dsUserSupport")
    @Primary
    public DruidDataSource dsUserPrimaryWrite(@Qualifier("dataSourcePropertiesUserSupport") DataSourceProperties properties) {
        DruidDataSource dataSource = (DruidDataSource) properties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
        DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }

        return dataSource;
    }

    @Bean(name = "sqlSessionFactoryUserSupport")
    @Primary
    public SqlSessionFactory sqlSessionFactoryUserPrimaryWrite(@Qualifier("dsUserSupport") DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/facebank/usersupport/mapper/usersupport/usersupport/*.xml"));
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        //分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        pageInterceptor.setProperties(properties);
        bean.setPlugins(new Interceptor[]{pageInterceptor});
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplateUserSupport")
    @Primary
    public SqlSessionTemplate sqlSessionTemplateUserPrimaryWrite(@Qualifier("sqlSessionFactoryUserSupport") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
