### Bean 元信息解析
- 基于资源
- 基于注解
### SpringBean 注册
- BeanDefinitionRegistry
  - beanDefinitionMap 保存 key-value
  - beanDefinitionNames 保证 BeanDefinition 的顺序
- BeanDefinition 的合并
  - 普通的一个Bean在初始的时候都是一个 GenericBeanDefinition 在加载的时候，会检查这个类是否有 parent，如果没有就会把这个类的信息封装到一个 RootBeanDefinition 中，如果有 parent，就会把这个类的信息封装到一个 ChildBeanDefinition 中，然后 ChildBeanDefinition 中会有一个 parentName 属性，指向这个类的 parent 的名字，这样就形成了一个树形结构，这个树形结构就是 BeanDefinition 的合并结果。
- Spring Bean Class 加载阶段
  - ClassLoader 类加载
  - Java Security 安全控制
  - ConfigurableBeanFactory 临时 ClassLoader
  - Spring 加载类的基础还是依赖于 Java 自身的类加载器 AppClassLoader，会涉及一些 Spring 安全控制，只不是大部分都是默认关闭的，所以我们忽略了，其中进行类加载时，还会使用一些临时的 ClassLoader，类初期的类型是 String 类型，在通过反射将类加载完成之后才会变称 Class 类型。
- Spring实例化前置
  - InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation