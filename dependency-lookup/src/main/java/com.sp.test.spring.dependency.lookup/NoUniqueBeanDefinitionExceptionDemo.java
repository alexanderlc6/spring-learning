package com.sp.test.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException} 示例代码
 * Created by AlexLc on 2020/2/9.
 */
public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        applicationContext.refresh();

        try {
            //由于Spring应用上下文存在两个String类型的Bean，通过单一类型查找会抛出异常
            applicationContext.getBean(String.class);
        }catch (NoUniqueBeanDefinitionException e){
            System.err.printf("Spring应用上下文存在%d个%s类型的Bean,具体原因：%s%n",
                    e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage()
                    );
        }


        applicationContext.close();
    }

    @Bean
    public String bean1(){
        return "1";
    }

    @Bean
    public String bean2(){
        return "2";
    }

    @Bean
    public String bean3(){
        return "3";
    }
}
