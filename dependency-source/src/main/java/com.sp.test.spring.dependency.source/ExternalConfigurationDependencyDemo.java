package com.sp.test.spring.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;


/**
 * 外部化配置作为依赖来源示例
 * Created by AlexLc on 2020/4/6.
 */
@Configuration
@PropertySource(value = "META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigurationDependencyDemo {
    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册配置类 -> Spring Bean
        applicationContext.register(ExternalConfigurationDependencyDemo.class);

        //启动Spring应用上下文
        applicationContext.refresh();

        //依赖查找ExternalConfigurationDependencyDemo Bean
        ExternalConfigurationDependencyDemo demo = applicationContext.getBean(ExternalConfigurationDependencyDemo.class);
        System.out.println("demo.id=" + demo.id);
        System.out.println("demo.name=" + demo.name);
        System.out.println("demo.resource=" + demo.resource);
        applicationContext.close();
    }
}
