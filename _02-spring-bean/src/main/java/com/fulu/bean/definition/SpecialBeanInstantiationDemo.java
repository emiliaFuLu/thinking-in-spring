package com.fulu.bean.definition;

import com.fulu.bean.factory.DefaultUserFactory;
import com.fulu.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊的 Bean实例化实例
 *
 * @author fulu
 * {@code @date} 2023年02月09日 23:05
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        // 配置XML配置文件
        // 启动Spring应用上下文
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        // 通过 ApplicationContext 获取 AutoWireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        // 通过ServiceLoader进行实例化
        displayServiceLoader(serviceLoader);
//        demoServiceLoader();

        // 创建 UserFactory 对象，通过 AutowireCapableBeanFactory 进行实例化
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());

    }

    //  这里有多个实例只会输出一个实例
    public static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    // 这里有多个实例会输出多个实例
    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
    