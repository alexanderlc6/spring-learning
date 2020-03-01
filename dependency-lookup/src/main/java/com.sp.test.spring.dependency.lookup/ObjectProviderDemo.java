package com.sp.test.spring.dependency.lookup;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Iterator;

/**
 * 通过ObjectProvider进行依赖查找
 * Created by AlexLc on 2020/2/3.
 */
//@Configuration  非必需的注解
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);

        lookupIfAvailable(applicationContext);
        lookupByStream(applicationContext);

        applicationContext.close();
    }

    private static void lookupByStream(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> stringIterable = objectProvider;
//        for (String str : stringIterable){
//            System.out.println(str);
//        }

        //Stream -> Method Reference
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前User对象：" + user);
    }

    @Bean
    @Primary
    public String hello(){  //当Bean未命名时,Bean名称就是方法名hello
        return "Hello,Kitty";
    }

    @Bean
    public String message(){
        return "message test";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

}
