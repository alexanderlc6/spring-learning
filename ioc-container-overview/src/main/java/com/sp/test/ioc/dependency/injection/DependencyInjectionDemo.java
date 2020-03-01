package com.sp.test.ioc.dependency.injection;

import com.sp.test.ioc.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖查找
 * 1.通过名称来查找
 * 2.通过类型来查找
 * Created by AlexLc on 2020/1/26.
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        //配置XML配置文件，启动上下文
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        //依赖来源1：自定义Bean
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
//        System.out.println(userRepository.getUsers());

        //依赖来源2：获取对象--依赖注入(内建依赖)
        System.out.println(userRepository.getBeanFactory());
        //确定是不是和外部的BeanFactory是同一个,结果为false,即userRepository.getBeanFactory()并不是普通Bean
//        System.out.println(userRepository.getBeanFactory() == beanFactory);

        ObjectFactory userFactory = userRepository.getObjectFactory();
        System.out.println(userFactory.getObject());

        //确定是不是和当前beanFactory实例一样,结果为true,说明XML配置ObjectFactory在Auto-wiring的时候帮我们注入了一个ApplicationContext
        System.out.println(userFactory.getObject() == applicationContext);

        //依赖查找(错误示例)
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        //依赖来源3：容器内建依赖
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取Environment类型的Bean:" + environment);
    }

    private static void whoIsIocContainer(UserRepository userRepository, ApplicationContext applicationContext){
        //ConfigurableApplicationContext <- ApplicationContext <- BeanFactory
        //ConfigurableApplicationContext#getBeanFactory()

        //确定是不是和外部的BeanFactory是同一个,结果为false,即userRepository.getBeanFactory()并不是普通Bean
        System.out.println(userRepository.getBeanFactory() == applicationContext);

        //ApplicationContext is BeanFactory


    }
}
