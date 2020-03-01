package com.sp.test.spring.dependency.lookup;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全依赖查找示例
 * Created by AlexLc on 2020/2/4.
 */
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        applicationContext.refresh();

        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderIfAvailable(applicationContext);

        displayListableBeanFactoryGetBeansOfType(applicationContext);
        displayObjectProviderStreamOps(applicationContext);

        applicationContext.close();
    }

    /**
     * 测试ObjectProvider Stream操作安全性--安全
     * @param applicationContext
     */
    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectProviderStreamOps",() -> userObjectProvider.forEach(System.out::println));
    }

    /**
     * 测试ListableBeanFactory#getBeansOfType方法安全性--安全
     * @param beanFactory
     */
    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeanException("displayListableBeanFactoryGetBeansOfType",
                () -> beanFactory.getBeansOfType(User.class));
    }

    /**
     * 测试ObjectProvider#getIfAvailable方法安全性--安全
     * @param applicationContext
     */
    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectProviderIfAvailable",() -> userObjectProvider.getIfAvailable());
    }

    /**
     * 测试ObjectFactory#getObject方法安全性--非安全
     * @param applicationContext
     */
    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        //ObjectFactory相当于ObjectProvider
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectFactoryGetObject",() -> userObjectFactory.getObject());
    }

    /**
     * 测试BeanFactory#getBean方法安全性--非安全
     * @param beanFactory
     */
    public static void displayBeanFactoryGetBean(BeanFactory beanFactory){
        printBeanException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeanException(String source, Runnable runnable){
        System.err.println("====================================");
        System.err.println("Source from:" + source);
        try {
            runnable.run();
        }catch (BeansException e){
            e.printStackTrace();
        }
    }

}
