### Singleton Bean 和 Prototype Bean

- 结论一：
    - Singleton Bean 无论是依赖查找还是依赖注入，均为同一个对象
    - Prototype Bean 无论是依赖查找还是依赖注入，均为新生成的对象

- 结论二：
    - 如果依赖注入集合类型对象，Singleton Bean 和 Prototype Bean 均会存在一个
    - Prototype Bean 有别于其他地方的依赖注入的对象
- 结论三：
  - 无论是 Singleton 还是 Prototype Bean都会执行初始化方法回调，不过仅 Singleton Bean 会执行销毁




`注意：Spring 是没有办法管理 Prototype Bean 的完整的生命周期，也没有办法记录实例的存在，销毁毁掉方法也不会执行，可以利用 BeanPostProcessor 进行清理`

