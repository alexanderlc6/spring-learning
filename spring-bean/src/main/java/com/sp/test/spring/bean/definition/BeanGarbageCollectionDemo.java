package com.sp.test.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean GC示例
 * Created by AlexLc on 2020/2/1.
 */
public class BeanGarbageCollectionDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();

        applicationContext.close();
        System.out.println("Spring应用上下文已关闭...");

        //强制触发GC
        System.gc();
        Thread.sleep(5000L);
    }
}
