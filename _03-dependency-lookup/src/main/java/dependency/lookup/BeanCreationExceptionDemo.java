package dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link BeanCreationException} 示例
 *
 * @author fulu
 * @create 2023年02月14日 23:17
 * @since
 **/
public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 BeanDefinition Bean Class 是一个 POJO 普通类，不过初始化方法回调时抛出异常
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);

        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    static class POJO implements InitializingBean {

        @PostConstruct // CommonAnnotationBeanPostProcessor
        public void init() throws Exception {
            throw new Exception("init() : For purpose...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet() : For purpose...");
        }
    }
}
