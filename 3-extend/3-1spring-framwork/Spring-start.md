# 1��DefaultListableBeanFactory.preInstantiateSingletons()
��1����ʼ�������ģ����ӳټ��ص�bean��
��2��isFactoryBean�ж��Ƿ��ǹ���bean�������FactoryBean���beanName����ǰ׺ '&'���߼���һ����
# 2��AbstractBeanFactory.doGetBean()
����1��transformedBeanName��ת��beanName��Ϊ��ȥ��FactoryBean �������Ƶ�ǰ׺"&",ΪʲôҪȥ����
��2��getSingleton(beanName)�����beanName��Ӧ��bean�Ƿ��Ѿ��������д���

getDependsOn()��ǰbean������bean




1��spring-boot-starter-aop
2��EnableAspectJAutoProxy��proxyTargetClass=true
3������IOC����ע��AnnotationAwareAspectJAutoProxyCreatorʵ����AnnotationAwareAspectJAutoProxyCreator
��BeanPostProcessor������ʵ��bean�ĺ��ô�������ʵ����AbstractAutoProxyCreator��������ǿ����

4��bean��ʵ��������Ҫִ��postProcessAfterInitialization�����к��ô���
5��������AnnotationAwareAspectJAutoProxyCreator���������AbstractAutoProxyCreator.wrapIfNecessary ��bean���д���

6��AbstractAutoProxyCreator.getAdvicesAndAdvisorsForBean �ж�bean�Ƿ���Ҫ����ǿ������AOP��
AbstractAdvisorAutoProxyCreator.findEligibleAdvisors ��ѯ��ǰbean����������֪ͨ�����������ǲ�����AOP֪ͨ��
��ѭ����������AOP��ǿ�Ż��õ��������棬����ֻ���������档




�������û��ѭ������ֻ��ʹ�����㻺��singletonFactories��registeredSingletons
AbstractAutowireCapableBeanFactory.doCreateBean
��1��addSingletonFactory() ���FactoryBean �����  
��2��initializeBean()��ʼ��beam

ObjectFactory:"�ӳټ���"

singletonFactories:����ӳټ��أ���Ϊ����ô�������ֱ�����bean�Ļ����������ԭ��
earlySingletonObjects����Ϊ�˽��ѭ������������ѭ��������ʱ���