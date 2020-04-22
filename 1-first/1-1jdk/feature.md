# jdk1.8 新特性

## 1、default关键字
        使用default创建接口默认方法，使得在抽象接口的过程中更灵活，将通用的方法直接写在接口中；避免每个实现都去重写一次。
    
## 2、函数式编程&lambda表达式
        函数式编程是指，函数（方法）可以作为参数进行行传递。
        lambda表达式是特定的语法的匿名函数，是便于进行函数式编程的语法糖；lambda中的变量应该是final类型。
lambda示例：
```
     list.stream().forEach(p -> System.out.println(p.getName()));
```
    
## 3、函数式接口
        函数式接口，只有*一个抽象方法*，不限制static、default、Object方法，由@FunctionalInterface注解标记；@FunctionalInterface注解是为了校验当前接口是否
    满足函数式接口规范，实际并不影响这个接口是函数式接口。
函数式接口示例：
```
// check current interface
@FunctionalInterface
public interface LambdaInter<T> {

    // 函数式接口方法
    int changeAge(T t);

    static void  staticMethod(){
        // this static mehod
    }

    default void defaultMethod1(){
        // multi default method1
    }
    default void defaultMethod2(){
        // multi default method2
    }
}
```
定义使用函数式接口的方法：使用的时候将LambdaInter.change()方法作为参数传递，类似匿名内部类的实现方式；不过
函数式接口更简约。
```
     public void change(List<Person> list, LambdaInter<Person> lambda){
            list.forEach(p ->{
                lambda.changeAge(p);
            });
        }
    
     public static void main(String[] args) {
            PersonService service = new PersonService();
            List<Person> list = new ArrayList<>();
            list.add(new Person("张三",20,1));
            list.add(new Person("翠花",10,0));
            service.change(list, (Person p) ->{
                if(p.getGender() == 0){
                    p.setAge(p.getAge() -2);
                }else{
                    p.setAge(p.getAge() +1);
                }
                return p;
            });
    
            list.forEach(p -> System.out.println(p.getAge()));
        }
```

## 4、stream(流)
        什么是流：流水线式处理，分为中间操作和终端操作。理解collection里的对象是原材料，我们需要加工原材料，生产产品；
    那么每一个中间操作理解为装配配件、筛选残次品等操作；最终装箱理解为终端操作。
        使用流的好处：结合lambda表达式，使对集合的操作更简单，可读性更好。
        流的分类
        
### 4-1 流的常用方法
filter:筛选;  
distinct:去重，需要实现hashCode和equals方法;  
limit:截取;  
skip:跳过;  
map:获取元素某个对象生成新的流,  
mapToDouble,mapToInt,mapToLong:获取对应类型的数值流,数值流有max,min等特殊操作;  
flatMap:将"二维"流对象平铺生成"一维"流,需要和map结合使用;既在map()之后调用flatMap();  
flatMapToDouble,flatMapToLong,flatMapToInt:平铺为指定类型的流.
```
 List<Double> s1Scores = Arrays.asList(89.5d, 90d, 100d,70d);
        Student s1 = new Student("jack", 12, 1,s1Scores );

        List<Double> s2Scores = Arrays.asList(78.5d, 81d, 92d,85d);
        Student s2 = new Student("marin", 13, 0,s2Scores );

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        // 先使用map获取成绩单List,然后将所有学生的成绩单平铺到一个流
        students.stream().map(Student::getScores).flatMap(List::stream).collect(Collectors.toList());
```
anyMatch:至少一个元素满足条件,返回boolean类型  
```
    boolean result = list.stream().anyMatch(Person::isStudent);
```
allMatch:所有元素都满足.  
```
    boolean result = list.stream().allMatch(Person::isStudent);
```
noneMatch:所有元素都不满足  
findAny:随便选出一个元素.  
peek:获取一个元素,可以改变流内部的元素,对流本身没有影响,使用forEach也可以做类似操作,但forEach是终端操作而peek可以返回流
```   
    List<Person> names = students.stream().peek(s ->{
               s.setAge(18);
           }).collect(Collectors.toList());
```

### 4-2 流的复杂操作
#### reduce(归约):归约是将集合中的所有元素经过指定运算，折叠成一个元素输出，如：求最值、平均数等，这些操作都是将一个集合的元素折叠成一个元素输出。
```aid23
    reduce函数接收两个参数:
        参数一表示为初始值,也是返回指类型;
        参数二表示进行归约的表达式.
        students.stream().reduce(new Student("default", 0, 0,null), (p1, p2) -> {
                    if(p1.getAge() > p2.getAge()){
                        return p1;
                    }ConcurrentMap
                    return null; 
                });

```
#### collect(收集器):包含一些通用的筛选映射处理的终端操作
计数:Collectors.counting
分组:Collectors.groupingby
转List:Collectors.toList
转Set:Collectors.toSet
对于String类型还有Collectors.join
对于数值类型有max,sum,averaging,min等特殊操作.  
**注意toConcurrentMap**等操作,返回线程安全类型的Map.

## 5、parallelStream(并行流处理)
适合线程安全的情况下数据梳理,基于Fork/join框架实现.
**常用方法参考Stram**  
Fork/Join概述:fork表示任务拆分,join表示结果合并;使用多线程取处理任务,有效利用CPU资源.


## 6、Date API
java8的DateAPI优势:  
(1)支持时区;
(2)自带格式化方法,可读性强;
(3)线程安全,java.util.Date可读性差,使用SimpleDateFormat格式化,但SimpleDateForma的forma&format等方法不是线程安全的.

    Instant :时间戳
    LocalDate :日期
    LocalTime :时间
    LocalDateTime:日期时间
    ZonedDateTime:包含时区的日期时间
**[Date api地址](http://www.matools.com/api/java8)**  



# jdk1.9 新特性