# 疑问

## 1、GB2312编码问题：GBK中数值、英文只占1字节？为啥不做填充。
    在使用Buffer读取txt文本，并使用GB2312解码的时候，数值和英文字母只占 1字节；我们知道中文是占2字节；  
    这样我们在解码的时候，如果当前buffer没有包含所有的字节，就会出现乱码。比如字符“a测试”；buffer长度为2；  
    那么read的时候就只读了 字符“a”和“测”的前半部分编码；最终导致解码乱码。 GBK中的数字和字符为何不填充为2byte。  
    
[引用：](https://blog.csdn.net/delphiwcdj/article/details/7746446)
如下代码执行可能会乱码。
```aidl

   ByteBuffer buffer = ByteBuffer.allocate(2);
            FileChannel fileChannel = new FileInputStream("C:\\Users\\yxy\\Desktop\\springBoot资源访问.txt").getChannel();
            while(fileChannel.read(buffer) != -1){
                buffer.flip();
                System.out.println(
                        Charset.forName("GB2312").decode(buffer));

                buffer.clear();
            }
```