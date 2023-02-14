package dependency.lookup;

import com.fulu.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 {@link org.springframework.beans.factory.ObjectProvider} 进行依赖查找
 *
 * @author fulu
 * {@code @date} 2023年02月12日 15:09
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类
        applicationContext.register(ObjectProviderDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找对象
        lookupByObjectProvider(applicationContext);

        // 延迟查找 String 类型的操作
        lookupIfAviliable(applicationContext);
        lookupByStreamOps(applicationContext);

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> stringIterable = beanProvider;
//        for (String s : stringIterable) {
//            System.out.println(s);
//        }

        // Stream -> Method reference
        beanProvider.stream().forEach(System.out::println);
    }

    // ObjectProvider#getIfAvailable 的使用
    private static void lookupIfAviliable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        User user = beanProvider.getIfAvailable(User::createUser);
        System.out.println("当前 User 对象： " + user);
    }

    @Bean
    public String message() {
        return "message";
    }

    @Bean
    @Primary
    public String helloWord() { // 方法名就是 Bean 名称 = "helloWord"
        return "hello word";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
    