package com.sp.test.ioc.domain;

import com.sp.test.ioc.enums.City;
import org.springframework.core.io.Resource;

/**
 * Created by AlexLc on 2020/1/25.
 */
public class User {

    private Long id;

    private String name;

    private String address;

    private City city;

    private Resource configFileLocation;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
//
//    @Component
//    private static class Config {
//        public static User INSTANCE = new User();
//
//        @Bean("user1")
//        public User createUser(){
//            User user = new User();
//            user.setId(1);
//            user.setName("test1");
//            user.setAddress("testAddr1");
//
//            return user;
//        }
//    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city=" + city +
                ", configFileLocation=" + configFileLocation +
                '}';
    }

    public static User createUser(){
        User user = new User();
        user.setId(12L);
        user.setName("qwwqqvv");
        return user;
    }
}
