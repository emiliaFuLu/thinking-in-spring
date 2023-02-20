package ioc.dependency.injection;

import com.fulu.domain.User;
import ioc.dependency.injection.annotation.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link Qualifier} 注解注入
 *
 * @author fulu
 * @create 2023年02月20日 22:34
 * @see Qualifier
 * @since
 **/
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // SupperUser -> primary = true

    @Autowired
    @Qualifier("user") // 指定 Bean 名称或 ID
    private User nameUser;

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupUsers;

    @Bean
    @Qualifier
    public User user1(){
        User user = new User();
        user.setName("ahhha");
        return user;
    }

    @Bean
    @Qualifier // 进行逻辑分组
    public User user2(){
        User user = new User();
        user.setName("double");
        return user;
    }

    @Bean
    @UserGroup
    public User user3(){
        User user = new User();
        user.setName("operator");
        return user;
    }

    @Bean
    @UserGroup
    public User user4(){
        User user = new User();
        user.setName("group");
        return user;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class (配置类)
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        // 加载 XML 文件
        beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        // 开启 Spring 上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        // superUser
        System.out.println("demo.user = "+demo.user);
        // User
        System.out.println("demo.nameUser = "+demo.nameUser);
        // User SuperUser
        System.out.println("demo.allUsers = "+demo.allUsers);
        // 带 @Qualifier 的两个 User 和 带 @UserGroup 的两个 User
        System.out.println("demo.qualifierUsers = "+demo.qualifierUsers);
        // 带 @UserGroup 的两个
        System.out.println("demo.groupUsers = "+demo.groupUsers);

        // 关闭 Spring 上下文
        applicationContext.close();
    }
}
