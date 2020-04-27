# IO原理简述
IO详解[IO/NIO](../2-second/2-6IO&NIO/io.md)
&emsp;&emsp;    java-IO：数据的输入输出，因为数据的传输是连续的，抽象成流。
>InputStream/OutputStream：java中流的核心，抽象了**二进制**的数据流处理。  
>>FileInput/OutputStream：以文件为目的地，进行输入输出的二进制流。  
>>ByteArrayInput/OutputStream：以内存为目的地，进行二进制流输入输出。  
>>PipedInput/OutputStream：以线程为目的地，进行数据传输；线程间通信。  
>>SequenceInputStream：注意此类**只有输入流**，进行流的合并。  
>>FilterInput/OutputStream：装饰器接口，使用装饰器模式；用于拓展字节流处理的功能，比如装饰了缓冲区功能的的BufferedInputStream，格式化输出的PrintStream。  

上述的流都是处理二进制数据的，除了FilterInputStream为装饰器接口，拓展流的功能外，其它流都是可以直接使用的流。  
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

装饰器模式
Component -> 组件  
TartComponent implements Component->被装饰对象  
DecoratorComponent implements Component ->装饰器对象也实现了组件，但是内部有一个Component类型的属性既（被装饰器对象），
丰富了被装饰对象的功能。

适配器模式
转换器，原本不相干的两个组件可以协同一起工作；
举例：
目标接口字符流Reader与需要被适配的类InputStream原本是两个不相干的类，为了满足可以读字符功能，有了适配器者InputStreamReader。
InputStreamReader的作用就是将字节输入流转换为能读取字符的字符输入流。其实最里面的适配器是InputStreamReade
