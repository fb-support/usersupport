package com.facebank.usersupport.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

/**
 * Created by pengyuming on 2017/9/7.
 */
@Component
public class FilterConfig {

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }



    /*@Bean
    public FilterRegistrationBean createEncodingFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new CharacterEncodingFilter());
        filter.addUrlPatterns("*//*");
        filter.setName("encodingFilter");
        filter.setOrder(2);
        filter.addInitParameter("encoding","UTF-8");
        filter.addInitParameter("forceEncoding","true");
        return filter;
    }*/
}
