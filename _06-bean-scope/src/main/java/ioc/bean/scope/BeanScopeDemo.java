package ioc.bean.scope;

import com.fulu.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * Bean 的作用域
 *
 * @author fulu
 * @create 2023年03月13日 21:46
 * @since
 **/
public class BeanScopeDemo implements DisposableBean {

    @Bean
    // scope 默认为 singleton
    public static User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser() {
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;
    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;// Resolvable Dependency

    public static void main(String[] args) {
        // 创建 BeanFactory 工厂
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class (配置类)
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.format("%s Bean 名称：%s 在初始化之后回调... %n", bean.getClass().getName(), beanName);
                    return bean;
                }

            });
        });

        // 开启 Spring 上下文
        applicationContext.refresh();
        // 结论一：
        // Singleton Bean 无论是依赖查找还是依赖注入，均为同一个对象
        // Prototype Bean 无论是依赖查找还是依赖注入，均为新生成的对象

        // 结论二：
        // 如果依赖注入集合类型对象，Singleton Bean 和 Prototype Bean 均会存在一个
        // Prototype Bean 有别于其他地方的依赖注入的对象

        // 依赖查找
        scopeBeanByLookup(applicationContext);
        // 依赖注入
        scopeBeansByInjection(applicationContext);

        // 关闭 Spring 上下文
        applicationContext.close();
    }

    private static void scopeBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("beanScopeDemo.singletonUser = " + beanScopeDemo.singletonUser);
        System.out.println("beanScopeDemo.singletonUser1 = " + beanScopeDemo.singletonUser1);
        System.out.println("beanScopeDemo.prototypeUser = " + beanScopeDemo.prototypeUser);
        System.out.println("beanScopeDemo.prototypeUser1 = " + beanScopeDemo.prototypeUser1);
        System.out.println("beanScopeDemo.prototypeUser2 = " + beanScopeDemo.prototypeUser2);

        System.out.println("beanScopeDemo.users = " + beanScopeDemo.users);
    }

    public static void scopeBeanByLookup(AnnotationConfigApplicationContext applicationContext) {

        for (int i = 0; i < 3; i++) {
            // 共享的对象
            User singletonUser = (User) applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser = " + singletonUser);

            // 每次依赖查找都会生成新的对象
            User prototypeUser = (User) applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser = " + prototypeUser);
        }
    }

    @Override
    public void destroy() {
        System.out.println("当前 BeanScope Bean 正在进行销毁...");

        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()) {
                entry.getValue().destroy();
            }
        }
        System.out.println("当前 BeanScope Bean 销毁完成");
    }
}
