package com.sp.test.spring.bean.definition;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注解BeanDefinition示例
 * Created by AlexLc on 2020/2/1.
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)  //方式3.通过Import导入
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册配置类
        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        //通过BeanDefinition注册API实现
        //1.命名Bean的方式
        registerUserBeanDefinition(applicationContext, "david-user");

        //2.非命名Bean的方式(#count格式:com.sp.test.ioc.domain.User#0=User{xxx})
        registerUserBeanDefinition(applicationContext);

        //启动Spring应用上下文
        applicationContext.refresh();

        System.out.println("Config类型的所有Beans:" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User类型的所有Beans:" + applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    /**
     * 命名Bean的注册方式
     * @param registry
     * @param beanName
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 21L)
                             .addPropertyValue("name", "alex");

        //若BeanName参数存在
        if(StringUtils.hasText(beanName)){
            //命名Bean的方式注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        }else {
            //非命名Bean的方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry){
        registerUserBeanDefinition(registry, null);
    }

    @Component  //方式2.通过Component方式,定义当前作为Spring Bean组件
    public static class Config{
        //方式1.通过@Bean方式
        @Bean(name = {"user","alex-user"})
        public User user(){
            User user = new User();
            user.setId(10L);
            user.setName("fqfff");
            return user;
        }
    }
}
