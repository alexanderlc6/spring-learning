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
 * 注解驱动的依赖注入处理过程Demo
 * Created by AlexLc on 2020/3/3.
 * @see Qualifier
 */
public class AnnotationDependencyInjectionResolutionDemo {
    @Autowired         //依赖查找(处理)
    private User user; //DependencyDescriptor ->
                       //必须required=true
                       //实时注入(eager=true)
                       //通过类型(User.class)
                       //字段名称("user")
                       //是否是首要的(primary=true)

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册配置类 -> Spring Bean
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        //启动Spring应用上下文
        applicationContext.refresh();

        //依赖查找QualifierAnnotationDependencyInjectionDemo Bean
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println("demo user:" + demo.user);           //输出SuperUser Bean

        applicationContext.close();
    }
}