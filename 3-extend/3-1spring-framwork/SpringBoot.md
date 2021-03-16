# SpringBoot原理
在Spring的基础上包装自动装配的功能。


# 启动步骤
（1）构建Application，并初始化（SpringFactoriesLoader机制注册初始化器、监听器），推断引导类；
（2）创建记时器；
（3）触发监听器starting事件；
（4）准备环境prepareEnvironment；
（5）创建上下文；
（6）准备上下文prepareContext，设置环境、回调初始化器、资源加载、事件触发；
（7）刷新上下文refresh，刷新（启动）Spring上下文；
（8）后置操作
