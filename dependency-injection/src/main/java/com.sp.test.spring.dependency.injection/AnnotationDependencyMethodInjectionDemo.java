package com.sp.test.spring.dependency.injection;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于Annotation实现依赖方法注入示例
 * 手动注入模式
 * Created by AlexLc on 2020/3/1.
 */
public class AnnotationDependencyMethodInjectionDemo {
    private UserHolder userHolder;

    private UserHolder userHolder2;

    @Autowired
    public void init1(UserHolder userHolder){
        this.userHolder = userHolder;
    }

    @Resource
    public void init2(UserHolder userHolder2){
        this.userHolder2 = userHolder2;
    }


    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册配置类 -> Spring Bean
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        //启动Spring应用上下文
        applicationContext.refresh();

        //依赖查找AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);


        //通过@Autowired字段关联
        UserHolder userHolder = demo.userHolder;
        System.out.println(userHolder);
        System.out.println(demo.userHolder2);

        System.out.println(userHolder == demo.userHolder2);

        applicationContext.close();
    }
}
