package com.sp.test.ioc.domain;

import com.sp.test.ioc.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AlexLc on 2020/1/25.
 */
public class User implements BeanNameAware {
    private Long id;

    private String name;

    private String address;

    private City city;

    private City[] workCities;

    private List<City> lifeCities;

    private Resource configFileLocation;

    /**
     * 当前Bean的名称
     * transient--不需要反序列化
     */
    private transient String beanName;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
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
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", configFileLocation=" + configFileLocation +
                '}';
    }

    public static User createUser(){
        User user = new User();
        user.setId(12L);
        user.setName("qwwqqvv");
        return user;
    }

    @PostConstruct
    public void init(){
        System.out.println("User Bean [" + beanName + "]初始化...");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("User Bean [" + beanName + "]销毁中...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
