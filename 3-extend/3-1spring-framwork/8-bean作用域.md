singleton:Ĭ��������һ��BeanFactoryֻ��һ��ʵ����
prototype��ԭ��������ÿ���������Һ�����ע�붼������һ���µ�ʵ����

Spring����û�취����prototype bean�������������ڣ����ٻص���������ִ�У�����ִ�г�ʼ��������
�����ֶ�ȥ����bean��destroy()����ִ�����١�

web��ص�bean������request��session��application��
�����Զ���������ʵ�ֽӿ�Scope�������Զ���ThreadLocal��Χ��������


BeanWrapper:�ṩ������������׼Javabean�Ĳ�����ʵ��������ת�����ȣ�����ת��������bean����

# �ھ��� bean����������
## 1��BeanDefinition����
��Դ�ļ���xml��properties�ֱ�ʹ��XmlBeanDefinitionReader��PropertiesBeanDefinitionReaderȥ������Դ�ļ�����ȡBeanDefinition��  
ע�⣺@Component��@Configuration��@Bean��AnnotatedBeanDefinitionReaderֱ��ע��class��ClassPathBeanDefinitionScanner���ݰ�·��ɨ��class��  
api��BeanDefinitionBuilder���ײ�ʵ�֡�

##  2��ע��BeanDefinition��ע���ʵ��������GenericBeanDefinition��
ע��ӿڣ�BeanDefinitionRegistry��ע��ӿڣ�ע���߼���Ψһʵ����DefaultListableBeanFactory��
ע���߼���ע�᷽��registerBeanDefinition����beanName��BeanDefinition��ӵ�beanDefinitionNames��beanDefinitionMap��beanDefinitionNames��δ����֤˳��
˵����Spring��ȥ�������@Configurationע������ ����@Beanע��ķ���
@Bean��ע�����ڽ��� ���@Configuration����� ע��������ɵ�BeanDefinition������FactoryMethodName

## 3���ϲ�
BeanDefinition����parent���ԣ���Ҫ���кϲ���**��Ҫ�����xml���õ�parent��ǩ**��
�ϲ�����:AbstractBeanFactory.getMergedBeanDefinition();�ݹ齫GenericBeanDefinition�ϲ���ת��ΪRootBeanDefinition��������
��mergedBeanDefinitions�С�

## 4��BeanDefinition.beanClass���ع��̹��̣�
�������õ�BeanDefinition�е�beanClass����ȥִ������أ�beanClass�����п������ַ�����
���ط�����AbstractBeanFactory.doResolveBeanClass()ȥ����beanClass���ԡ�

## 5��ʵ����
��1��ʵ����ǰ�ò�����InstantiationAwareBeanPostProcessor��������beanʵ����ǰ��һЩ��������postProcessBeforeInstantiation()����
����createBean()��ʱ��ȥ�жϵ�ǰBeanFactory�Ƿ�������InstantiationAwareBeanPostProcessor������еĻ�ֱ�ӻ᷵��InstantiationAwareBeanPostProcessor
���ص�ʵ����������ȥ������ʵ����bean��

��2��ʵ������
doCreateBean��������ʵ������������ѭ�����ã������ʵ������������һ���ն���û���κ�����ֵ��
AbstractAutowireCapableBeanFactory.createBeanInstance()����������Ӧ��BeanWrapper������������������
һ����ʹ��@Bean������xml��������FactoryMethod����ʹ��instantiateUsingFactoryMethod()����ȥ����ʵ����
�������ֻ��Ĭ�Ϲ���������ʹ��instantiateBean()ʵ��������ִ�����⴦��
����������ж������������ôSpring��ѡ��һ��Ĭ�ϵĹ�����������޷��ҵ�һ��Ĭ�ϵĹ������������״�
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
��3��ʵ�������ô������Ը�ֵǰ��
InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()
��ʱbean�Ѿ�ʵ����������Ĭ�Ϸ���true��bean�������ʼ���׶Ρ�����false��bean�������̡����ǿ����ڷ������Զ����޸�bean��


## 6���������� propertyValue
AbstractAutowireCapableBeanFactory.populateBean

## 7����ʼ�� initializeBean
1��Aware�ص�������BeanFactory��ApplicationContext�Ļص�
2����ʼ��ǰ�ý׶Σ�����ע���BeanPostProcessor.postProcessBeforeInitialization()��������������дbean
3����ʼ���׶Σ�����˳��ִ��@PostContruct -> InitializingBean�ӿ� -> �Զ����init������  
@PostContract������ע��������CommonAnnotationBeanPostProcessor����ʵ��ִ����CommonAnnotationBeanPostProcessor.postProcessBeforeInitialization()
��ʼ��ǰ�ô����ʱ����Ѿ�ִ���ˡ�����PostContractʵ�ʿ������Ϊ��ʼ��ǰ�ò�����
4����ʼ����ɽ׶Σ�4.1�汾֧��
## 8������

��1������ǰ�׶�
DestructionAwareBeanPostProcessor.postProcessBeforeDestruction()
��2�����٣�@PreDestory, DisposableBean�ӿڣ�ָ���Զ������ٷ���
��3��GC



# ��ʮ�� ����Ԫ��Ϣ
## 1��beanԪ��Ϣ
|                     | ������Դ�ļ�             | ��ʾע��                        | �Զ�ɨ��                         | @Beanע��                                        |
|:--------------------|:------------------------|:-------------------------------|:-------------------------------|:-------------------------------------------------|
| ������               | BeanDefinitionReader   | AnnotatedBeanDefinitionReader  | ClassPathBeanDefinitionScanner | ConfigurationClassBeanDefinitionReader            |
| ע���BeanDefinition | GenericBeanDefinition  | AnnotatedGenericBeanDefinition | ScannedGenericBeanDefinition   | ConfigurationClassBeanDefinition����reader�ڲ��ࣩ |
| ע�뷽ʽ             | xml��properties��groovy | ��ʾע�룬�ֶ�ע�룬�ڲ�ʹ��       | @Component��������ע��           | ʹ��@Beanע��ʱ��ע��                               |


 @Component����ע�⣺@Configuration��@Repository��@Service��@Controller �ȡ�
 @Beanע��BeanDefinition�������ԣ� @Beanʹ����configuration���У���ɨ���������ʱ�򣬻�ȥ���������@Beanע��ķ��������ɶ�Ӧ��BeanDefinition


schema��ʲô������xml�ļ��Ϸ�����ģ�飬����DTD��
Spring xml���þ��Ƕ����˶�Ӧ��schema�ļ���Ȼ�����ǿ��ԺϷ�ʹ��xml����bean����Դ��Ϣ��
Spring xml�ṩ���Զ�����չ:�Զ���NamespaceHandler��BeanDefinitionParse

## 2��Ioc����Ԫ��Ϣ

����ע����������ã���Ӧ�������ʹ��@Configurationע����࣬
����@ComponentScan�� @Bean�� @Import������ʹ����configuration���ϲ�����Ч��
