# 一、内存分区
## 1、程序计数器：当前执行的指令的地址值。
## 2、虚拟机站栈：和线程的生命周期一致，用于保存栈帧。
2-1、每调用一个方法会创建一个栈帧；每个栈帧包含操作数栈、局部变量表、动态链接。
&emsp;&emsp;局部变量表：保存方法参数和内部定义的局部变量，有变量槽概念一个变量槽32位。
&emsp;&emsp;**操作数栈（核心）**：根据指令执行计算、出入栈的操作，代码的执行依赖操作数栈。
&emsp;&emsp;动态连接：栈帧所属方法的运行时常量池引用。
&emsp;&emsp;方法返回地址：








## dup指令作用：
1、new指令调用后会生成一个实例并压入操作数栈顶。
2、invokespecial是父方法、构造方法、私有方法调用指令，是实例方法调用；实例方法调用会默认传递一个当前
实例的引用(this)作为方法参数，并保存到局部变量表。
3、invokespecial指令调用的是init方法，需要穿一个"s1"实例作为参数，init对其进行初始化；
4、注意<init>后面接的描述符是V；表示类型是无返回值。invokespecial指令执行的时候将当前操作栈的对象弹出，作为参数传递；
弹出后操作栈就没实例了，所以需要dup拷贝一份。
```
        // 代码
        String s1 = new String();
        
        // 生成的字节码
         0: new           #2                  // class java/lang/String
         3: dup
         4: invokespecial #3                  // Method java/lang/String."<init>":()V

```

## 方法调用指令
（1）invokevirtual指令：用于调用对象的实例方法，根据对象的实际类型进行分派（虚方法分派），这也是Java语言中最常见的方法分派方式。  
（2）invokeinterface指令：用于调用接口方法，它会在运行时搜索一个实现了这个接口方法的对象，找出适合的方法进行调用。  
（3）invokespecial指令：用于调用一些需要特殊处理的实例方法，包括实例初始化方法、私有方法和父类方法。  
（4）invokestatic指令：用于调用类静态方法（static方法）。  
（5）invokedynamic指令：用于在运行时动态解析出调用点限定符所引用的方法。并执行该方法。前面四条调用指令的分派逻辑都固化在Java虚拟机  
内部，用户无法改变，而invokedynamic指令的分派逻辑是由用户所设定的引导方法决定的
