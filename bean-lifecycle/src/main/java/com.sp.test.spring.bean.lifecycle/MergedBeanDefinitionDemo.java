package com.sp.test.spring.bean.lifecycle;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * BeanDefinition合并示例
 * Created by AlexLc on 2020/10/6.
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";

        //基于ClassPath加载XML资源
        Resource resource = new ClassPathResource(location);
        //指定字符编码UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载的BeanDefinition数量:" + beanCount);

        //通过Bean ID和类型依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);
    }
}
