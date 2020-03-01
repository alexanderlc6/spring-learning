package com.sp.test.spring.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于API实现依赖Constructor方法注入示例
 * 手动注入模式
 * Created by AlexLc on 2020/3/1.
 */
public class ApiDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

//        //注册配置类
//        applicationContext.register(ApiDependencySetterInjectionDemo.class);

        //生成UserHolder的BeanDefinition
        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();
        //注册UserHolder的BeanDefinition
        applicationContext.registerBeanDefinition("userHolder", userHolderBeanDefinition);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        //启动Spring应用上下文
        applicationContext.refresh();

        //依赖查找并创建Bean
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }

    /**
     * 为{@link UserHolder}生成BeanDefinition
     * @return
     */
    private static BeanDefinition createUserHolderBeanDefinition(){
        BeanDefinitionBuilder defaultBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        defaultBuilder.addConstructorArgReference("superUser");
        return defaultBuilder.getBeanDefinition();
    }
//    @Bean
//    public UserHolder userHolder(User user){  //superUser -> primary=true
//        UserHolder userHolder = new UserHolder();
//        userHolder.setUser(user);
//        return userHolder;
//    }
}
