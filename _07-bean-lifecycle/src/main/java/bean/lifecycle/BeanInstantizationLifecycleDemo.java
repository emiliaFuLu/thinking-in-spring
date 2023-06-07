package bean.lifecycle;

import com.fulu.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化生命周期示例
 *
 * @author <a href="mailto:fulurjj@gmail.com">FuLu</a >
 * @since 2023/5/25 23:26
 */
public class BeanInstantizationLifecycleDemo {

    public static void main(String[] args) {
        executorDefault();
        System.out.println("--------------------------------");
        executorApplication();
    }

    private static void executorApplication() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-construct-dependency-injection.xml"};

        applicationContext.setConfigLocations(locations);

        applicationContext.refresh();

        // 通过 Bean Id 和 类型进行查找依赖
        User user = applicationContext.getBean("user", User.class);
        System.out.println("user = " + user);

        User superUser = applicationContext.getBean("superUser", User.class);
        System.out.println("superUser = " + superUser);

        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println("userHolder = " + userHolder);
        applicationContext.close();
    }


    public static void executorDefault() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-construct-dependency-injection.xml"};
        // 基于 Classpath 加载 properties 资源
        int definitions = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载的资源数量：" + definitions);
        // 通过 Bean Id 和 类型进行查找依赖
        User user = beanFactory.getBean("user", User.class);
        System.out.println("user = " + user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println("superUser = " + superUser);

        Employ employ = beanFactory.getBean("employ", Employ.class);
        System.out.println("employ = " + employ);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println("userHolder = " + userHolder);
    }
}
