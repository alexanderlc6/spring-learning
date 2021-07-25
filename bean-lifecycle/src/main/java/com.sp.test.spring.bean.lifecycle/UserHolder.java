package com.sp.test.spring.bean.lifecycle;

import com.sp.test.ioc.domain.User;

/**
 * Created by AlexLc on 2020/10/8.
 */
public class UserHolder {
    private final User user;

    public UserHolder(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
