package com.fulu.bean.factory;

import com.fulu.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link com.fulu.domain.User} Bean的{@link FactoryBean} 实现
 *
 * @author fulu
 * {@code @date} 2023年02月11日 13:58
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
    