package com.sp.test.springbeans.scope;


import com.sp.test.ioc.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * Bean的作用域示例
 * Created by AlexLc on 2020/3/29.
 */
public class BeanScopeDemo implements DisposableBean{

    @Bean
    //默认Scope就是Singleton
    public static User singletonUser(){
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
        return createUser();
    }

    public static User createUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;    //Resolvable Dependency

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册配置类 -> Spring Bean
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean名称：%s在初始化后回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        //启动Spring应用上下文
        applicationContext.refresh();

        //结论一
        //Singleton Bean无论依赖查找还是依赖注入均为同一个对象
        //Prototype Bean无论依赖查找还是依赖注入均为新生成的对象

        //结论二
        //如果依赖注入集合类型对象，Singleton Beanhe和Prototype Bean均会存在一个
        //Prototype Bean有别于其他地方的依赖注入

        //结论三
        //无论是Singleton Bean还是Prototype Bean均会执行初始化方法回调，不过仅Singleton会执行销毁,因为Spring容器无法管理
        // Prototype Bean的完整生命周期、无法记录实例的存在，只是可利用BeanPostProcessor清扫工作
        scopedBeansByLookup(applicationContext);
        scopedBeansByInjection(applicationContext);

        applicationContext.close();
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            //singletonUser是共享的Bean对象
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser =" + singletonUser);

            //prototypeUser是每次依赖查找均生成了新的Bean对象
            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser" + prototypeUser);
        }
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext){
        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("beanScopeDemo.singletonUser:" + beanScopeDemo.singletonUser);
        System.out.println("beanScopeDemo.singletonUser:" + beanScopeDemo.singletonUser1);
        System.out.println("beanScopeDemo.prototypeUser:" + beanScopeDemo.prototypeUser);
        System.out.println("beanScopeDemo.prototypeUser1:" + beanScopeDemo.prototypeUser1);
        System.out.println("beanScopeDemo.prototypeUser2:" + beanScopeDemo.prototypeUser2);

        System.out.println("beanScopeDemo.users:" + beanScopeDemo.users);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("当前BeanScopeDemo Bean正在销毁中...");

        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        //获取BeanDefinition
        for (Map.Entry<String, User> entry : this.users.entrySet()){
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if(beanDefinition.isPrototype()){   //如果当前Bean是Prototype的Scope
                User user = entry.getValue();
                user.destroy();
            }
        }

        System.out.println("当前BeanScopeDemo Bean销毁完成");
    }
}
