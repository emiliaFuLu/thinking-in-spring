package dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException} 示例
 *
 * @author fulu
 * @create 2023年02月14日 23:04
 * @since
 **/
public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 hierarchicalDependencyLookupDemo 作为配置类 (Configuration Class)
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        try {
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.out.printf(" Spring 应用上下文存在%d个 %s 类型的 Bean，具体原因是 %s%n",
                    e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage());
        }

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }

}
