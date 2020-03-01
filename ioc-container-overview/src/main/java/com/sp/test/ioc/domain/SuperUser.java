package com.sp.test.ioc.domain;

import com.sp.test.ioc.annotation.Super;

/**
 * Created by AlexLc on 2020/1/30.
 */
@Super
public class SuperUser extends User {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "email='" + email + '\'' +
                "} " + super.toString();
    }
}
