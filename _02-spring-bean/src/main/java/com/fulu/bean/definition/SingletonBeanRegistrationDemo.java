package com.fulu.bean.definition;

import com.fulu.bean.factory.DefaultUserFactory;
import com.fulu.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单例 Bean 注册实例
 *
 * @author fulu
 * {@code @date} 2023年02月11日 17:23
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 创建外部 UserFactory 对象
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        // 注册外部单例对象
        beanFactory.registerSingleton("userFactory", userFactory);

        // 启动 Spring 应用上下文
        applicationContext.refresh();


        // 通过依赖查找的方式来获取 UserFactory
        UserFactory userFactoryBeanLookup = beanFactory.getBean("userFactory", UserFactory.class);
        System.out.println("userFactory == userFactoryBeanLookup : " + (userFactory == userFactoryBeanLookup));

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }
}
    