AspectJ是一个代码生成工具，在编译的时候添加切入点的字节码。
Spring AOP是基于代理（jdk,CGLIB）的，CGLIB是字节码底层技术生成一个代理类（区别AspectJ，AspectJ是直接修改源class）；

Spring AOP：仅仅支持Spring bean的织入；适用Spring。
AspectJ：是支持任何类，更大，功能更全。

# 1、事务失效原因分析
[引用](https://www.jianshu.com/p/4120b89190d0)
1、bean没有被Spring管理：因为事务是基于Spring AOP的，Spring AOP仅适用于Spring bean;
2、方法不是public修饰：
3、自身调用：
```
public class A{

    // 分析(1)、method1没加事务，外部调用method1()的时候调用的是A类本身实例；
    // method1中调用method2，默认使用的是this；Spring事务是基于AOP的，有事务注解的方法
    // 对生成对应的增强代理类proxy，method1中的this调用的是A的实例方法，不是proxy；所以事务失效
    public void method1(){
        this.method2();
    }
    
    @Transactional
    public void method2(){
    }
    
    // 同分析(1)，method3中调用的method4也不是增强代理类，所以事务失效。
    // 如果正常，method4是一个新事务，回滚操作不会影响method3；但是实际影响到了method3()所以失效。
    @Transactional
    public void method3(){
        this.method4();
    }
    
    @Transactional( propagation = Propagation.REQUIRES_NEW)
    public void method4(){
    }
}
```
【注意】：Spring事务默认的传播机制是有事务加入，没有事务新建；所以平时在使用的时候类似方法method4()的调用
可以不指定事务传播机制，调用method4()如果
4、方法是final修饰的：final修饰的方法不能被重写，而当前方法如果是final修饰的，肯定方法已经实现了；AOP肯定是基于CGLIB完成的，
CGLIB代理增强类是去继承目标类，重写方法。
