# 概述
attach（依附、寄生）机制：JVM提供的一种JVM之间**本地通信**的能力；能让一个进程传递命令给另一个进程，让他执行一些内部的操作。
应用场景：通过jstack命令输出当前的线程dump。
com.sun.tools.attach包路径下是attach相关API。

# 原理
    Unix domain socket 是IPC机制的一种实现方式（IPC：inter-process communication 进程间通信)，是基于网络socket演进来的，
用于同一台主机的进程间通讯；
通信过程中不需要经过网络协议栈，不需要打包拆包、计算校验和、维护序号和应答；效率更高
    attach就是基于IPC实现的JVM间通信，每个JVM都会有Signal Dispatcher 和Attach Listener两个线程。

## Attach Listener（目标JVM）
&emsp;&emsp;   启动后会创建一个监听套接字，并创建了一个文件/tmp/.java_pid，客户端和目标JVM就是通过这个socketFile进行通信。
&emsp;&emsp;   Attach Listener线程可以在**程序启动**的时候根据JVM参数设置启动（StartAttachListener）；
&emsp;&emsp;   如果目标JVM启动时未启动Attach Listener线程，**运行时**需要启动，客户端会向依附的JVM发送一个信号，目标JVM的Signal Dispatcher线程
就是用于监听信号的，监听到信号是"sigquit"时，Signal Dispatcher会启动Attach Listener线程。

## Signal Dispatcher
&emsp;&emsp;   监听并处理 OS（进程间通信简称）的信号。

流计算引擎
列式数据库 HiStore
Exactly-Once功能
开源流计算库StreamLib

