package com.fulu.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean 垃圾回收 (GC) 示例
 *
 * @author fulu
 * {@code @date} 2023年02月11日 17:09
 */
public class BeanGarbageCollectionDemo {
    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class (配置类)
        applicationContext.register(BeanInitializationDemo.class);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        //关闭 Spring 应用上下文
        applicationContext.close();

        // 强制触发 GC
        System.gc();
    }
}
    