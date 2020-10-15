# 1、SPI（Service Provider Interface）
是Java提供的一套用来被第三方实现或者扩展的接口，它可以用来启用框架扩展和替换组件。 SPI的作用就是为这些被扩展的API寻找服务实现。
第三方实现后，程序在引用第三方jar包的时候，扫描META-INF/services/下的指定文件，然后由类加载器求加载实例。
示例：SpringBoot的模块化扩展机制就是一种SPI思想的，当添加新的模块时，需要添加META-INF/spring.factories文件，文件中包含有需要被加载的
类全限定类名称。

# 2、什么是JNDI(ava Naming and Directory Interface):
一种IOC（控制反转）的实现，通过配置将一些bean进行初始化，交由"容器"托管，应用程序不关注这些bean的修改、初始化；  
应用程序根据自己的需要去获取对应的bean即可。比如tomcat中可以部署多个应用程序，这些应用程序使用同一个数据库，就  
可以将数据源配置在tomcat中，各个应用程序就不用配置了，直接使用。这里的"容器"不仅限于tomcat，也可以是Spring也  
可以是其他的容器；JNDI是一种IOC思想。

官网说明：https://www.oracle.com/java/technologies/jndi-overview.html
概括：Java官方提供的一套命名和目录功能API，可以使用JNDI存储和检索任何类型的命名Java对象。

https://blog.csdn.net/u010430304/article/details/54601302/
