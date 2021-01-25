# 1、DefaultListableBeanFactory.preInstantiateSingletons()
（1）初始化单例的，非延迟加载的bean；
（2）isFactoryBean判断是否是工厂bean，如果是FactoryBean会给beanName加上前缀 '&'，逻辑不一样；
# 2、AbstractBeanFactory.doGetBean()
？（1）transformedBeanName：转换beanName，为会去掉FactoryBean 对于名称的前缀"&",为什么要去掉；
（2）getSingleton(beanName)：检查beanName对应的bean是否已经在容器中存在

getDependsOn()当前bean依赖的bean




1、spring-boot-starter-aop
2、EnableAspectJAutoProxy；proxyTargetClass=true
3、会向IOC容器注入AnnotationAwareAspectJAutoProxyCreator实例，AnnotationAwareAspectJAutoProxyCreator
是BeanPostProcessor，可以实现bean的后置处理；并且实现类AbstractAutoProxyCreator（代理增强）；

4、bean在实例化后，需要执行postProcessAfterInitialization，进行后置处理；
5、配置了AnnotationAwareAspectJAutoProxyCreator，将会调用AbstractAutoProxyCreator.wrapIfNecessary 对bean进行处理

6、AbstractAutoProxyCreator.getAdvicesAndAdvisorsForBean 判断bean是否需要被增强（事务，AOP）
AbstractAdvisorAutoProxyCreator.findEligibleAdvisors 查询当前bean符合条件的通知，（包含但是不限于AOP通知）
有循环依赖且有AOP增强才会用到三级缓存，否则只有两级缓存。




【】如果没有循环依赖只会使用两层缓存singletonFactories、registeredSingletons
AbstractAutowireCapableBeanFactory.doCreateBean
（1）addSingletonFactory() 添加FactoryBean 必须的  
（2）initializeBean()初始化beam

ObjectFactory:"延迟加载"

singletonFactories:解决延迟加载，因为后后置处理器，直接添加bean的话不符合设计原理；
earlySingletonObjects：是为了解决循环依赖，产生循环依赖的时候会