package com.sp.test.spring.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanInstantiationException} Demo
 * Created by AlexLc on 2020/2/10.
 */
public class BeanInstantiationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册BeanDefinition,Bean class是一个CharSequence接口
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());


        applicationContext.refresh();
        applicationContext.close();
    }
}
