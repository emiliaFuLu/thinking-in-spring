package com.fulu.bean.definition;

import com.fulu.bean.factory.DefaultUserFactory;
import com.fulu.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化 Demo
 * Bean 的初始化的 三种方式  按照优先级排序  PostConstruct > InitializeBean > 自定义initMethod
 * Bean 的销毁的三种方式  按照优先级顺序  PreDestroy > DisposableBean > 自定义doDestroy
 *
 * @author fulu
 * {@code @date} 2023年02月11日 14:28
 */
@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class (配置类)
        applicationContext.register(BeanInitializationDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 非延迟初始化 Spring 应用上下文启动完成后，被初始化
        System.out.println("Spring 应用上下文已启动...");
        // 依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);

        System.out.println("Spring 应用上下准备关闭...");

        // 关闭 Spring 应用上下文
        applicationContext.close();

        System.out.println("Spring 应用上下文已关闭...");
    }

    /**
     * initUserFactory 是在 AbstractBeanDefinition#serInitMethodName() 实现
     * 是在 Spring 5.1的时候提到 BeanDefinition 中的
     * 3.自定义初始化方式 初始化 Bean
     */
    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    @Lazy(value = false)
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

    // Bean 的延迟加载是在初始化 Spring 上下文启动之后才进行加载的
    // 非延迟加载是在 Spring上下文启动之前就已经加载了
}
    