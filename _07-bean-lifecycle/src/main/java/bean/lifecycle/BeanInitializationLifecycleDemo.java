package bean.lifecycle;

import com.fulu.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean 实例化生命周期示例
 *
 * @author <a href="mailto:fulurjj@gmail.com">FuLu</a >
 * @since 2023/5/25 23:26
 */
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        executorDefault();
    }


    public static void executorDefault() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

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

//        Employ employ = beanFactory.getBean("employ", Employ.class);
//        System.out.println("employ = " + employ);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

        System.out.println("userHolder = " + userHolder);


    }
}
