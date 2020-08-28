# 布隆过滤器
概述：一个二进制数组a，默认值都是0，通过多次哈希计算，将一个值"x"每次哈希计算的结果作为a的索引\[5,7,8\]；标记为1；  
现在判断一个值"y"是否已经存在，将这个值同样经过多次哈希计算得到结果\[5,8,9\]，现在数组a索引为9的值是0，说明"y"是肯定  
不存在的。**布隆过滤器用于判断一个值肯定不存在，或有可能存在；不能判断一个值肯定存在**  
关键点：  
（1）哈希算法：影响布隆过滤器性能，[哈希算法](https://blog.csdn.net/weixin_34308389/article/details/92487414)  
（2）位数组长度：影响错误率。[位数组长度](https://blog.csdn.net/ttaannkkee/article/details/102502921)  
现成的轮子
```
        <!-- -->
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>21.0</version>
        </dependency>
```
