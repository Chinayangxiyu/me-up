# 一、范型
什么是范型：参数化类型，使用范型能够提供通用的算法和设计方案，增强了抽象能力。
类型擦除：Java范型实现的方案是类型擦除，在编译的时候编译器会根据范型类型做校验；编译后的字节码为"裸类型（Raw type）"，并且会做
对应的类型强转。
Java范型的劣势：不支持原生类型的范型、需要做自动的类型转换（装箱、拆箱）导致性能不高；并且无法在运行时候获取具体的范型类型。


【拓展】:范型的实现有不同的方式，C#使用的"具现化范型"；对于C# List<int>和List<double>是不同的类型。Java使用类型擦除去
实现范型是为了保证向后兼容，既1.5以前的代码也能在加入范型后正常运行。也正因为类型擦除导致了Java范型的劣势。


# 二、序列化（Serializable）
概述：Java序列化使用反射将对象序列化为二进制数据，在使用的时候反序列化为对象。
[引用](https://time.geekbang.org/column/article/99774?utm_source=pinpaizhuanqu&utm_medium=geektime&utm_campaign=guanwang&utm_term=guanwang&utm_content=0511)
## 关键词
1、Serializable接口：只有实现了Serializable接口的类才能实现序列化和反序列化。
2、serialVersionUID：序列化对象的版本号，版本号不同，反序列化会失败。
3、ObjectInputStream/ObjectOutputStream：执行序列化和反序列化。
4、注解transient：标记该属性不进行序列化。
5、writeObject（）和readObject（）：默认是使用ObjectInputStream/ObjectOutputStream的这两个方法执行序列化和反序列化，如果需要定制
当前类需要使用final 重写writeObject和readObject方法，定制序列化。
6、writeReplace() 和 readResolve()：序列化前置方法，预处理；反序列化后置方法，后置处理。

## 优劣分析
优势：
Java定制化的序列化方式，提供了完善的序列化、反序列化信息，较为完善。
劣势：
&emsp;&emsp;  不能跨平台使用；
&emsp;&emsp;  反序列化不安全；
&emsp;&emsp;  Java的序列化实现比较复杂，提供类类型信息，导致序列化后更占资源；并且序列化时间也长。

**基于Java序列化的劣势，现在基本使用fastJson，gson，Protobuf进行序列化**

## 序列化引发的问题
单例模式在进行反序列化时候会违背单例原则，可以通过重写readResolve方法保证单例特性。
单例模式更好的方式是使用枚举实现，枚举能预防反射，反序列化攻击；并且保证单例。


# 三、枚举