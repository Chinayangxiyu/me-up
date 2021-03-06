
# 一、Redis数据结构
## 1.1 字符串（SDS）
SDS结构实现字符串，属性如下  
int len;// 保存的字符长度  
int free;// 空闲的字节长度  
char buf[];// 字符数组

SDS好处
（1）空间预分配，避免了内存重新分配，因为有free维护空闲空间。  
（2）惰性释放，字符缩短时不会物理缩短数组；而是变更len和free值。

## 链表

## 1.2 字典
哈希表实现

## 1.3 跳跃表
## 1.4 整数集合
## 1.5 压缩列表


# 二、持久化
## 2.1 RDB（快照）
使用压缩的二进制文件保存当前的数据库状态；根据更新时间和单位时间更新次数设置保存条件；比如60秒之内，进行了至少10000次修改。  
Redis的周期性函数serverCorn默认每100秒执行一次，该函数执行时会判断设置的保存条件是否满足，满足则调用BGSAVE命令保存RDB文件。

Redis有SAVE和BGSAVE两个命令生成RDB文件，SAVE命令会阻塞服务器进程，BGSAVE是派生子进程创建RDB文件。


## 2.2 AOF
保存redis服务器所执行的写命令来保存数据库状态；保存频率有每个命令同步（always），每秒同步（everysec），操作系统决定同步频率（no）。

AOF重写：AOF持久化因为每一个写命令都会记录，可能导致AOF文件过大；此时为了节约空间，会创建一个子进程去读取当前的数据库状态生成一个  
新的AOF文件（新的AOF文件一般会更小）。这期间主进程还是会处理客户端请求，需要使用一个AOF重写缓冲区记录这段时间内新的写请求，在新的  
AOF文件同步完成后将重写缓冲区的命令追加到新AOF文件中。

## 2.3 持久化对比
（1）RDB持久化实现简单，载入速度更快；但是如果出现宕机，丢失的数据更多。  
（2）AOF持久化的频率更高，极限情况下最多只丢失一条数据；但是有AOF重写、载入恢复慢的问题。

总结：因为AOF更新频率比RDB更高，redis默认优先使用AOF持久化。  
在实际场景中如果不能容忍数据丢失应该使用AOF持久化，比如使用redis做消息中间件；  
如果不关注数据是否丢失，因为启动时候载入数据更快，可以使用RDB持久化，比如单纯的用作缓存。


# 三、事件
Redis是事件驱动程序；Redis事件分文件事件和时间事件。

## 3.1 文件事件
基于反应器模式（Reactor）网络事件处理器，使用I/O多路复用程序同时监听多个套接字；根据套接字执行的任务为其分配不同的任务处理器。  
文件事件处理器由四部分组成：套接字、I/O复用程序、文件事件分派器、事件处理器。[图](./resources/文件事件处理器组成4部分.png)

IO多路复用：通过select/epoll系统调用，一个进程监视多个网络文件描述符，文件描述符就绪（可读，可写）后，内核将就绪的socket返回给应用程序。  
select原理：系统轮询注册的套接字，32位最多注册1024个，64位最多2048位。  
epoll原理：先注册，套接字准备就绪会通过回调存在epoll_ctl集合，只需要获取epoll_ctl集合中的套接字即可，并且epoll的文件描述符的数量限制更大。

## 3.2 时间事件
redis的时间事件是周期性事件，所有的时间时间放在一个无序链表；当时间事件执行器运行时，去遍历链表；满足时间条件的事件会被执行。  
Redis的serverCorn函数就是时间事件的应用，通过周期性执行，来保证Redis稳定运行；默认100毫秒执行一次，可调整。


# 四、多机数据库
## 4.1 主从复制

### PSYNC命令实现主从同步，具有两种模式；
（1）初次同步：主服务器创建并发送RDB文件，主服务器会将收到的写命令传播给从服务器。  
（2）从服务器宕机后同步：每次复制同步主从都会维护一个偏移量，主服务器会有一个先进先出队列维护一定大小的最佳传播命令（复制积压缓冲区）；  
当从机宕机重连后，只同步掉线期间不一样的数据。

### 心跳检测
命令同步期间从机每秒向主机发送一次心跳，并带上偏移量；主服务器根据心跳检测 判断是否继续提供写服务；  
也可以根据心跳检测提供的偏移量主动向从机同步数据。

## 4.2 哨兵模式（Sentinel）
由一个或多个Sentinel实例组成的Sentinel系统监控多个主服务器，以及主服务器所属从服务器；  
当主服务器下线时，自动将主服务器下属从服务器提升为主服务器，保证可用性。

### 工作原理
（1）设置Snetinel监视的主服务器信息，并创建连接（命令连接和订阅连接）。  
（2）Sentinel每10秒向主机发送一次INFO命令，INFO命令可以返回主机及下属从机的信息。  
（3）根据主机返回的从机信息，和从机建立连接并以10秒一次的频率发送INFO命令获取从机信息。  
（4）Sentnel向主机，从机发送信息。  
（5）Sentnel之间会通过频道信息创建命令连接。

### 故障转移
（1）主观下线：Sentnel以每秒的频率向主服务器发送心跳检测，如果未收到回复标记主观下线。  
（2）客观下线：主观下线后，Sentnel向同样监视这一主服务器的Sentnel进行询问，当有足够的（Sentnel配置的quorum参数）  
Sentbel认为服务下线，变为客观下线。  
（3）主服务器客观下线后，Sentnel会选举一个领头Sentinel进行故障转移（随机的，多余半数节点支持），选举过程是每个Sentnel  
向其它节点发送命令，收到命令的节点如果还没有设置leader就将其设置为leader，Sentnel根据返回的信息判断自己是否成为leader；  
（4）故障转移：选出新的主服务器（筛选出最新通信，偏移量最高的从机），修改从机的复制目标，旧的主机设置为从机。



