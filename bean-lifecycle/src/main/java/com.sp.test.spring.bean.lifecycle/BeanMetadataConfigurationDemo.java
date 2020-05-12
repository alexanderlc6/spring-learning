package com.sp.test.spring.bean.lifecycle;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean元信息配置示例
 * Created by AlexLc on 2020/5/1    3.
 */
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //实例化基于Properties资源BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "META-INF/user.properties";

        //基于ClassPath加载properties资源
        Resource resource = new ClassPathResource(location);
        //指定字符编码UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载的BeanDefinition数量:" + beanCount);

        //通过Bean ID和类型依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

    }
}
