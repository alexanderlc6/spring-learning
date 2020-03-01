package com.sp.test.spring.dependency.injection;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于{@link org.springframework.beans.factory.Aware}接口回调的依赖注入示例
 * 手动注入模式
 * Created by AlexLc on 2020/3/1.
 */
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware,ApplicationContextAware {

    private static BeanFactory beanFactory;
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        //注册配置类 -> 当前类就是一个Spring Bean
        context.register(AwareInterfaceDependencyInjectionDemo.class);


        //启动Spring应用上下文
        context.refresh();

        System.out.println(beanFactory == context.getBeanFactory());    //true,未注入则报false错误
        System.out.println(applicationContext == context);              //true,未注入则报false错误

        context.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }
}
