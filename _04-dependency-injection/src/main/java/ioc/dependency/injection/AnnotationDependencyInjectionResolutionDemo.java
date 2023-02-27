package ioc.dependency.injection;

import com.fulu.domain.User;
import ioc.dependency.injection.annotation.InjectUser;
import ioc.dependency.injection.annotation.MyAutowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

/**
 * 注解驱动的以来注入过程
 *
 * @author fulu
 * @create 2023年02月22日 23:17
 * @since
 **/
@Configuration
public class AnnotationDependencyInjectionResolutionDemo {


    @Autowired
    @Lazy
    private User lazyUser;

    // DependencyDescriptor ->
    // required true
    // 实时注入(eager true)
    // 通过类型 （User.class）
    // 字段名称（"user"）
    @Autowired // 依赖查找（处理）
    public User user;

    @Inject
    public User injectUser;

    @Autowired // 集合类型以来注入
    public Collection<User> users;

    @MyAutowired
    private Optional<User> optionalUser;

    @InjectUser
    private User myInjectUser;

//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        // @Autowired + @Inject + 新注解 @InjectUser
//        Set<Class<? extends Annotation>> annotationTypes = new LinkedHashSet<>(Arrays.asList(Autowired.class, InjectUser.class, Inject.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(annotationTypes);
//        return beanPostProcessor;
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE-3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // @Autowired + @Inject + 新注解 @InjectUser
        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class (配置类)
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        // 加载 XML 文件
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        // 开启 Spring 上下文
        applicationContext.refresh();

        // 依赖查找 AnnotationDependencyInjectionResolutionDemo Bean
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        // superUser
        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.injectUser = " + demo.injectUser);
        System.out.println("demo.users = " + demo.users);
        System.out.println("demo.optionalUser = " + demo.optionalUser);
        System.out.println("demo.myInjectUser = " + demo.myInjectUser);

        // 关闭 Spring 上下文
        applicationContext.close();
    }
}
