package com.sp.test.ioc.container;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * BeanFactory作为IOC容器
 * Created by AlexLc on 2020/1/30.
 */
public class BeanFactoryAsIocContainerDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML配置文件ClassPath路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";

        //加载资源
        int beanDefinitionCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean定义加载的数量:" + beanDefinitionCount);

        //依赖查找集合对象
        lookupCollectionObjectByType(beanFactory);
    }

    private static void lookupCollectionObjectByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String,User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User集合对象:" + users);
        }
    }
}
