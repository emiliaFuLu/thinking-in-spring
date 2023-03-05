package ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖来源
 *
 * @author fulu
 * @create 2023年03月05日 15:01
 * @since
 **/
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册 Resolve Dependency
            beanFactory.registerResolvableDependency(String.class, "Hello FuLu");
        });

        applicationContext.refresh();

        applicationContext.close();
    }
}
