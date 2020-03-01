package com.sp.test.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认{@link UserFactory} 实现
 * Created by AlexLc on 2020/2/1.
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    //1.基于PostConstructor注解实现初始化
    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct:UserFactory初始化中...");
    }

    /**
     * 自定义初始化
     */
    public void initUserFactory(){
        System.out.println("自定义初始化方法initUserFactory():UserFactory初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet():UserFactory初始化中...");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("@PreDestroy:UserFactory销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy():UserFactory销毁中...");
    }

    public void doDestroy(){
        System.out.println("自定义销毁方法 doDestroy():UserFactory销毁中...");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前DefaultUserFactory对象正在被垃圾回收...");
    }
}
