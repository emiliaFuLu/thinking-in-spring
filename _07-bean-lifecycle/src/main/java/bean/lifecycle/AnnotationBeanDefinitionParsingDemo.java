package bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解 BeanDefinition 注解示例
 *
 * @author fulu
 * @since 2023年03月23日 23:41
 **/
public class AnnotationBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 Java 注解的 AnnotatedBeanDefinitionReader 的实现
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();

        // 注册当前类 （非 @Component Class）
        beanDefinitionReader.register(AnnotationBeanDefinitionParsingDemo.class);

        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        System.out.println("已加载 BeanDefinition 数量" + (beanDefinitionCountAfter - beanDefinitionCountBefore));
        // 普通 Class 作为 Component 注册到 Spring IoC容器后，通常 Bean 名称为 annotationBeanDefinitionParsingDemo
        // Bean 名称的生成来自于 BeanNameGenerator，注解实现 AnnotatedBeanNameGenerator
        AnnotationBeanDefinitionParsingDemo demo =
                beanFactory.getBean("annotationBeanDefinitionParsingDemo", AnnotationBeanDefinitionParsingDemo.class);
        System.out.println(demo);
    }

}
