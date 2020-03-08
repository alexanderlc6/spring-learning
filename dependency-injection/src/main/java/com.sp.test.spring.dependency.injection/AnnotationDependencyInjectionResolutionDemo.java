package com.sp.test.spring.dependency.injection;

import com.sp.test.ioc.domain.User;
import com.sp.test.spring.dependency.injection.annotation.InjectedUser;
import com.sp.test.spring.dependency.injection.annotation.MyAutowired;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 注解驱动的依赖注入处理过程Demo
 * Created by AlexLc on 2020/3/3.
 * @see Qualifier
 */
@Configuration
public class AnnotationDependencyInjectionResolutionDemo {
    @Autowired  //依赖查找-延迟查找
    @Lazy
    private User lazyUser; //user,superUser

    @Autowired         //依赖查找(处理)
    private User user; //DependencyDescriptor ->
                       //必须required=true
                       //实时注入(eager=true)
                       //通过类型(User.class)
                       //字段名称("user")
                       //是否是首要的(primary=true)

    @Autowired  //集合类型的依赖注入
    private Map<String, User> users; //user,superUser

    @MyAutowired
    private Optional<User> userOptional;        //superUser

    @Inject     //和@Autowired注入效果相同,BeanPostProcessor均可以处理
    private User injectedUser;

    @InjectedUser
    private User myInjectedUser;

    @Bean(name = "InjectedUserAnnotationBeanPostProcessor")
    public AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        //替换原有的注解处理，使用新注解@InjectedUser
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }

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
        System.out.println("demo user:" + demo.user);                           //输出SuperUser Bean
        System.out.println("demo injectedUser:" + demo.injectedUser);           //输出SuperUser,User Bean

        System.out.println("demo Optional<User>:" + demo.userOptional);         //输出SuperUserBean
        System.out.println("demo myInjectedUser:" + demo.myInjectedUser);       //输出SuperUserBean

        applicationContext.close();
    }
}