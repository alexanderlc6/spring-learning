package com.sp.test.spring.dependency.injection;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * {@link ObjectProvider}实现延迟注入
 * Created by AlexLc on 2020/3/3.
 * @see Qualifier
 */
public class LazyAnnotationDependencyInjectionDemo {
    @Autowired
    private User user;  //实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider;  //延迟注入，推荐ObjectProvider，可以避免一些异常如NoSuchBeanException等

    @Autowired
    private ObjectFactory<Set<User>> usersObjectFactory;

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册配置类 -> Spring Bean
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        //启动Spring应用上下文
        applicationContext.refresh();

        //依赖查找QualifierAnnotationDependencyInjectionDemo Bean
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        System.out.println("demo user:" + demo.user);           //输出SuperUser Bean
        System.out.println("demo.userObjectProvider:" + demo.userObjectProvider.getObject());    //继承了ObjectFactory方法,输出SuperUser Bean
        System.out.println("demo.usersObjectFactory:" + demo.usersObjectFactory.getObject());

        demo.userObjectProvider.forEach(System.out::println);   //等同于@Qualifier的allUsers模式
        applicationContext.close();
    }
}