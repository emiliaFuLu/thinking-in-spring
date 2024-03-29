package bean.lifecycle;

import com.fulu.domain.SuperUser;
import com.fulu.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化生命周期示例
 *
 * @author <a href="mailto:fulurjj@gmail.com">FuLu</a >
 * @since 2023/5/25 23:26
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        executorDefault();
    }

    public static void executorDefault(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
//        beanFactory.addBeanPostProcessor(new MyBeanPostProcessor());
//        beanFactory.addBeanPostProcessor(new MyBeanPostProcessor2());
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
