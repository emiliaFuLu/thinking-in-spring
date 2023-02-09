package com.fulu;

import com.sun.beans.editors.StringEditor;

import java.beans.*;
import java.util.stream.Stream;

/**
 * @author fulu
 * {@code @date} 2022年08月27日 22:08
 */
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

        Age age = new Age();
        Person person = new Person();

        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    // PropertyDescriptor  允许添加属性编辑器  -PropertyEditor
                    // name   String
                    // age    Integer
                    Class<?> propertyType = propertyDescriptor.getPropertyType();
                    String propertyName = propertyDescriptor.getName();
                    if ("age".equals(propertyName)) {
                        // String -> Integer
                        // 设置属性编辑器
                        propertyDescriptor.setPropertyEditorClass(StringToInteger.class);
                        // 进行属性设置
//                        PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(person);
//                        propertyEditor.setAsText("123123");
                    }
                    System.out.println(propertyDescriptor);
                });
    }

    /**
     * 对某一个Bean中的属性进行编辑
     */
    static class StringToInteger extends PropertyEditorSupport {
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }
}
    