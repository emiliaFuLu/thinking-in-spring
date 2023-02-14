package dependency.lookup;

import com.fulu.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全 依赖查找示例$
 *
 * @author fulu
 * @create 2023年02月14日 00:07
 * @since
 **/
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 TypeSafetyDependencyLookupDemo 作为配置类 (Configuration Class)
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);

        // 启动 Spring 上下文
        applicationContext.refresh();

        // 单一类型查找
        // 演示 BeanFactory#getBean() 方法的安全性  (不安全)
        displayBeanFactoryGetBean(applicationContext);
        // 演示 ObjectFactory#getObject() 方法的安全性 (不安全)
        displayObjectFactoryGetObject(applicationContext);
        // 演示 ObjectProvider#getIfAvaliable() 方法的安全性 (安全)
        displayObjectProviderIfAvailable(applicationContext);

        // 集合类型查找
        // 演示 ListableBeanFactory#getBeansOfType() 方法的安全性 (安全)
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        // 演示 ObjectProvider Stream 的安全性
        displayObjectFactoryStreamOps(applicationContext);


        // 关闭 Spring 上下文
        applicationContext.close();
    }

    private static void displayObjectFactoryStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectFactoryStreamOps", () -> beanProvider.forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeanException("displayListableBeanFactoryGetBeansOfType", () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectProviderIfAvailable", beanProvider::getIfAvailable);
    }

    public static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext beanFactory) {
        // ObjectProvider is ObjectFactory
        ObjectFactory<User> objectFactory = beanFactory.getBeanProvider(User.class);
        printBeanException("displayObjectFactoryGetObject", objectFactory::getObject);

    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeanException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeanException(String source, Runnable runnable) {
        System.err.println("=============================================");
        System.err.println("Source from : " + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
