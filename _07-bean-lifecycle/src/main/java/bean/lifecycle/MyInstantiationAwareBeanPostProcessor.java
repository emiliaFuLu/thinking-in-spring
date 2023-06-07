package bean.lifecycle;

import com.fulu.domain.SuperUser;
import com.fulu.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * @author <a href="mailto:fulu@witsky.cn">FuLu</a>
 * @since 2023/6/7 16:39
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
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
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            MutablePropertyValues mpv = new MutablePropertyValues();
            mpv.addPropertyValues(pvs);
            // 原始配置 <property name="number" value="1"/>
            // 如果存在 <property name="number" value="2"/>
            // 则 2 会覆盖 1
            if (mpv.contains("number")) {
                mpv.addPropertyValue("number", "31");
            }

            if (mpv.contains("remark")) {
                mpv.removePropertyValue("remark");
                mpv.addPropertyValue("remark", "the userHolder v2");
            }
            return mpv;
        }
        return null;
    }
}

class MyBeanPostProcessor implements BeanPostProcessor {
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

class MyBeanPostProcessor2 implements BeanPostProcessor {
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