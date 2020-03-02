package com.sp.test.ioc.dependency.lookup;

import com.sp.test.ioc.annotation.Super;
import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找
 * 1.通过名称来查找
 * 2.通过类型来查找
 * Created by AlexLc on 2020/1/26.
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        //配置XML配置文件，启动上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);

        lookupSimpleObjectByType(beanFactory);
        lookupCollectionObjectByType(beanFactory);
        lookupByAnnotationType(beanFactory);
    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String,User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注@Super所有的User集合对象:" + users);
        }
    }

    private static void lookupCollectionObjectByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String,User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User集合对象:" + users);
        }
    }

    private static void lookupSimpleObjectByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("按Bean类型实时查找:" + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>)beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找:" + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory){
        User user = (User)beanFactory.getBean("user");
        System.out.println("实时查找:" + user);
    }
}
