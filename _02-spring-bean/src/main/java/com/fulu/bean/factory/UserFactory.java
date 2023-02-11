package com.fulu.bean.factory;

import com.fulu.domain.User;

/**
 * {@link com.fulu.domain.User} 工厂类
 *
 * @author fulu
 * {@code @date} 2023年02月09日 23:11
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }

    void initUserFactory();

    void doDestroy();
}
    