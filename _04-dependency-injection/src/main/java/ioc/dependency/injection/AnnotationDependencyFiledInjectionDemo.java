package ioc.dependency.injection;

import com.fulu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于 Java 注解依赖 Filed 注入示例
 *
 * @author fulu
 * @create 2023年02月16日 21:52
 * @since
 **/
public class AnnotationDependencyFiledInjectionDemo {

    @Autowired
    // static @Autowired 会忽略掉静态字段
    private UserHolder userHolder;

    @Resource
    // byType
    private UserHolder userHolder2;

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class (配置类) -> Spring Bean
        applicationContext.register(AnnotationDependencyFiledInjectionDemo.class);

        // 根据 XML 加载 User Bean
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        // 加载 XML 资源，生成 Bean
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:META-INF/dependency-lookup-context.xml");

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 AnnotationDependencyFiledInjectionDemo Bean
        AnnotationDependencyFiledInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFiledInjectionDemo.class);

        // @Autowired 字段关联
        UserHolder userHolder = demo.userHolder;
        System.out.println(userHolder);
        System.out.println(demo.userHolder2);

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    @Bean
    private UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
