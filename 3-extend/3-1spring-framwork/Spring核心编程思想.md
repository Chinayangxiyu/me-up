5��SpringIOC��������
�ӳٲ��ҵĺ��壻
BeanFactory����˫��ί�ɻ���

6��IOC������
6-1��  
BeanDefinition������bean��Ԫ������Ϣ�����������򣬶�Ӧbean������Ϣ��lazy��primary����Ϣ��parent����ʹ����в�νṹ����
����ʵ��GenericBeanDefinition��

BeanDefinitionReader����ȡBeanDefinition�Ľӿڣ������xml�ļ��ж�ȡBeanDefinitionʹ��XmlBeanDefinitionReader����
**����Ҫ�����е�BeanDefinition����Դ������BeanDefinitionReader�ṩ**��

6-2��
BeanDefinitionRegistry�ӿڣ�BeanDefinitionע�����ĳ���ӿڣ�bean�Ǹ���BeanDefinition�����ģ������Ҫ����bean�ͱ����
BeanDefinition���й���


6-3��BeanFactory��bean�����ĸ��ӿڣ��ṩ��getBean��getType�Ȼ���bean�����Ļ���������
��Ҫ�ӽӿ�
ListableBeanFactory����չBeanFactory���������ͻ�ȡbean��list����
HierarchicalBeanFactory����չBeanFactory���в�νṹ������parentBeanFactory��

6-4��  
ApplicationContext�ӿڣ�SpringӦ�ó���ĺ��Ľӿڣ���������IOC��IOCֻ����̳���BeanFactory�Ĺ��ܣ�����������Դ����  
��ResourceLoader�������ʻ���MessageSource�����¼�������ApplicationEventPublisher�����ܡ�





bean��ӵ�IOC�����Ĳ���
1������BeanDefinition��Ϣ����ע�ᵽDefaultListableBeanFactory.beanDefinitionMap��
2������  InstantiationStrategy.instantiate����ʵ��bean
3������ʵ���Ĺ�������Ҫװ��������



##  6-5���Զ�װ�䣨�󶨣�
BeanPostProcessor����bean��ʼ��ǰ����ʼ������һЩ����,ʵ�������������Ĺ���
BeanPostProcessor.postProcessBeforeInitialization()����bean��ʼ��֮ǰ��
BeanPostProcessor.postProcessAfterInitialization()����bean��ʼ��֮��


AutowiredAnnotationBeanPostProcessor������@AutoWired��@Value(װ������)��@Inject���Զ�װ�䣻ǰ������Spring�Լ��ģ�
@Inject��JSR330����ġ�

CommonAnnotationBeanPostProcessor����Ҫ֧��JSR250�����ע�⣬����@Resource,@PostConstruct,@PreDestory;��  
AutowiredAnnotationBeanPostProcessor��ȳ���װ��bean����ʵ����bean��init��destroy������
�Զ�װ��Ĳ��裺  
��1���������ȡ��ǰbean�������ע�⣨@AutoWired��@Resource)���ֶκͷ����������Զ�װ����Ҫ��Ԫ��Ϣ��InjectionMetadata����
��2����������Ҫװ���bean��InjectionMetadata.inject()�����и��ݲ���1"Ԫ��Ϣ"��ȥbeanFactory�в��Ҷ�Ӧ��bean��
��3����ִ�а󶨣����÷��䷽�������鵽��bean�͵�ǰ����󶨡�

InstantiationAwareBeanPostProcessor�ӿڵ�postProcessProperties()�����ǽ�bean�󶨶�Ӧ������֮ǰ���Ĵ���
AutowiredAnnotationBeanPostProcessor��CommonAnnotationBeanPostProcessor��ʵ���˸ýӿڣ����þ���ִ�������������Զ�װ�䡣


## 6-6��refresh�����أ����������ģ�
ConfigurableApplicationContext.refresh():����Spring������


## 6-6��
ConfigurableListableBeanFactory.registerResolvableDependency������ע��һ������bean������BeanFactory.class��ApplicationContext.class
�ȡ���Щbean����ͨ���������ң�getBean�� �ҵ�����Ϊ��������ʹ�õ���ApplicationContext�ڲ���BeanFactory��������Щbeanû��ע�ᵽbeanFactory������
�ǻ��浽��DefaultListableBeanFactory.resolvableDependencies�����У���������ע�루�󶨣���ʱ��������⴦��
����������£�
```
DefaultListableBeanFactory.findAutowireCandidates();
```


�����ܽ᣺IOC����ע���bean����ô���ģ�
��1��BanDefinition������  
AnnotatedBeanDefinitionReader������ע�⣬��@Bean
ClassPathBeanDefinitionScanner��ɨ��classPath
���ն��ǵ���DefaultListableBeanFactory.registerBeanDefinition()ע��BeanDefinition��

bean��������DefaultListableBeanFactory.getBean();

BeanPostProcessor��bean�Ĵ�����
BeanFactoryPostProcessor��beanFactory���ӣ������Զ����޸�Ӧ�ó��������ĵ�bean���壬���������ĵײ�bean������bean����ֵ��BeanDefinition
�Ѿ����أ�����bean��δʵ������ȥ�޸�BeanDefinition������ֵ��



AutowireCapableBeanFactory:�����Զ�װ��������BeanFactory
SingletonBeanRegistry:ע��һЩ���õĵ���bean����Щbean������