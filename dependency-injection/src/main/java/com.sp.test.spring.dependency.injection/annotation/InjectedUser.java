package com.sp.test.spring.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * 自定义的依赖注入注解
 * Created by AlexLc on 2020/3/9.
 */
@Target({ElementType.CONSTRUCTOR,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
