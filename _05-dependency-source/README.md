1. 依赖查找
    - 依赖查找的来源
        - SpringBeanDefinition
        - 单例对象
    - 通过 DefaultListableBeanFactory#registerResolvableDependency() 进行注册对象
    - 默认情况下，Spring 为我们注册了四个对象
        - BeanFactory
        - ResourceLoader
        - ApplicationContext
        - ApplicationEventPublisher
    - 依赖注入的来源比依赖查找多一项
        - 就是非 Spring 管理的对象

2. Spring 依赖注入的来源
    - SpringBeanDefinition 作为依赖来源
        - BeanDefinitionRegistry#registerBeanDefinition() 方法，由DefaultListableBeanFactory 实现该方法 进行
          BeanDefinition 的注册
    - 单体对象 作为依赖来源 （非 Spring IOC 托管的对象）
        - SingletonBeanRegistry#registerSingleton() 方法，由 DefaultSingletonBeanRegistry 实现，并由
          DefaultListableBeanFactory 重载实现来进行单体对象的注册
    - ResolveDependency 作为依赖来源
        - 无生命周期
        - 无法延迟初始化
        - 只能用于类型方面的依赖注入，而且不能进行依赖查找
    - 外部化配置作为依赖来源（通过@Value 所标注的外部化配置）
        - 无生命周期管理
        - 无法延迟初始化
        - 无法通过依赖查找

`Spring 更大的特性在于依赖注入而不是依赖查找，因为依赖查找是一种主动的并且是一个不是那么直接的一种方式，需要进行二次的操作。`
