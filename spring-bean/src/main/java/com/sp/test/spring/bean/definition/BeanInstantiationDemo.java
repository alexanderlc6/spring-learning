package com.sp.test.spring.bean.definition;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化示例
 * Created by AlexLc on 2020/2/1.
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        //配置XML配置文件，启动上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User user = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(user);
        System.out.println(userByInstanceMethod);
        System.out.println(userByFactoryBean);

        System.out.println(user == userByInstanceMethod);
        System.out.println(user == userByFactoryBean);
    }
}
