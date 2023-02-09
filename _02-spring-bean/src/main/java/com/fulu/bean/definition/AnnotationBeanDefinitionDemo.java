package com.fulu.bean.definition;

import com.fulu.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * @author fulu
 * {@code @date} 2023年02月09日 22:29
 */
@Import(AnnotationBeanDefinitionDemo.Config.class) // 通过@Import 来进行导入
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Class (配置类)
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        // 1.通过@Bean 方式定义
        // 2.通过@Component
        // 3.通过@Import 来进行导入

        // 通过BeanDefinition 注册API实现
        // 1.命名Bean的注册方式
        registerUserBeanDefinition(applicationContext, "fulu123-user");
        // 2.非命名Bean的注册的方式
        registerUserBeanDefinition(applicationContext);

        /**
         * 命名方式注册走的是AnnotationBeanNameGenerator
         * 非命名方式走的是DefaultBeanNameGenerator
         */

        // 启动 Spring 上下文
        applicationContext.refresh();

        System.out.println("Config 类型的的所有beans：" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的的所有beans：" + applicationContext.getBeansOfType(User.class));

        // 显示的关闭 Spring 应用上下文
        applicationContext.close();
    }

    /**
     * 命名Bean的注册方式
     *
     * @param registry
     * @param beanName
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "fulu")
                .addPropertyValue("age", 123);

        // 判断如果Bean Name参数存在时
        if (StringUtils.hasText(beanName)) {
            //  注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名方式注册
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }

    }

    /**
     * 非命名注册方式
     *
     * @param registry
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    // 2.通过@Component
    @Component // 定义当前类作为 Spring Bean (组件)
    public static class Config {

        // 1.通过@Bean 方式定义

        /**
         * 通过 Java 注解的方式，定义一个Bean
         */
        @Bean(name = {"user", "fulu-user"})
        public User user() {
            User user = new User();
            user.setAge(123);
            user.setName("fulu");
            return user;
        }
    }
}
    