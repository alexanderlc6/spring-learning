package com.sp.test.spring.bean.lifecycle;

import com.sp.test.ioc.domain.SuperUser;
import com.sp.test.ioc.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * Bean实例化生命周期示例
 * Created by AlexLc on 2020/10/7.
 */
public class BeanInstantiationLifecycleDemo  {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //添加自定义的BeanPostProcessor实现(示例)
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = { "META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml" };

//        //基于ClassPath加载XML资源
//        Resource resource = new ClassPathResource(location);
//        //指定字符编码UTF-8
//        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanCount = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载的BeanDefinition数量:" + beanCount);

        //通过Bean ID和类型依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals(beanName, "superUser") && SuperUser.class.equals(beanClass)){
                //把先前配置好的superUser Bean覆盖
                return new SuperUser();
            }

            //保持Spring IOC容器的实例化操作
            return null;
        }
    }
}
