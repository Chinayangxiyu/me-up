singleton:默认作用域，一个BeanFactory只有一个实例。
prototype：原型作用域，每次依赖查找和依赖注入都会生成一个新的实例。

Spring容器没办法管理prototype bean的完整生命周期，销毁回调方法不会执行；可以执行初始化方法。
可以手动去调用bean的destroy()方法执行销毁。

web相关的bean作用域request，session，application；
可以自定义作用域，实现接口Scope，比如自定义ThreadLocal范围的作用域


BeanWrapper:提供分析、操作标准Javabean的操作。实现了类型转换器等，可以转换，设置bean属性

# 第九章 bean的生命周期
## 1、BeanDefinition配置
资源文件：xml、properties分别使用XmlBeanDefinitionReader、PropertiesBeanDefinitionReader去解析资源文件，获取BeanDefinition。  
注解：@Component、@Configuration、@Bean；AnnotatedBeanDefinitionReader直接注册class，ClassPathBeanDefinitionScanner根据包路径扫描class。  
api：BeanDefinitionBuilder，底层实现。

##  2、注册BeanDefinition（注册的实际类型是GenericBeanDefinition）
注册接口：BeanDefinitionRegistry是注册接口；注册逻辑的唯一实现是DefaultListableBeanFactory。
注册逻辑：注册方法registerBeanDefinition，将beanName和BeanDefinition添加到beanDefinitionNames、beanDefinitionMap；beanDefinitionNames是未来保证顺序。
说明：Spring会去解析添加@Configuration注解类中 加了@Bean注解的方法
@Bean的注入是在解析 标记@Configuration的类后， 注解会在生成的BeanDefinition中设置FactoryMethodName

## 3、合并
BeanDefinition具有parent属性，需要进行合并，**主要是针对xml配置的parent标签**。
合并过程:AbstractBeanFactory.getMergedBeanDefinition();递归将GenericBeanDefinition合并、转换为RootBeanDefinition，并缓存
在mergedBeanDefinitions中。

## 4、BeanDefinition.beanClass加载过程过程：
根据配置的BeanDefinition中的beanClass属性去执行类加载，beanClass属性有可能是字符串。
加载方法：AbstractBeanFactory.doResolveBeanClass()去加载beanClass属性。

## 5、实例化
（1）实例化前置操作：InstantiationAwareBeanPostProcessor，可以在bean实例化前做一些操作。如postProcessBeforeInstantiation()方法
会在createBean()的时候去判断当前BeanFactory是否配置了InstantiationAwareBeanPostProcessor，如果有的话直接会返回InstantiationAwareBeanPostProcessor
返回的实例，不会再去真正的实例化bean。

（2）实例化：
doCreateBean方法进行实例化，并处理循环引用；这里的实例化仅仅创建一个空对象，没有任何属性值。
AbstractAutowireCapableBeanFactory.createBeanInstance()方法创建对应的BeanWrapper，这个方法有三种情况
一、像使用@Bean，或者xml中配置了FactoryMethod将会使用instantiateUsingFactoryMethod()方法去创建实例；
二、如果只有默认构造器将会使用instantiateBean()实例化，不执行特殊处理
三、如果类有多个构造器，那么Spring会选择一个默认的构造器，如果无法找到一个默认的构造器，将会抛错。
???
@Component
public class ControctorDemo {
    private Person person;
    private String s1;

    @Autowired
    public ControctorDemo(Person person) {
        this.person = person;
    }

    @Autowired
    public ControctorDemo(String s1) {
        this.s1 = s1;
    }     
}


???
（3）实例化后置处理（属性赋值前）
InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()
此时bean已经实例化，方法默认返回true，bean将进入初始化阶段。返回false，bean结束流程。我们可以在方法中自定义修改bean。


## 6、属性设置 propertyValue
AbstractAutowireCapableBeanFactory.populateBean

## 7、初始化 initializeBean
1、Aware回调，区分BeanFactory和ApplicationContext的回调
2、初始化前置阶段，调用注册的BeanPostProcessor.postProcessBeforeInitialization()，方法，可以重写bean
3、初始化阶段：按照顺序执行@PostContruct -> InitializingBean接口 -> 自定义的init方法；  
@PostContract依赖于注解驱动（CommonAnnotationBeanPostProcessor）；实际执行在CommonAnnotationBeanPostProcessor.postProcessBeforeInitialization()
初始化前置处理的时候就已经执行了。所以PostContract实际可以理解为初始化前置操作。
4、初始化完成阶段：4.1版本支持
## 8、销毁

（1）销毁前阶段
DestructionAwareBeanPostProcessor.postProcessBeforeDestruction()
（2）销毁：@PreDestory, DisposableBean接口，指定自定义销毁方法
（3）GC



# 第十章 配置元信息
## 1、bean元信息
|                     | 基于资源文件             | 显示注册                        | 自动扫描                         | @Bean注册                                        |
|:--------------------|:------------------------|:-------------------------------|:-------------------------------|:-------------------------------------------------|
| 解析类               | BeanDefinitionReader   | AnnotatedBeanDefinitionReader  | ClassPathBeanDefinitionScanner | ConfigurationClassBeanDefinitionReader            |
| 注册的BeanDefinition | GenericBeanDefinition  | AnnotatedGenericBeanDefinition | ScannedGenericBeanDefinition   | ConfigurationClassBeanDefinition（是reader内部类） |
| 注入方式             | xml、properties、groovy | 显示注入，手动注入，内部使用       | @Component及其派生注解           | 使用@Bean注解时候注入                               |


 @Component派生注解：@Configuration、@Repository、@Service、@Controller 等。
 @Bean注入BeanDefinition的特殊性： @Bean使用在configuration类中，在扫描配置类的时候，会去解析添加了@Bean注解的方法，生成对应的BeanDefinition


schema是什么：定义xml文件合法构建模块，类似DTD。
Spring xml配置就是定义了对应的schema文件，然后我们可以合法使用xml配置bean等资源信息。
Spring xml提供了自定义拓展:自定义NamespaceHandler、BeanDefinitionParse

## 2、Ioc容器元信息

基于注解的容器配置，对应的类必须使用@Configuration注解的类，
比如@ComponentScan， @Bean， @Import都必须使用在configuration类上才能生效。
