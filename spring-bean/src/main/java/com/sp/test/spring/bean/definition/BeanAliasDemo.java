package com.sp.test.spring.bean.definition;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean别名示例
 * Created by AlexLc on 2020/2/1.
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        //配置XML配置文件，启动上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        //通过别名获取曾用名user的Bean
        User user = beanFactory.getBean("user", User.class);
        User alexUser = beanFactory.getBean("alex-user", User.class);
        System.out.println("Alex user是否与user Bean相同" + (user == alexUser));
    }
}