## 4.3 集群（Cluster）
Redis集群由多个节点组成，每个节点都会保存自己和所属集群的所有节点的状态。

### 槽指派
集群的整个数据库被分为16384个槽，数据库的每个键都属于这些槽中的一个，一个节点可以处理0或最多16384个槽；
当数据库中16384个槽都有节点处理时集群处于上线状态，否则集群处于下线状态。
向节点发送CLUSTER ADDSLOTS命令，我们可以将一个或多个槽指派（assign）给节点负；节点不仅保存自己处理的槽信息
也会保存其它节点处理的槽信息。

### 集群中执行命令
服务器接收到命令后，计算key所对应的槽，这个槽如果是当前节点负责就直接处理，否则将返回错误信息和对应的处理节点信息，由客户端重新发一次命令。
节点内部使用跳表保存key和槽之间的关系。

### 重新分片
redis集群可以在线重新将槽指派给其它节点，所以redis集群可以动态扩展（对比哨兵模式的优点）。

### ASK错误
重新分片期间，源节点向目标节点迁移的过程中，如果客户端向源节点发送一个请求；而这个key正是源节点所属的槽；
因为正在迁移，所以这个key可能已经迁移完成，在源节点找不到了。这时源节点就会返回ASK错误，并指引客户端转向
正在导入槽的节点。

### 复制与故障
Redis集群中的节点也分为主节点和从节点，其中主节点负责处理槽，从节点负责同步主节点；当主节点下线时会从复制
的从节点中选出新的主节点继续处理命令。

故障检测：集群中所有的主节点都会互相通信，检测集群中主节点状态；当有半数以上的主节点都报告某个主节点下线后
会向集群广播一条该主节点的Fail消息。

故障转移：主节点所属从节点会有一个被选中，这个新的主节点会承接已下线主节点的所有槽指派 并处理新的命令请求，  
新主节点会向集群广播自己得到提升。

主节点选举：
从节点收到自己复制的主节点下线后，向集群广播一条消息，要求集群中所有有选举权的主节点投这个从节点
为新的主节点，有选举权的主节点会投票给 收到的第一条从节点广播的消息的发送从节点；当有半数以上的
主节点投票给同一从节点时，该从节点得到提升。
否则开启新一轮选举，直到选出新的主节点。



## 4.4 哨兵、集群对比
监控：哨兵模式使用哨兵系统进行监控，集群模式使用工作节点进行监控。
故障转移：哨兵系统是选举一个领头Sentinel进行故障转移，集群模式是从 主节点所属从节点中选出新的主节点进行故障转移。

集群模式进行了分片，每个主节点都能处理写请求 提升了整个系统的可用性；
哨兵模式无法动态扩容，整个库的容量（数据）达到上限后扩容麻烦；而cluster采用分片的模式 能扩展整个集群的容量，  
而且cluster模式支持在线重新分片，可以动态扩容。


# 五、扩展
## 5.1 发布订阅
Redis使用PUBLISH、SUBSCRIBE、PSUBSCRIBE命令实现发布订阅功能

## 5.2 事务
redis的事务是对一个命令集合的原子操作；
MULTI命令将客户端从非事务状态切换至事务状态，此时客户端如果再发送EXEC、DISCARD、WATCH、MULTI四个命令的其中一个，那么服务器立即执行这个命；
否则命令会被添加到一个事务队列里面。
客户端发送EXEC命令时，服务端会顺序执行事务队列里面的命令，并返回结果。
redis的事务没有回滚机制，事务队列中某个命令执行错误 并不影响其它命令的执行。

WATCH命令在EXEC执行之前监视数据库键，如果监视期间数据库键被修改，那么服务器会拒绝执行EXEC命令。
redis会维护所有被监视的键。



# 面试问题
## 过期键删除策略
定时、定期、惰性删除，惰性删除是指下一次获取的
## 内存淘汰策略
拒绝写操作、删除最少使用 设置了超时时间的键、删除最少使用的键、随机删除过期键、随机删除键


## 热key
1、本地缓存；
2、怎么监控热key：客户端，proxy，redis命令

## redis锁
（1）过期时间避免死锁；
（2）休眠加循环实现自旋锁；
（3）setnx，或lua表达式保证加锁的原子性；释放锁使用lua表达式保证原子性；
（4）使用uuid避免 redis同步丢失数据导致的锁释放冲突问题；
原理分析，redis主从同步可能丢失数据，如果thread1获取锁（添加数据）成功后，数据在同步给从服务器的时候丢失；
而此时主机宕机；从服务器升级为新主机时没有thread1添加的锁；此时thread2来获取锁成功；
thread1执行后如果释放锁，因为自己之前添加的锁已经丢失，会释放thread2添加的锁，  
为了避免这种情况，每个线程加锁的时候可以添加uuid，释放锁时比较uuid进行释放，避免错误释放。

（5）锁超时时间续期；经典实现redisson看门狗（不建议开启）
描述：普通的redis锁为了避免死锁添加来过期时间，这个过期时间固定的；
但是当出现业务执行时间超过了设置的过期时间，会导致锁失效；既当前临界区的任务执行未结束，其它线程又可以成功获取锁；
（6）使用hash结构实现可重入；key包含线程信息，value保存int类型的重入次数。



问题：自动续期不能解决根本问题，业务执行时间超长有两个原因；  
1是对业务执行时间的预估不准确，导致设置的过期时间不规范；
2是系统执行出现异常，此时



## redis延迟队列
zset