package com.sp.test.spring.dependency.injection;

import com.sp.test.ioc.domain.User;

/**
 * {@link User}的Holder类
 * Created by AlexLc on 2020/3/1.
 */
public class UserHolder {

    public UserHolder() {
    }

    private User user;

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
