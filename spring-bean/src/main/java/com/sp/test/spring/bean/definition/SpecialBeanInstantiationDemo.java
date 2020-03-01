package com.sp.test.spring.bean.definition;

import com.sp.test.ioc.domain.User;
import com.sp.test.ioc.repository.UserRepository;
import com.sp.test.spring.bean.factory.DefaultUserFactory;
import com.sp.test.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.soap.SAAJResult;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Bean实例化示例
 * Created by AlexLc on 2020/2/1.
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        //配置XML配置文件，启动上下文
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        //通过ApplicationContext获取实现
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        //ServiceLoader加载方式1
        demoServiceLoader();

        //ServiceLoader加载方式2
        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);

        //方式3:通过AutowireCapableBeanFactory创建UserFactory对象
//        UserFactory userFactory = beanFactory.createBean(UserFactory.class);//ERROR:Failed to instantiate,创建必须是具体类
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        userFactory.createUser();

        //方式4:BeanDefinitionRegistry,见AnnotationBeanDefinitionDemo示例
    }

    public static void demoServiceLoader(){
        ServiceLoader<UserFactory> serviceLoader =
                ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    /**
     * 从ServiceLoaderFactoryBean创建一个ServiceLoader<UserFactory>对象
     * @param serviceLoader
     */
    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader){
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
