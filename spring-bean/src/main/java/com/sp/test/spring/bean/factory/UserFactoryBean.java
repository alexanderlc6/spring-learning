package com.sp.test.spring.bean.factory;

import com.sp.test.ioc.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by AlexLc on 2020/2/1.
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
