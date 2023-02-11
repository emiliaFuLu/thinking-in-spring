package com.fulu.domain;

/**
 * @author fulu
 * {@code @date} 2023年01月09日 22:57
 */
public class User {
    private String name;
    private Integer age;

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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    public static User createUser() {
        User user = new User();
        user.setName("fulu");
        user.setAge(13);
        return user;
    }
}
    