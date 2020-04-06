package com.sp.test.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency作为依赖来源
 * Created by AlexLc on 2020/4/4.
 */
public class ResolvableDependencySourceDemo {
    @Autowired
    private String value;

    @PostConstruct
    public void init(){
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencySourceDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            //注册Resolvable Dependency
            beanFactory.registerResolvableDependency(String.class, "Hello world");
        });
        applicationContext.refresh();

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
//        if(beanFactory instanceof ConfigurableListableBeanFactory){
//            ConfigurableListableBeanFactory configurableListableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
//
//        }

        applicationContext.close();
    }
}
