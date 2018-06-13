package com.example.skkj.intercepters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    LoginInterceptor loginInterceptor;
    @Autowired
    InitDataInterceptor initDataInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        // 映射为 user 的控制器下的所有映射
        registry.addInterceptor(loginInterceptor).addPathPatterns("/web*/*").excludePathPatterns("/index", "/");
        registry.addInterceptor(initDataInterceptor);
        super.addInterceptors(registry);
    }
}
