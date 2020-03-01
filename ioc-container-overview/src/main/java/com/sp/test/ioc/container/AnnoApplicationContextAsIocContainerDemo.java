package com.sp.test.ioc.container;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 注解 (@link ApplicationContext) 作为IOC容器
 * Created by AlexLc on 2020/1/30.
 */
//@Configuration
public class AnnoApplicationContextAsIocContainerDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        //将当前类作为配置类
        annotationConfigApplicationContext.register(AnnoApplicationContextAsIocContainerDemo.class);

        //启动应用上下文
        annotationConfigApplicationContext.refresh();

        //依赖查找集合对象
        lookupCollectionObjectByType(annotationConfigApplicationContext);

        //关闭应用上下文
        annotationConfigApplicationContext.close();
    }

    @Bean
    public User user(){
        User user = new User();
        user.setId(10L);
        user.setName("fqfff");
        return user;
    }

    private static void lookupCollectionObjectByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String,User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User集合对象:" + users);
        }
    }
}
