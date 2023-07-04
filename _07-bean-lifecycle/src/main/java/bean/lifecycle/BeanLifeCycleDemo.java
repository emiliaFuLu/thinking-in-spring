package bean.lifecycle;

import com.fulu.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Spring 生命周期
 *
 * @author <a href="mailto:fulurjj@gmail.com">FuLu</a >
 * @since 2023/7/5 00:19
 */
public class BeanLifeCycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-construct-dependency-injection.xml"};
        // 基于 Classpath 加载 properties 资源
        int definitions = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载的资源数量：" + definitions);

        // 显示的执行 preInstantiateSingletons()
        // SmartInitializingSingleton 通常在 Spring ApplicationContext 场景使用
        // preInstantiateSingletons 将已注册的 BeanDefinition 初始化成 Spring Bean, 在进行回调的时候确保已经完成 Bean 的初始化
        beanFactory.preInstantiateSingletons();

        // 通过 Bean Id 和 类型进行查找依赖
        User user = beanFactory.getBean("user", User.class);
        System.out.println("user = " + user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println("superUser = " + superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println("userHolder = " + userHolder);

        beanFactory.destroyBean("userHolder", userHolder);
        System.out.println("userHolder = " + userHolder);
    }
}
