package com.sp.test.spring.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link BeanCreationException} Demo
 * Created by AlexLc on 2020/2/10.
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册BeanDefinition,Bean class是一个POJO普通类,不过在初始化方法回调时抛出异常
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());


        applicationContext.refresh();
        applicationContext.close();
    }

    static class POJO implements InitializingBean {

        @PostConstruct      //CommonAnnotationBeanPostProcessor
        public void init() throws Throwable {   //先与afterPropertiesSet()执行
            throw new Throwable("Init() For purpose...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet() For purpose...");
        }
    }
}
