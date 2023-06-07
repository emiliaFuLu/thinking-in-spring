package bean.lifecycle;

import com.fulu.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * User Holder 类
 *
 * @author <a href="mailto:fulu@witsky.cn">FuLu</a>
 * @since 2023/6/1 13:59
 */
public class UserHolder implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware , EnvironmentAware {
    private final User user;

    private Integer number;
    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String beanName;
    private Environment environment;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String remark;

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
       this.environment = environment;
    }
}
 