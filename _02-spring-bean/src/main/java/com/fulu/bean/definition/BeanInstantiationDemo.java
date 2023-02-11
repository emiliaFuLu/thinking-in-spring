package com.fulu.bean.definition;

import com.fulu.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化实例的三种方式
 *
 * @author fulu
 * {@code @date} 2023年02月09日 23:05
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        // 配置XML配置文件
        // 启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        // 通过静态方法进行实例化
        User user = beanFactory.getBean("user-by-static-method", User.class);
        // 通过Bean工厂方法实例化
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        // 通过 FactoryBean 实例化
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(user);
        System.out.println(userByInstanceMethod);
        System.out.println(userByFactoryBean);

        System.out.println(user == userByInstanceMethod);
        System.out.println(user == userByFactoryBean);
    }
}
    