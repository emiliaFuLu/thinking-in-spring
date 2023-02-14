package dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanInstantiationException} 示例  Bean 所对应的类型是非具体类型时报错
 *
 * @author fulu
 * @create 2023年02月14日 23:12
 * @since
 **/
public class BeanInstantiationExceptionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 BeanDefinition Bean Class 是一个 CharSequence 接口
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);

        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }
}
