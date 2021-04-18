# 一、mysql自增主键

# 二、UUID

# 三、雪花算法

[百度雪花算法](https://github.com/baidu/uid-generator/blob/master/README.zh_cn.md)

## 3-1、雪花算法概述
使用64bit生成long类型的ID，保障单节点递增，分布式趋势递增（因为workID、序列不同，所以无法保障完全递增）  
（1）第一位三标记位，因为long类型是带符号的，第一位写死0标记为正数；  
（2）41位表示时间戳，精确到毫秒级；  
（3）10位的机器ID，其中5位workID，5位datacenterID；  
（4）12位毫秒内的递增序列，最大值4096（表示同一数据中心，同一节点一毫秒最多获取4096个ID）

## 雪花算法的问题
（1）分布式无法保证完全递增：因为workID和datacenterID，雪花算法只能保证单节点递增，分布式系统只能根据时间戳实现趋势递增。  
（2）时间回拨：时间回拨会导致ID重复，
（3）workID的分配问题。  
（4）workID + dataCenterID上限32

常用解决方案：
（1）分布式完全递增无法实现，实现完全递增需要维护共享变量，吞吐量和顺序性二选一。  
（2）解决时间回拨：可以每次缓存最后一次获取的时间戳，获取ID时比较当前时间戳（curTime）和  
上一次时间戳（lastTime），如果发生时间回拨抛出异常，或者让当前线程等待（lastTime - curTime）。  
（3）使用zk或者mysql获取workID。
（4）workID上限

## 3-2 百度UidGenerator
[参考](https://github.com/baidu/uid-generator/blob/master/README.zh_cn.md)
在原始的雪花算法分段上上做了调整；
第1位标记位，28位时间戳，22位workID，13位序列号；
（1）使用mysql获取workID，重启插入后返回的ID就是workID，因为上22位所以最大支持（2^22 - 1）次重启。  
（2）使用未来时间解决时间回拨，既不实用System.currentTimeMillis()获取时间，而是使用AtomicLong递增  
来获取时间戳，每次触发信的序列号填充就调用incrementAndGet()增1并更新"时间戳"。  
（3）

## 3-3 美团 Leaf

[参考](https://tech.meituan.com/2017/04/21/mt-leaf.html)
### Leaf-segment
原理简述：  
（1）使用proxy server，一次性的从mysql获取一端自增ID，应用从proxy server获取ID，避免mysql性能瓶颈。  
（2）使用双buffer解决TP999问题（分段ID消耗完时需要重写获取ID，这段时间导致阻塞），双buffer类似滑动窗口，  
当消耗到500个ID时提前获取。

缺点：
（1）ID不够随机，自增ID泄漏发号数量。  
（2）完全依赖DB系统，DB花挂了导致整个系统不可用。

### Leaf snowflak
（1）通过向zookeeper创建顺序持久化节点获取workID；  
（2）本地缓存workID避免zk不可用的影响；  
（3）向zk保存当前机器的时间，生成ID时判断是否发生时钟回拨，若发生则通过zk获取所有机器的系统时间做平均值校验。







