### 依赖注入

1. 自动注入
    - 通过名称
    - 通过类型
    - 通过构造器
2. Setter方式注入
    - 注解方式
    - xml方式
    - API方式
3. 构造器方式注入
    - 注解方式
    - xml方式
    - API方式
4. 字段注入
    - 手动模式
        - @Autowired
        - @Resource
        - @Inject
5. 方法注入
    - 手动模式
        - @Autowired
        - @Resource
        - @Inject
        - @Bean
6. 接口回调注入
    - Aware接口
7. 基础类型注入
    - 原生类型：boolean、byte、char、short、int、float、long、double
    - 标量类型：Number、Character、Boolean、Enum、Locale、Charset、Currency、Properties、UUID
    - 常规类型：Object、String、TimeZone、Calendar、Optional等
    - Spring类型：Resource、InputSource、Formatter等
8. 集合类型注入
    - 数组类型：原生类型、标量类型、常规类型、Spring类型
    - 集合类型
        - Collection：List、Set、
        - Map：Properties
9. 限定注入
    - 使用注解 @Qualifier
        - 通过 Bean 名称
        - 通过分组限定
    - 基于注解 @Qualifier 扩展限定
        - 自定义注解 入Spring Cloud @LoadBalance
10. 延迟依赖注入
    - 使用 API ObjectFactory 延迟注入
        - 单一类型
        - 集合类型
    - 使用 API ObjectProvider 延迟注入 （推荐）
        - 单一类型
        - 集合类型
11. 