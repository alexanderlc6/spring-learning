package com.sp.test.springbeans.scope;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


/**
 * 自定义Scope(@link ThreadLocalScope)示例
 * Created by AlexLc on 2020/5/10.
 */
public class ThreadLocalScopeDemo {
    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME) //说明当前Bean被声明为Scope
    public User user(){
        return createUser();
    }

    public static User createUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {

        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册配置类 -> Spring Bean
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            //注册Scope
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        //启动Spring应用上下文
        applicationContext.refresh();

        scopedBeansLookup(applicationContext);
        applicationContext.close();
    }

    private static void scopedBeansLookup(AnnotationConfigApplicationContext applicationContext){
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                //user是共享的Bean对象
                User user = applicationContext.getBean("user", User.class);
                System.out.printf("[Thread id:%d] user =%s%n",Thread.currentThread().getId(), user);
            });

            thread.start();

            //强制线程执行完成
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext){

    }
}
