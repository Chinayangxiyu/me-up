# 1、AOP概念：面向切面编程，切面、连接点、切入点是核心概念。
切面：可以是方法、属性。  
连接点：程序执行的点，比如方法执行前、异常处理、方法执行后。  
切入点：匹配连接点的说明。

# 2、Spring AOP
描述：SpringAOP是Java实现的，基于IOC容器注册的bean实现的切面编程，目前仅支持方法执行的连接点。Spring AOP虽然没有提供全面的AOP解决方案  
但是能解决我们的大部分问题，当我们需要完整的AOP框架时，可以接入Aspectj。
AOP代理：默认使用基于接口的JDK，基于类继承的使用CGLIB。
DefaultAopProxyFactory.createAopProxy()方法会根据proxyTargetClass状态、目标类的类型去区分选择哪种代理。

Spring AOP使用AspectJ 5声明的注解、切入点解析和匹配。

# 3、Cglib增强
ConfigurationClassPostProcessor:如果一个类是@Configuration配置类，会进行增强。
ConfigurationClassPostProcessor.postProcessBeanFactory():使用cglib增强的子类替换目标类；

