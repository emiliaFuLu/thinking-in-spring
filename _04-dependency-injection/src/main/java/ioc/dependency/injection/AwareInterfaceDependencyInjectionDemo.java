package ioc.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 {@link  org.springframework.beans.factory.Aware} 接口回调的依赖注入示例
 *
 * @author fulu
 * @create 2023年02月16日 21:52
 * @since
 **/
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;
    private static ApplicationContext applicationContext;


    public static void main(String[] args) {
        // 创建 BeanDefinition 容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class （配置类） -> Spring Bean
        context.register(AwareInterfaceDependencyInjectionDemo.class);

        // 启动 Spring 应用上下文
        context.refresh();

        System.out.println(context == applicationContext);
        System.out.println(context.getBeanFactory() == beanFactory);

        // 关闭 Spring 应用上下文
        context.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }
}
