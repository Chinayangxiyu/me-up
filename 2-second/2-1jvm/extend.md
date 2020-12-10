# 1��Introspector(��ʡ)
��ʡ��ͨ������ʵ�ֵģ�����ڷ����ṩ�˸��򵥵�api.
��ʡ����ֻ���JavaBean��ֻ�з���JavaBean�������ĳ�Ա�ſ��Բ�����ʡAPI���в���.
Javabeans�淶��
1����ӳ��public�ģ�
2��������һ���ղι�������
3������Ӧ����private�ģ�
4�������ж�Ӧ��get/set������

5������ֶ�Ϊboolean����, ��ʱ��Ӧ�ýи�getter����, ����is����, �����getName���isName.
6������������ĵڶ�����ĸ��д, ��ô��������ֱ������ getter/setter ������ get/set �ĺ󲿷�, ����˵��Сд����. ����������ΪsName��������getsName/setsName.
7�����ǰ������ĸ�Ǵ�д(ר�����ʺ����Դʵ�), �������������ֱ�ӽ��� getter/setter ������ get/set �ĺ󲿷�. ����������ΪURL, ������getURL/setURL.
8���������ĸ��д��Ҳ��������ֱ�ӽ��� getter/setter ������ get/set �ĺ󲿷�. ����������ΪName, ������getName/setName, ��������������, ����Ϊ�Ҳ������Զ�����: ��ΪĬ�ϵ���������name.


# 2��SPI��Service Provider Interface��
��Java�ṩ��һ��������������ʵ�ֻ�����չ�Ľӿڣ��������������ÿ����չ���滻����� SPI�����þ���Ϊ��Щ����չ��APIѰ�ҷ���ʵ�� ��  
������ʵ�ֺ󣬳��������õ�����jar����ʱ��ɨ��META-INF/services/�µ�ָ���ļ���Ȼ����������������ʵ����  
������**ServiceLoader**��ServiceLoader��Javaר�����ڼ��ط���ģ���ClassLoader��ͬ��ClassLoader�Ǽ���class�ļ���ServiceLoader  
������ָ���̵߳�"contextClassLoader"ȥ����ָ����"META-INF/services/"���ķ���ʵ�֡�

ʾ����SpringBoot��ģ�黯��չ���ƾ���һ��SPI˼��ģ�������µ�ģ��ʱ����Ҫ���META-INF/spring.factories�ļ����ļ��а�������Ҫ�����ص�
��ȫ�޶������ơ�


# 3��JDNI(java Naming and Directory Interface):
һ��IOC�����Ʒ�ת����ʵ�֣�ͨ�����ý�һЩbean���г�ʼ��������"����"�йܣ�Ӧ�ó��򲻹�ע��Щbean���޸ġ���ʼ����  
Ӧ�ó�������Լ�����Ҫȥ��ȡ��Ӧ��bean���ɡ�����tomcat�п��Բ�����Ӧ�ó�����ЩӦ�ó���ʹ��ͬһ�����ݿ⣬��  
���Խ�����Դ������tomcat�У�����Ӧ�ó���Ͳ��������ˣ�ֱ��ʹ�á������"����"��������tomcat��Ҳ������SpringҲ  
������������������JNDI��һ��IOC˼�롣

����˵����https://www.oracle.com/java/technologies/jndi-overview.html  
������Java�ٷ��ṩ��һ��������Ŀ¼����API������ʹ��JNDI�洢�ͼ����κ����͵�����Java����

https://blog.csdn.net/u010430304/article/details/54601302/


# 4���ֽ��뼼��
1��ASM
CGLIB�ײ�ʹ�õ���ASM
2��javassist
3��Aspectj  
��AOP˼������ʵ�֣���һ�����ԣ����Լ����﷨����������class�ֽ��룩
https://blog.csdn.net/zhao9tian/article/details/37762389
[�ٶȰٿ�](https://baike.baidu.com/item/Aspectj/4830848?fr=aladdin)

ASM���졢���ܸ��ߣ�javassist����


# 5��MethodHandle�����������
ʲô��MethodHandle��ģ���ֽ���ķ���ָ����ã�����ʵ�ֽ�������Ϊ�������д��ݣ��ṩ������Java�����"����"������
MethodHandle��Java����Ƚϣ�
��1����MethodHandle��ģ����ֽ��뷽��ָ����ã�Reflection��Java�����εĵ��ã�MethodHandle��Ч�ʸ��ߣ�  
**����ΪMethodHandle��ģ����ֽ���ָ����ã�������������������Java����������������JVM�ϵ�����**����Reflectionֻ������Java��  
��2����MethodHandle�����Ƿ�����Java�˵�"ӳ��"��������ǩ��������������Ϣ�ȷ�����٣����������ġ�  
��3������ΪMethodHandle�ǻ����ֽ���ģ������Ͽ����������Ż������������ȣ������䲻���ԡ�
```
public class MethodHandleDemo {

    public static void main(String[] args) throws Exception, Throwable {
        Person p = new Person();

        // 1���鷽���������ʹ��
        MethodType virtualType = MethodType.methodType(int.class, String.class);

        MethodHandle virtualMethod = lookup()
                .findVirtual(p.getClass(), "virtualMethod", virtualType) // �����鷽��
                .bindTo(p); // �鷽����ʵ����������Ҫ��ʵ����
        virtualMethod.invoke("���� ��̬�����������");

        // invokeExact��ʾ��ȷ���ã�����Բ����ͷ���ִֵ������ת����
        // ��ΪvirtualMethod�����Ĳ�����String���ͣ��������µ��ûᱨ��
//         virtualMethod.invokeExact(11);

        // 2����̬�������������ʹ��
        MethodType staticType = MethodType.methodType(void.class);
        MethodHandle statucHandle = lookup().
                findStatic(Person.class, "staticMethod", staticType);// ���Ҿ�̬����
        statucHandle.invokeExact();

    }
}


class Person{


    public int virtualMethod(String n){
        System.out.println("this is a virtual method��param=" + n );
        return 100;
    }

    public static final void staticMethod(){
        System.out.println("this a static method");
    }
}
```


# 6���鷽�������鷽��(�漰��������)
�������ֱ�ӽ������ķ��������滻Ϊֱ�����õķ�����Ϊ"���鷽��"������static,private,super,final���εķ����͹�����������  
��������Ϊ�鷽����
