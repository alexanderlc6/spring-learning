package com.sp.test.spring.bean.definition;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition}
 * Created by AlexLc on 2020/1/31.
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

        //通过属性Set方式注入
        beanDefinitionBuilder.addPropertyValue("id", "15L")
                             .addPropertyValue("name", "Alex");

        //获取BeanDefinition实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //BeanDefinition并非终态，可自定义修改
//        beanDefinition.setDependsOn();

        //2.通过AbstractBeanDefinition以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置Bean类型
        genericBeanDefinition.setBeanClass(User.class);
        //通过MutablePropertyValues批量操作属性
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("id", "18L")
                             .add("name", "David");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }

}
