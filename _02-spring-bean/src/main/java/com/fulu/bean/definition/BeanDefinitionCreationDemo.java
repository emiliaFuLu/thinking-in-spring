package com.fulu.bean.definition;

import com.fulu.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建实例
 *
 * @author fulu
 * {@code @date} 2023年02月07日 23:15
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 1.通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder.addPropertyValue("name", "fulu123");
        beanDefinitionBuilder.addPropertyValue("age", 444);

        // 获取 BeanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // BeanDefinition 并非Bean终态，可以自定义修改
        System.out.println(beanDefinition);

        // 2.通过AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置Bean 类型
        genericBeanDefinition.setBeanClass(User.class);

        // 通过 MutablePropertyValues 批量设置属性
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.
                add("name", "小马哥")
                .add("age", 1234);
        // 通过set MutablePropertyValues  批量操作属性
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
        System.out.println(genericBeanDefinition);
    }
}
    