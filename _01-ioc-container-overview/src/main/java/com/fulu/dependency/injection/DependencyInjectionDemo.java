package com.fulu.dependency.injection;

import com.fulu.repository.UserRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入
 *
 * @author fulu
 * {@code @date} 2023年01月09日 22:57
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // 配置 XML配置文件
        // 启动spring 上下文
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        // 自定义 Bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

//        System.out.println(userRepository.getUsers());


        // 依赖注入 （内建依赖）
        System.out.println(userRepository.getBeanFactory());
//        System.out.println(applicationContext ==userRepository.getBeanFactory());


        ObjectFactory userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject() == applicationContext);

        // 依赖查找 (错误)
//        System.out.println(applicationContext.getBean(BeanFactory.class));

        // 容器内建 Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println(environment);
    }

    private static void whoIdIocContainer(UserRepository userRepository, ApplicationContext applicationContext) {
        // 这个为什么不成立？？？
        System.out.println(applicationContext == userRepository.getBeanFactory());

        // 这个就是源头的BeanFactory
//        System.out.println(applicationContext.getBeanFactory() == userRepository.getBeanFactory());

        // ApplicationContext is BeanFactory

        // ConfigurablePllication <- ApplicationContext <- BeanFactory

        // ApplicationContext是BeanFactory的超集  它在BeanFactory的基础上，扩展了更多的实现
        // Easier integration with Spring’s AOP features 更容易与 Spring 的 AOP 特性集成
        // Message resource handling (for use in internationalization) 消息资源处理（用于国际化）
        // Event publication 事件发布
        // Application-layer specific contexts such as the WebApplicationContext for use in web applications.
        // 应用层特定的上下文，例如用于 Web 应用程序的 WebApplicationContext。

    }


}
    