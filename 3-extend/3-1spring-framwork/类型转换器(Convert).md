# 1、类型转换底层核心类
Converter:单类型转换器
GenericConverter:通用的、灵活的、多类型转换器（实现复杂）。
ConditionalConverter:转换器的类型匹配。
ConversionService:是类型转换系统的入口，协调各个转换器的调用.
ConverterRegistry:转换器注册器，一般和ConversionService组合使用。

实际场景中Converter和GenericConverter可以互相调用，  
比如User有一个属性 companys List<Company>,需要GenericConverter去转换；
内部元素Company 需要Converter去转换，依次递归,直到转换为简单类型。

# 2、类型转换器
TypeConverter:定义转换方法的接口，向外提供类型转换功能。
TypeConverterSupport:TypeConverter的抽象实现，主要用作BeanWrapperImpl的基类。
TypeConverterDelegate:类型转换的委托类（工具类），内部使用conversionService（底层类型转换器）和PropertyEditor（属性编辑器）  
实现对Javabean的操作。

**BeanWrapperImpl，最终就是通过TypeConverterDelegate实现属性的解析、填充、转换。详情请见属性填充  
AbstractAutowireCapableBeanFactory.applyPropertyValues()**