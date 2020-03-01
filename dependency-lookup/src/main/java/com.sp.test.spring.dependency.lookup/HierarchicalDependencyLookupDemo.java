package com.sp.test.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 层次性依赖查找示例
 * 1.通过名称来查找
 * 2.通过类型来查找
 * Created by AlexLc on 2020/1/26.
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);


        //1.获取HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前BeanFactory的父级BeanFactory:" + beanFactory.getParentBeanFactory());

        //2.设置Parent BeanFactory
        ConfigurableListableBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前BeanFactory的父级BeanFactory:" + beanFactory.getParentBeanFactory());

        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");
        displayContainsBean(beanFactory, "user");
        displayContainsBean(parentBeanFactory, "user");

        applicationContext.refresh();
        applicationContext.close();
    }

    private static ConfigurableListableBeanFactory createParentBeanFactory(){
        //创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML配置文件ClassPath路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";

        //加载资源
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName){
        System.out.print(String.format("当前BeanFactory[%s]是否包含Bean[name:%s] : %s\n",
                beanFactory, beanName, containsBean(beanFactory, beanName)));
    }

    private static Boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName){
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if(parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if(containsBean(parentHierarchicalBeanFactory, beanName)){
                return true;
            }
        }

        return beanFactory.containsBean(beanName);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName){
        System.out.print(String.format("当前BeanFactory[%s]是否包含Local Bean[name:%s] : %s\n",
                beanFactory, beanName, beanFactory.containsLocalBean(beanName)));
    }
}
