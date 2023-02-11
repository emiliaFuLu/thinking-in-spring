package com.fulu.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认{@link UserFactory} 实现
 *
 * @author fulu
 * {@code @date} 2023年02月09日 23:14
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    // 1.基于 @PostConstruct 注解初始化Bean
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct: UserFactory 初始化中...");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化方法 initUserFactory(): UserFactory 初始化中...");
    }

    // 2.基于InitializingBean#afterPropertiesSet() 的初始化Bean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet(): UserFactory 初始化中...");
    }

    // 1. 基于 @preDestroy 注解销毁Bean
    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: UserFactory 销毁中...");
    }

    // 2. 基于 DisposableBean#destroy 销毁Bean
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy(): UserFactory 销毁中...");
    }

    // 3. 自定义销毁方式
    public void doDestroy() {
        System.out.println("自定义销毁方法 doDestroy(): UserFactory 销毁中...");
    }


    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前 DefaultUserFactory 对象正在被垃圾回收");
    }


}
    