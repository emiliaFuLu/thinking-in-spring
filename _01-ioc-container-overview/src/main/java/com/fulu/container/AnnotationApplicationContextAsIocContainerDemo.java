package com.fulu.container;

import com.fulu.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * {@link org.springframework.context.ApplicationContext} 作为IoC容器
 *
 * @author fulu
 * {@code @date} 2023年02月07日 21:50
 */
@Configuration
public class AnnotationApplicationContextAsIocContainerDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 AnnotationApplicationContextAsIocContainerDemo 作为配置类(Configuration Class)
        applicationContext.register(AnnotationApplicationContextAsIocContainerDemo.class);

        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找集合对象
        lookUpCollectionByType(applicationContext);

        applicationContext.close();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setAge(123);
        user.setName("fulu");
        return user;
    }

    private static void lookUpCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找的User集合：" + users);
        }
    }
}
    