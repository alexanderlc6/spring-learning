package com.sp.test.spring.bean.definition;

import com.sp.test.spring.bean.factory.DefaultUserFactory;
import com.sp.test.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean初始化示例
 * Created by AlexLc on 2020/2/1.
 */
@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();

        //非延迟初始化是在Spring应用上下文启动完成后被初始化
        System.out.println("Spring应用上下文已启动...");

        //方式1:依赖查找,PostConstructor注解实现初始化
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("Spring应用上下文准备关闭...");

        applicationContext.close();
        System.out.println("Spring应用上下文已关闭...");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")   //方式2
    @Lazy(value = false)   //非延迟初始化
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }
}
