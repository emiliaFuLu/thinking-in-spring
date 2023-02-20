package com.fulu.domain;

import com.fulu.ENUM.City;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

/**
 * @author fulu
 * {@code @date} 2023年01月09日 22:57
 */
public class User {
    private String name;
    private Integer age;

    /**
     * 枚举类型注入
     */
    private City city;

    private City[] workCities;

    private List<City> liveCities;

    /**
     * Spring 类型注入
     */
    private Resource configFileLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

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

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLiveCities() {
        return liveCities;
    }

    public void setLiveCities(List<City> liveCities) {
        this.liveCities = liveCities;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", liveCities=" + liveCities +
                ", configFileLocation=" + configFileLocation +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setName("fulu");
        user.setAge(13);
        return user;
    }
}
    