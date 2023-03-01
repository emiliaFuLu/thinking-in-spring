1. 依赖注入的来源
   - 依赖注入的来源比依赖查找多一项 
     - 就是非 Spring 管理的对象
   - 可以通过 DefaultListableBeanFactory#registerResolvableDependency() 进行注册对象
   - 默认情况下，Spring 为我们注册了四个对象
     - BeanFactory
     - ResourceLoader
     - ApplicationContext
     - ApplicationEventPublisher