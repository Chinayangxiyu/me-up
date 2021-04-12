# 一、Spring cloud简介
## 1.1 微服务核心关键点
（1）服务治理：注册、发现、负载均衡。  
（2）容错：降级、熔断、限流。  
（3）网关：微服务映射、路由管理。  
（4）通信：http  
（5）统一配置：动态存储、版本管理、加密解密。  
（6）微服务监控：日志监控、调用链分析、健康检查。  
（7）服务部署于编排：docker、k8s。  
（8）微服务安全：session管理、JWT认证。

## 1.2 cloud核心项目及功能
（1）eureka：注册中心；
（2）ribbon：客户端负载均衡；
（3）hysterix：熔断、降级；
（4）fegin：在http基础上封装的"服务调用"，默认集成了ribbon、hystrix；


# 二、服务治理和负载均衡
什么是服务治理：隔离服务生产者和消费者，对生产者和消费者进行协调、管理、监听。

### 2.1 Eureka
概述：作为注册中心，服务提供方向Eureka注册服务，消费方从注册中心查询提供的服务列表。
eureka节点之间都是平等的，即使只有一个节点存活eureka也能提供服务，如果eureka全挂了
消费客户端也能通过缓存继续消费。eureka保证了AP。

#### 2.1.1 注册
（1）eurekaServer将服务注册信息维护在缓存（三层缓存）中；在收到注册请求或服务变化时，本节点注册完成后，  
会调用replicateToPeers()方法向其它eureka节点同步。  
（2）一层缓存readOnlyCacheMap，使用的是ConcurrentHashMap；将服务实例封装为客户端获取服务时候需要的  
Http响应，并拥有两个key，ALLAPPS（全量服务实例信息）和ALLAPPSDELTA（增量服务实例信息）；  
（3）二层缓存readWriteCache使用的是GuavaCache(GuavaCache 可以自动回收元素)，readOnlyCacheMap会从  
readWriteCache同步数据，默认30秒每次。  
（4）三层缓存Registry使用ConcurrentHashMap实现），所有的注册的服务信息；


#### 2.1.2 续约
provider注册服务后，每30秒会向eureka发送一次心跳进行一次续约，如果eureka连续三次（默认90秒）  
没收到provider的心跳请求，会将其从服务注册表中提出剔除。但是当eureka处于自我保护机制时不会，  
即使provider超时未续约，不会剔除。

#### 2.1.3 服务下线
provider主动下线时候，会向eurekaServer发送下线请求。

#### 2.1.4 服务获取
客户端首次获取服务是全量获取，之后每30秒向eureka请求一次增量的服务列表（readOnlyCacheMap的ALLAPPSDELTA）  
信息并缓存在本地。


#### 2.1.5 自我保护机制
简述：为了避免网络问题导致大量服务提供者和eurekaServer失去连接，或者连接"超时"而导致服务  
被"错误地"下线，自我保护机制就是eurekaServer通过一些阈值去预判发生了网络问题，开启保护机制  
保证服务不会被下线。

（1）开启自我保护条件：eureka每分钟收到的心跳续期数量低于某个阈值会开启，当心跳数恢复某个阈值会关闭。  
（2）心跳阈值计算公式：服务实例总数量×（30/每个实例心跳间隔秒数）×自我保护系数（0.85）；  
心跳间隔时间设置参数：eureka.instance. lease-renewal-interval-in-seconds；  
自我保护时间设置：eureka.server.renewal-percent-threshold。  
（3）eureka默认每5分钟向其它节点同步服务注册信息，同步失败时也会开启自我保护机制。

#### 2.1.6 服务从注册到可用的2分钟
（1）在非Spring cloud环境下使用eureka，服务提供者并不是在启动时就注册，而是在第一次  
发生心跳请求时才会注册（延迟30秒），而Spring cloud对eureka做了修改，启动后就注册。  
（2）eurekaServer收到注册请求时会先保存到缓存readWriteCache中，而向消费端提供数据的  
缓存readOnlyCache每30秒才会向readWriteCache同步数据。  
（3）客户端缓存的服务列表信息，每30秒才会向eureka发起同步请求。  
（4）Ribbon负载均衡时，会从eureka客户端缓存列表中拉取数据并缓存30秒，过期后重新拉取。

总结：在非Spring cloud环境下，最极端的情况，服务提供方启动后，调用方需要在2分钟以后才能  
调用服务。

#### 2.1.7 Eureka高可用
eureka采用点对点（peer-to-peer）对等通信，没有leader、follower概念，去中心化；  
节点和节点可以相互注册，某个节点宕机eureka客户端会自动切换请求，宕机节点恢复后会重
新加入。

### 2.2 Ribbon


# 三、容错保护-Hystrix

断路器：当一定时间内请求失败超过一定的阈值，或请求失败超过一定的频率则打开断路器；
之后每过一段时间尝试放过一些请求进行试探（半开状态），如果请求通过则关闭断路器，否则继续打开。






