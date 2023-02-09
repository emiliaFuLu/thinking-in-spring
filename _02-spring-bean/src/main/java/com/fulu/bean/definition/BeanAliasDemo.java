package com.fulu.bean.definition;

import com.fulu.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fulu
 * {@code @date} 2023年02月09日 22:22
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        // 配置 XML配置文件
        // 启动spring 上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-definitions-context.xml");

        // 通过别名 fulu-user获取曾用名 user 的Bean
        User fuluUser = beanFactory.getBean("user", User.class);
        User user = beanFactory.getBean("fulu-user", User.class);

        System.out.println("fuluUser 是否与 user Bean 相同: " + (user == fuluUser));
    }
}
    