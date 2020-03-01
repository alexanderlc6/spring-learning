package com.sp.test.spring.bean.definition;

import com.sp.test.spring.bean.factory.DefaultUserFactory;
import com.sp.test.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体Bean注册示例
 * Created by AlexLc on 2020/2/1.
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //创建一个UserFactory外部对象
        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        //注册外部单例对象
        singletonBeanRegistry.registerSingleton("userFactory", userFactory);
        applicationContext.refresh();

        //通过依赖查找的方式来获取UserFactory对象
        UserFactory userFactoryByLookup = applicationContext.getBean("userFactory", UserFactory.class);
        System.out.println("userFactory == userFactoryByLookup:" + (userFactory == userFactoryByLookup));

        applicationContext.close();
        System.out.println("Spring应用上下文已关闭...");
    }
}
