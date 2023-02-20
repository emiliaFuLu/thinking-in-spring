package ioc.dependency.injection;

import com.fulu.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

/**
 * {@link ObjectProvider} 注解注入
 *
 * @author fulu
 * @create 2023年02月20日 22:34
 * @see Qualifier
 * @since
 **/
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // 实时注入

    @Autowired
    private ObjectProvider<User> objectProvider; // 延迟注入

    @Autowired
    private ObjectFactory<Collection<User>> objectFactory;

    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class (配置类)
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        // 加载 XML 文件
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        // 开启 Spring 上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        // superUser
        System.out.println("demo.user = "+demo.user);
        // superUser
        System.out.println("demo.objectProvider = "+demo.objectProvider.getObject()); // 继承 ObjectFactory

        // superUser User
        demo.objectProvider.forEach(System.out::println);

        // superUser User
        System.out.println("demo.objectFactory = "+demo.objectFactory.getObject());

        // 关闭 Spring 上下文
        applicationContext.close();
    }
}
