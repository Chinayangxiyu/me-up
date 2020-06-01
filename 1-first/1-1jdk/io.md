# 1、Java-BIO
IO原理详解[IO/NIO](/2-second/2-6IO&NIO/io.md)  
IO涉及的涉及模式[IO装饰器、适配器模式](/2-second/2-4design_mode/结构性模式.md)
## 1-1字节流  
&emsp;&emsp;  数据是以字节的格式输入输出，因为数据的传输是连续的，抽象成流。
>InputStream/OutputStream：java中流的核心，抽象了**二进制**的数据流处理。  
>>FileInput/OutputStream：以文件为目的地，进行输入输出的二进制流。  
>>ByteArrayInput/OutputStream：以内存为目的地，进行二进制流输入输出。  
>>PipedInput/OutputStream：以线程为目的地，进行数据传输；线程间通信。  
>>SequenceInputStream：注意此类**只有输入流**，进行流的合并。  
>>FilterInput/OutputStream：装饰器接口，使用装饰器模式；用于拓展字节流处理的功能，比如装饰了缓冲区功能的的BufferedInputStream，格式化输出的PrintStream。  

&emsp;&emsp;     问题：ByteArrayStream 是byte[]格式的数据，以内存为目的地进行输入输出；但是应用程序本来就能拿到应用内存中的数据，为什么还要使用ByteArrayStream？  
&emsp;&emsp;     分析：某些api的参数是InputStream或OutputStream；为了适配这些api接口，我们需要将byte数组转换为流的格式。  
&emsp;&emsp;     举例：Request.getInputStream()方法调用后，流中没有数据了，无法复用；Servlet、Filter等组件使用了责任链模式；后续的处理节点可能还需要这个流；    
            所以我们需要将流中的数据读取出来缓存到内存中（byte[]）;并使用这个数组为后续处理节点构建InputStream，此时可以使用ByteArrayStream。
    

## 1-2 字符流
&emsp;&emsp;    1-1的流都是处理二进制数据的，除了FilterInputStream为装饰器接口，拓展流的功能外，其它流都是可以直接使用的流。  
            InputStream/OutputStream都是基于字节的，但是在编码过程中我们很多操作都是直接使用字符；如果我们字节将二进制的流编码成字符，或者将字符解码为二进制流    
            这是一个重复、冗余的工作；所以java抽象了Reader、Writer面向字符的IO功能。  
**注意：编码解码只是按照我们需要的格式（顺序）对二进制文件进行排序、组装；本质上数据的传输都是基于二进制的。java的Reader、Writer都是基于**  
**StreamEncoder和StreamDecoder实现的，既将二进制输入流解码成字符；将字符编码为二进制输出流。**  

>Reader/Writer
>>FileReader/FileWriter：直接从文件读取字符，或向文件写字符；这里的**直接**只是我们看不到而已；因为其内部完成了二进制-字符的编码解码。
>>BufferedReader/BufferedWriter：使用缓冲增加了字符读写的速度；内部也使用了装饰器模式。
>>PrintWriter：格式化输出，也是使用装饰器模式。
>>StringReader/StringWriter：继承自Reader/Writer以内存为目的地进行读写。
>>InputStreamReader/OutputStreamReader：使用**适配器模式**，将字节流转换为字符流。

## 1-3 另类Randomaccessfile
 &emsp;&emsp;   前面我们所说的对文件数据的操作都是通过二进制流实现的，如果我们要修改文件中第十行的数据，我们需要获取整个文件的二进制流，  
            修改后进行输出，而randomaccessfile调用了很多本地方法从而实现直接操作文件；对于刚刚修改的第十行数据，randomaccessfile可以直接进行修改  
            而不需要获取流。  
            
            
**总结：**  IO


#2、Java-NIO
[IO模型](/2-second/2-6IO&NIO/io.md)  
 &emsp;&emsp;   概述：NIO是jdk1.4引入的new IO；使用了Buffer、channel、Selector三个核心组件扩展了传统IO的功能，提升了效率。BIO是同步阻塞的，以read为例；  
 &emsp;&emsp;   （1）调用read后，线程会阻塞；就绪后才能继续执行；造成了线程资源浪费；  
 &emsp;&emsp;   （2）流中的数据读取后就没了，复用不方便。  

## 2-1 Buffer
&emsp;&emsp;   概述：一个数组内存块，通过一组属性实现了高效操作，传统BIO使用流读取数据后就没了，使用Buffer可实现复用。       
&emsp;&emsp;   作用：缓冲从Channel读取的数据或向Channel写的数据。  
&emsp;&emsp;   分类：Byte、Char、Long等其中基本类型的Buffer加上一个特殊的MappedByteBuffer（内存映射，节省系统内存 -.应用内存之间的copy）；常用的是ByteBuffer。  
 重要属性：
 capacity：容量，容量大小可以写入内存的对象个数，ByteBuffer 1000容量是占内存1000b，1000容量的LongBuffer占内存 1000 * 8b。  
 position：读写位置，写模式下初始值0，最大为limit-1(数组长度-1)；读模式下初始值为0，最大值为limit(上一次最后一次写的position)。    
 limit：边界值（写的时候等于容量，读的时候等于上一次写的position最后位置）。
 mark：标记，表示调用mark()方法时 position的位置。 
 重要方法：
 allocate：创建缓冲区；    
 put：写入；  
 flip：将写模式翻转为读模式，既将limit设置为position的值，将position重置为0，将mark重置为-1；  
 get：从缓冲区读取一个对象（byte、Long），position会随之移动，超过limit是会抛错（BufferUnderflowException）；  
 rewind：倒带重读，position和mark重置；  
 mark：标记，将mark的值设置为当前position的位置；  
 reset：将position的值恢复成mark标记的值；  
 clear：将读模式切换为写模式，重置position和mark，将limit设置为capacity。  
 
 ## 2-2 Channel  
 &emsp;&emsp;   概述：一个Channel表示一个NIO连接，一个通道可以表示一个文件描述符，“资源”和应用程序之间数据传输抽象；  
 &emsp;&emsp;   作用：连接文件
 &emsp;&emsp;   分类：FileChannel(文件数据读写)，SocketChannel(Tcp-Socket)，ServerSocketChannel（服务端的TCP-socket），DatagramChannel(基于UDP协议的数据读写)。  
 
 Channel基本方法：open、read、write、close；
 FileChannel详解：  
    阻塞式Channel，不能设置为非阻塞原因(分析)：FileChannel操作的是本地磁盘文件，准备就绪的时间比网络IO短，所以即使非阻塞，也多干不了什么事；而且对于文件的操作
    提供了MappedBuffer，直接映射系统内存，就根本没有IO就绪这一说了，综上所述，没有必要将FileChannel设置为阻塞的。
 
 
三个类被修改：FileInputStream,FileOutputStream,RandomAccessFile
NIO是基于Channel和缓冲区的，唯一直接与通道交互的缓冲区的ButeBuffer;
Buffer.flip进行读写转换；缓冲区的数据读写后不会移除，调用clear
Buffer属性：position、limit、mark
Buffer方法：

Selector:
    基于操作系统的select/epoll时间实现，可以获取当前某些事件就绪的文件描述符;不同的操作系统实现方式不一样，Selector会根据操作系统创建。
    Selector的作用就是获取准备就绪的文件描述符
    linux下实现类为EPollSelectorImpl，根据epoll本地方法实现；
    windows系统下实现类WindowsSelectorImpl，使用的是poll方法。
    
Channel

