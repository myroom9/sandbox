package com.whahn.sandbox.config;

import com.whahn.sandbox.filter.HeaderCheckFilter;
import com.whahn.sandbox.filter.RequestInitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<RequestInitFilter> requestInitFilter() {
        FilterRegistrationBean<RequestInitFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new RequestInitFilter());

        return filterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<HeaderCheckFilter> headerCheckerFilter() {
        FilterRegistrationBean<HeaderCheckFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new HeaderCheckFilter());

        return filterFilterRegistrationBean;
    }
}
