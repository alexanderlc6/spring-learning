package com.sp.test.spring.bean.factory;

import com.sp.test.ioc.domain.User;

/**
 * {@link User} 工厂类
 * Created by AlexLc on 2020/2/1.
 */
public interface UserFactory {

    default User createUser(){
        return User.createUser();
    }
}
