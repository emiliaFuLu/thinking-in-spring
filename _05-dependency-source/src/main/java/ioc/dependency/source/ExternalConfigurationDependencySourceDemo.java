package ioc.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置
 *
 * @author fulu
 * @create 2023年03月05日 15:33
 * @since
 **/
@Configuration
@PropertySource(value = "META-INF/default.properties",encoding = "UTF-8")
public class ExternalConfigurationDependencySourceDemo {


    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath://META-INF/default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        // 创建 BeanFactory 工厂
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class (配置类)
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

        // 开启 Spring 上下文
        applicationContext.refresh();

        // 依赖查找
        ExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

        System.out.println("demo.id = " + demo.id);
        System.out.println("demo.name = " + demo.name);
        System.out.println("demo.resource = " + demo.resource);

        // 关闭 Spring 上下文
        applicationContext.close();
    }

}
