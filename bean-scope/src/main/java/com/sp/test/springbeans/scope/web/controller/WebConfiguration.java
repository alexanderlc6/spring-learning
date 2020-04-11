package com.sp.test.springbeans.scope.web.controller;

import com.sp.test.ioc.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Web MVC配置类
 * Created by AlexLc on 2020/4/8.
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    @RequestScope
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("Test");
        return user;
    }
}
