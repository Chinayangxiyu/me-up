linux相关命令  
env; 查看全局的环境变量，JVM参数配置可能会引用一些环境变量；  
echo $变量名; 查看单个环境变量的值  
lsof -i:端口号; 查看端口属于哪个程序  
ps -ef|grep {PID 或 名称如Java，tomcat}; 查询具体的进程
[ps命令详解](https://www.cnblogs.com/moonbaby/p/10528443.html)

jvm相关命令[详解](https://blog.csdn.net/u010316188/article/details/80215884)  
jinfo;虚拟机配置信息  
jps;显示虚拟机进程  
jstat; 虚拟机运行时各种详细数据  
jmap; 内存快照  
jstack; 线程快照



JVM参数  
配置jvisualvm远程连接，JVM需配如参  
-Dcom.sun.management.jmxremote.port=1090\##端口号可以自己指定
-Dcom.sun.management.jmxremote.ssl=false
-Dcom.sun.management.jmxremote.authenticate=false
