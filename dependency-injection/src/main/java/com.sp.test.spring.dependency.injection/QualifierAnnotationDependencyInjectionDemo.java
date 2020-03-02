package com.sp.test.spring.dependency.injection;

import com.sp.test.ioc.domain.User;
import com.sp.test.spring.dependency.injection.annotation.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link org.springframework.beans.factory.annotation.Qualifier}注解依赖注入
 * Created by AlexLc on 2020/3/3.
 * @see org.springframework.beans.factory.annotation.Qualifier
 */
public class QualifierAnnotationDependencyInjectionDemo {
    @Autowired
    private User user;  //默认指向SuperUser因为primary设为了true

    @Autowired
    @Qualifier("user")  //指定Bean的名称或ID
    private User namedUser;

    /** 整体应用上下文存在4个User类型的Bean:superUser,user,user1=> @Qualifier,user2=> @Qualifier */

    @Autowired
    private Collection<User> allUsers;  //2 Beans = user + superUser，不会包含带有@Qualifier注解的Bean

    @Autowired
    @Qualifier  //有此注解会进行分组
    private Collection<User> qualifiedUsers; //2 Beans = user1 + user2 + user3 + user4

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;  //2 Beans = user3 + user4

    @Bean
    @Qualifier  //进行逻辑分组
    public User user1(){
        return createUser(7L);
    }

    @Bean
    @Qualifier
    public User user2(){
        return createUser(8L);
    }

    @Bean
    @UserGroup
    public User user3(){
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4(){
        return createUser(10L);
    }

    private static User createUser(Long id){
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册配置类 -> Spring Bean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        //启动Spring应用上下文
        applicationContext.refresh();

        //依赖查找QualifierAnnotationDependencyInjectionDemo Bean
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        System.out.println("demo user:" + demo.user);           //输出SuperUser Bean
        System.out.println("demo.namedUser" + demo.namedUser);  //输出User Bean
        System.out.println("demo.allUsers" + demo.allUsers);    //输出user,superUser
        System.out.println("demo.qualifiedUsers" + demo.qualifiedUsers);    //输出user1,user2,user3,user4
        System.out.println("demo.groupedUsers" + demo.groupedUsers);        //输出user3,user4

        applicationContext.close();
    }
}