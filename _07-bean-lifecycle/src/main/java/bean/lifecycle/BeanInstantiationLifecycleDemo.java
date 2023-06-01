package bean.lifecycle;

import com.fulu.domain.SuperUser;
import com.fulu.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化生命周期示例
 *
 * @author <a href="mailto:fulurjj@gmail.com">FuLu</a >
 * @since 2023/5/25 23:26
 */
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
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

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                // 把配置完成 superUser Bean 覆盖
                return new SuperUser();
            }
            return null; // 保持 Spring IoC 容器的实例化操作
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                // user 对象不允许属性赋值（填入）（配置元信息 -> 属性值）
                User user = (User) bean;
                user.setName("xiaowang");
                return false;
            }
            return true;
        }

        // user 是跳过 Bean 属性赋值（填入）
        // superUser 也是完全跳过 Bean 实例化（Bean 属性赋值（填入））
        // userHolder
        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {

            return null;
        }
    }

    static class MyBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                User user = (User) bean;
                user.setId(2L);
                user.setName("BeanPostProcessor");
                return user;
            }
            return null;
        }
    }

    static class MyBeanPostProcessor2 implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                User user = (User) bean;
                user.setId(3L);
                user.setName("BeanPostProcessor2");
                return user;
            }
            return null; // 保持 Spring IoC 容器的实例化操作
        }
    }
}
