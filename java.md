- jdk1.8, method: abstract, default, static
    - lambda, 方法引用，函数式接口，Stream流
- servlet name
- design model
- api document
- annotation

# 0101

#### 1. 常量 6种

| 类型   | 含义   | 举例   |
| :--- | :--- | :--- |
| 整数   |  所有整数    |      |
| 小数   |  所有小数    |      |
| 字符   |  **单引号**，有且只有一个内容，（空格是一个内容）    |      |
| 字符串  | **双引号**，     |      |
| 布尔值  | 只有2个值     | `true` , `false` |
| null |   只有一个值   | `null`     |

#### 2. 变量

- **值类型** : ``整数`` ``浮点数`` ``字符`` ``布尔`` 
- **引用类型** ``类``  ``数组`` ``接口``  

| 类型    | 关键字         | 占用内存 (字节) | 取值范围    |
| ----- | ----------- | --------- | ----------------------- |
| 字节型   | byte        | 1         | ``-128`` ~`` 127``      |
| 短整型   | short       | 2         | ``-32768`` ~ `` 32767`` |
| 字符型   | char        | 2         | ``0`` ~ ``65535``       |
| 整型    | int (默认)    | 4         | `-2^31`  ~ `2^31 -1` , 约21亿 |
| 长整形   | long        | 8         |  `-2^63` ~ `2^63 -1`                     |
| 单精度浮点 | float       | 4         | `1.4013E-45` ~ `3.4028E+38` |
| 双精度浮点 | double (默认) | 8         | `4.9E-324` ~ `1.7977E+308` |
| 布尔型   | boolean     | 1         | `true` `false`  |


#### 3. java develop kit

|     1.7     |    1.8    | 9.0  |
| :---------: | :-------: | :--: |
|             | 默认方法，静态方法 | 私有方法 |
|             |  Lambda   |      |
|  系统行分隔符号：   |           |      |
| 1.7之前处理io异常 |           |   **   |

# 0111
### 1. final 
- **类** ，不能被继承
- **方法** ， 不能被重写
- **变量** ， 不能被重新赋值
    - 被`final`修饰的常量，一般都是大写

### 2. 权限修饰符

### 3. 内部类
- `成员内部类`
    - **类里面定义类**
    - 格式
        - ```java
            public class Car {
                class Engine {
                    
                }
            }
            ```
    - 访问
        - 从内到外：直接反问，包括私有成员, 遵循就近原则 
            - `Car.this.oil` 外部类
            - `this.oil` 本类
            - `oil` 局部
        - 从外到内：必须先实例化再访问       
         
- `局部内部类`
    - **方法内部定义类**
    - 格式
    - 访问
    
- `匿名内部类`        
    - **实例作为参数**
    - 格式
    - 访问

# 0201
#### 1. Objects
- `equals`
- `toString`
- `hashCode`
- `Objects`

#### 2. 日期时间类
- `Date`
- `DateFormat`
- `Calendar`

#### 3. System
- `currentTimeMilis`
- `arraycopy`

#### 4. StringBuilder
- `append`
- `toString`

#### 5. 基本数据类型包装类

# 0202
#### 1. Iterator接口 /ɪtə'retɚ/ 迭代器

> 1. List 有 索引， Set没有，所以不能用遍历
> 2. 集合遍历的时候，有些数据类型需要向下转型

```javascript 
    Collection<String> coll = new ArrayList<String>();

    coll.add("aaaa");
    coll.add("bbb");
    coll.add("cc");

	for(Iterator<String> it = coll.iterator();it.hasNext();) {
        String name = it.next();
        System.out.println(name);//输出
    }
```



#### 2. for增强

> jdk1.5之后对遍历数组和集合进行优化

```javascript
    Collection<String> coll = new ArrayList<String>();

    coll.add("aaaa");
    coll.add("bbb");
    coll.add("cc");
    coll.add(123);
    coll.add(true);

	for (Object o : coll) {
         //Object o = "aaaa" 多态  
         String s=(String)o;//强转 
         System.out.println(s.length());//输出每个元素长度
     }

```

#### 3. Generic  /dʒə'nɛrɪk/ 泛型
> 泛型不支持基本类型
<br>
> 泛型不支持继承
<br>
> jdk1.7以后，泛型可以后面可以不用写，但是一定要 ``<>`` 

- **自定义泛型类**

```java

public class Generic<E> {
    private E name;

    public Generic() {
    }

    public Generic(E name) {
        this.name = name;
    }

    public void showName () {
        System.out.println(this.name);
    }

    public E getName() {
        return name;
    }

    public void setName(E name) {
        this.name = name;
    }
}

// ==

public class R01 {
    public static void main(String[] args) {
        Generic<String> gen = new Generic<>();
        gen.setName("lucy");
        gen.showName();

        Generic<Integer> genInt = new Generic<>();
        genInt.setName(890);
        genInt.showName();
        
    }
}
```

- **自定义泛型方法**

```java
public class Generic {
    private String  name;

    public Generic() {
    }

    public Generic(String name) {
        this.name = name;
    }

    public <E> void noReturn (E e) {
        System.out.println(e);

    }

    public <E> E hasReturn(E e) {
        return e;
    }

    public <I, S> S mutiReturn (I i, S s) {
        System.out.println(i);
        System.out.println(s);
        return s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// ==

public class R01 {
    public static void main(String[] args) {
        // 自定义泛型方法
        Generic gen = new Generic();

        // 不带返回值
        gen.noReturn(123);
        gen.noReturn("lucy");

        // 带返回值
        Integer i = gen.hasReturn(345);
        String str = gen.hasReturn("cat");
        System.out.println(i);
        System.out.println(str);

        // 多个泛型参数
        String str2 = gen.mutiReturn(888, "dog");
        System.out.println(str2);
    }
}

```

- **自定义泛型接口**

```java
public interface Coll_me<E> {
    void show(E e);
}

// ==

public interface List_me<E> extends Coll_me<E> {
    @Override
    default void show(E e) {};
}

// ==

public class Array_me<E> implements List_me<E> {
    @Override
    public void show(E e) {
        System.out.println(e);

    }
}

// ==
public class T01 {
    public static void main(String[] args) {
        Array_me<String> s = new Array_me<>();
        s.show("cat");

        Array_me<Integer> i = new Array_me<>();
        i.show(978);
    }
}

```


# 0203
## 一、  Collection  /kə'lɛkʃən/ 集合 

- Collection
  - List (Interface)
  - Set (Interface)


### 1.集合概述

1. 集合体系是从 ``jdk1.2`` 以后开始的

| List （单列）  | Set （双列） |
| :----         | :----       |
| 存取有序       |             |
| 具有索引       |             |
| 数据不唯一，可以重复 |       **  |

### 2.集合和数组区别



### 3.集合常用功能

> Collection是所有单列集合``List`` ``Set``的父接口 , 因此定义了一些操作所有集合的通用方法。

   1.``boolean add (E e)``:  把给定的对象添加到当前集合中
2. ``void clear()``:   清空集合所有的元素
 3. ``boolean remove (E e)``:  把给定的对象删除
 4. ``boolean contains(E e)``:  判断是否包含给定对象
 5. ``boolean isEmpty()``:  判断集合是否为空
 6. ``int size()``: 返回集合中元素的个数
 7. ``Object[] toArray()``:  把集合中的元素，存储到数组中，可以**间接**对集合进行遍历。 

## 二、List

- List (Interface)
  - ArrayList
  - LinkedList
  - Vector

> List 接口中的方法：

1.  `void add(int index, E element)` 将指定的元素插入此列表中的指定位置（可选操作）。
2.  `E set(int index, E element)` `返回旧值`用指定的元素（可选操作）替换此列表中指定位置的元素。





### 1. 	ArrayList



### 2.	LinkedList

```
public void addFirst(E e):将指定元素插入此列表的开头。
public void addLast(E e):将指定元素添加到此列表的结尾。
public E getFirst():返回此列表的第一个元素。
public E getLast():返回此列表的最后一个元素。
public E removeFirst():移除并返回此列表的第一个元素。
public E removeLast():移除并返回此列表的最后一个元素。
public E pop():删除并返回此列表的第一个元素。此方法相当于removeFirst() 。
public boolean isEmpty()：如果列表不包含元素，则返回true。
```



## 三、Set

- Set (Interface)
  - HashSet 
    - LinkedHashSet 
  - TreeSet



> Set 接口中的方法：

1. ​



#### 1. 	HashSet

1. jdk1.8以前 哈希表 = 数组 + 链表，1.8以后，哈希表 = 数组 + 链表 + 红黑树；（为了提高查询效率）
2. ​

##### LinkedHashSet

1. 底层是链表 + 哈希
2. 链表：存储有序，哈希：保证数据唯一。

  #### 2.TreeSet


# 0204

## 一、Collections 工具类

> 常用方法:  **都是静态方法，没有构造函数** 

1. ``public static void shuffle(List<?> list)`` 打乱集合顺序
2. ``public static void shuffle(List<?> list, Random rnd)`` 打乱集合顺序 
3. ``public static <T> boolean addAll (Collection<? super T> c, T... elements)`` 
4. ``public static <T> void sort(List<T> list)``  默认是升序
5. ``public static <T> void sort(List<T> list, Comparator<? super T> c)``  默认是升序

### sort 方法扩展

> 如果集合存放的是自定义对象， ``sort(List<T> list)`` 是解决不了的，有2种方式

a:	要求这个自定义对象必须实现 ``Comparable<T>`` 接口，同时，必须实现接口中默认的排序抽象方法 ``int compareTo(T o)``

``` java
public class Student implements Comparable<Student>{
    private String name;
    private int age;
  
	// ......
  
    @Override
    public int compareTo(Student o) {
		//return this.age - o.age;// 升序
        return o.age - this.age;// 降序
    }
}
```



b:	`public static <T> void sort(List<T> list，Comparator<? super T> c)` 将集合中元素按照指定规则排序

```javascript 
Collections.sort(list, new Comparator<String>() {
  	@Override
  	public int compare(String o1, String o2) {
    	//降序：o2 - o1
    	//return o2.compareTo(o1);
      	returen o2.getAge() - o1.getAge();
  	}
});
```



## 二、Map

> **Map 是双列集合，Collection是单列集合，它们都是超级父接口**

1. 特点：由哈希控制键，能保证唯一性
2. 区别：单列集合一次只能存一个对象，对象之间没有任何关系。双列集合一次可以存两个对象，两个对象只有有映射关系。
3. 常用方法：
   1. ``put``
   2. ``remove``
   3. ``get``
   4. ``clear``
   5. ``containsKey`` ``containsValue``
   6. ``keySet``   ``entrySet``

```java
// keySet
public class R01 {
    public static void main(String[] args) {
        HashMap<Integer, Student> hs = new HashMap<>();
        hs.put(1, new Student("lili", 18));
        hs.put(2, new Student("lucy", 19));
        hs.put(3, new Student("lili", 18));
        hs.put(3, new Student("jim", 18));
        System.out.println(hs);
        Set<Integer> keys = hs.keySet();
        System.out.println(keys);

        for(Iterator<Integer> it = keys.iterator(); it.hasNext();) {
            Integer key = it.next();
            System.out.println(key +"  --  "+ hs.get(key));
        }

        for (Integer key : keys) {
            System.out.println(key +"  ==  "+ hs.get(key));
        }
    }
}

// entrySet

public class Case {
    public static void main(String[] args) {
        String  str = "alsdkfjalskgsdfklj";
        HashMap<Character, Integer> strMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (strMap.containsKey(c)) {
                strMap.put(c, strMap.get(c) +1);
            } else {
                strMap.put(c, 1);
            }
        }
        Set<Map.Entry<Character, Integer>> kvs = strMap.entrySet();
        for (Map.Entry<Character, Integer> kv : kvs) {
            System.out.println(kv.getKey() +"\t"+ kv.getValue());
        }
    }
}
```


# 0205

## 一、异常

1. java异常： 程序中出现的bug，或者不正常现象。开发过程中，需要对这些问题进行预先的判断和处理
2. 异常并不是语法错误，语法错了编译是不通过的，代码不能运行。



### 1.异常体系 （Throwable)

1. **Error**
2. **Exception**



### 2.异常分类	(Exception)

1. **编译异常**``checked``
2. **运行异常** ``runtime``

> **小结：** 
>
> 1. Exception是编译异常，编译器会检测到异常是否有处理方案，如果没有处理方案不能通过
> 2. RuntimeException是运行异常，编译器不会检测该异常是否有处理方案，不需要声明



## 二、异常的处理

### 1.抛出异常	（throw)



### 2.声明异常	（throws)



### 3.捕获异常   	  (try...catch)



### 4.代码块		  (finally)



### 5.异常注意事项



## 三、自定义异常

1. 为什么需要自定义异常
2. 自定义异常格式：
3. ​


# 0206

## 一、进程



## 二、线程

> 创建线程：操作线程需要用到 ``java.lang.Thread`` 

**1.、构造方法**

- `public Thread()`:分配一个新的线程对象。
- `public Thread(String name)`:分配一个指定名字的新的线程对象。
- `public Thread(Runnable target)`:分配一个带有指定目标新的线程对象。
- `public Thread(Runnable target,String name)`:分配一个带有指定目标新的线程对象并指定名字。

**2、常用方法**

- `public String getName()`:获取当前线程名称。
- `public void start()`:表示此线程开始执行; java虚拟机调用此线程的run方法。
- `public void run()`:此线程要执行的任务在此处定义代码。
- `public static void sleep(long millis)`:使当前正在执行的线程以指定的毫秒数暂停（暂时停止执行）。
- `public static Thread currentThread()  `:返回对当前正在执行的线程对象的引用。



### 1、创建线程方式一

**步骤：**

1. 自定义**线程类**继承 `Thread`
2. 重写 `Thread` 类的 `run` 方法
3. 实例化自定义**线程类**
4. 调用 ``start()``



```java
public class Task extends Thread {// **这里是继承 Thread**
    @Override
    public void run() {
        Thread t = Thread.currentThread();//获取当前线程的对象
        t.setName("task");
        String name = t.getName();

        for (int i = 0; i < 10; i++) {
            System.out.println(name +"\t"+ i);
        }
    }
}

// ==

public class R01 {
    public static void main(String[] args) {
        Task t = new Task();
        t.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() +"\t"+ i);
        }
    }
}
```







### 2、创建线程方式二

**步骤：**

1. 自定义**任务类**实现接口 ``Runnable`` 
2. 重写 ``run`` 方法
3. 实例化 ``Thread`` ，同时实例化 **任务类** ，当做参数
4. 再调用 ``start（）`` 

```java
public class Task implements Runnable {// **这里是实现 Runnable**
    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        for (int i = 0; i < 10; i++) {
            System.out.println(name +"\t"+ i);
        }
    }
}

// ==

public class R01 {
    public static void main(String[] args) {
        new Thread(new Task()).start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() +"\t"+ i);
        }
    }
}

// == 也可以直接用匿名类的形式，不用定义Task任务类
public class R01 {
    public static void main(String[] args) {
        //new Thread(new Task()).start();// 可以用匿名类的形式

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < 10; i++) {
                    System.out.println(name +"\t"+ i);
                }
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() +"\t"+ i);
        }
    }
}
```

**注意事项:**

>实际开发用的是第二种方式，有以下好处
>
>1. 避免了单继承的局限性
>2. 松散耦合







## 三、并行、并发



## 四、线程控制

使用Thread类中的sleep()函数可以让线程休眠

`public static void sleep(long millis)` 在指定的毫秒数内让当前正在执行的线程休眠（暂停执行）

说明：这个函数是静态的，使用线程类名调用。使用哪个线程调用就让哪个线程休眠。

```java
public class Task implements Runnable{
    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);// 休眠，子类的异常是能捕获，不能声明
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name +"\t"+ i);
        }
    }
}

// ===

public class R01 {
    public static void main(String[] args) {
        new Thread(new Task(), "task").start();// 这里可以直接设置线程名字

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +"\t"+ i);
        }

    }
}
```



## 五、线程安全

```java
public class Task implements Runnable{
    private int tickets = 100;

    @Override
    public void run() {
        while (true) {
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() +"\t"+ tickets);
                tickets--;
            }
        }
    }
}


// ==

public class R01 {
    public static void main(String[] args) {
        Task task = new Task();
        Thread t1 = new Thread(task, "t1");
        Thread t2 = new Thread(task, "t2");
        Thread t3 = new Thread(task, "t3");
        Thread t4 = new Thread(task, "t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
// 以上代码执行，会出现重票和跳票的现象，这就是线程的安全问题。
```

**原因如下：**

1. 如果是单线程就不会出现上述问题。
2. 多个线程操作共享资源。
3. 操作资源的代码有多行，如果是一行或者是很少的情况下，代码执行很快，也不会出现上述问题。
4. CPU的随机切换。本质原因是CPU在处理多个线程的时候，在操作共享数据的多条代码之间进行切换导致的。

**解决方案 :**

1. 无法改变，就是多线程程序。
2. 无法改变，多个线程就是要操作同一个资源。
3. 无法改变，因为就是有多行大妈
4. CPU的运行时无法解决的，针对CPU的切换，是由操作系统去控制的，人为是无法干预的。

**总结 :**

解决线程安远问题，可以人为的控制CPU在执行某个线程操作共享数据的时候，不让其他线程进入到操作共享数据的代码中区，这样就可以保证安全了。这种解决方案称为 **线程同步** 。 

**线程同步** 有3种方式：

> 提示：线程同步需要一个锁，锁是任意对象，但必须是唯一的。



### 1、同步代码块

- 同步代码块的**锁**是**任意对象**

```java
public class Task implements Runnable{
    private int tickets = 100;
    private Object obj = new Object();// 同步代码块的锁是任意对象
    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() +"\t"+ tickets);
                    tickets--;
                }
            }
        }
    }
}
```



### 2、非静态同步方法

- 非静态同步方法的**锁**是**this**

```java
public class Task implements Runnable {
    private int tickets = 50;
    // 非静态同步方法的锁是this
    @Override
    public void run() {
        while (true) {
            syncMethod();
        }
    }

    private synchronized void syncMethod() {// 这里需要 synchronize 修饰符
        if (tickets > 0) {
            try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
          	// 这里用休眠是为了更具体的看到线程的切换，不然执行太快，看不到线程在切换
            System.out.println(Thread.currentThread().getName() + "\t" + tickets);
            tickets--;
        }
    }
}
```



### 3、静态同步方法

- 通过结果发现静态方法也是有锁的，但是静态方法和对象是没有关系的。静态方法是类加载到静态区以后就可以是用类名直接调用的，说明锁对象此时已经存在了，这个锁对象就是 **当前类的字节码文件对象**。

```java
public class Task implements Runnable {
    private static int tickets = 50;// static

    @Override
    public void run() {
        while (true) {
            syncMethod();
        }
    }

    private static synchronized void syncMethod() {// static
        if (tickets > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + tickets);
            tickets--;
        }
    }
}// end
```



### 4、jdk1.5以后锁的升级

- Lock属于接口，不能创建对象，所以我们可以使用它的子类[ReentrantLock](mk:@MSITStore:C:\javaee\开发资料\API\JDK_API_1_6_zh_CN.CHM::/java/util/concurrent/locks/../../../../java/util/concurrent/locks/ReentrantLock.html)来创建对象并使用Lock接口中的函数；

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task implements Runnable {
    private int     tickets = 50;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lockMethod();
        }
    }

    private void lockMethod() {
        lock.lock();// 上锁
        if (tickets > 0) {
            try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() +"\t"+ tickets);
            tickets--;
        }
        lock.unlock();// 解锁
    }
}

```


# 0207

## 一、多线程

### 1、线程状态

1. `new` 新创建	
2. `run` 可运行
3. `teminated` 被终止：死亡状态或者终止状态。出现异常或者任务结束
4. `blocked` 锁阻塞：就是一个线程获取锁对象，其他线程都是处于等待锁的状态。
5. `waiting` 无线等待
6. `tiemd waiting` 计时等待：使用`sleep(long time)` 处于计时等待状态

> 关于CPU执行线程的概念
>
> 1. CPU的执行资格：在CPU执行的队列中等待。还没有被CPU执行。
> 2. CPU的执行权：当前持有CPU资源，正在被执行



### 2、线程通信

- ``wait()`` 
  - 处于无限等待的状态，就会立刻释放锁
  - 分为带参和不带参
  - 该线程必须使用`notify()`方法或者`notifyAll()`方法唤醒，如果被唤醒没有锁就回到 `blocked` 状态
  - 和 ``sleep()`` 的区别
    - `sleep()` 方法不能保证该线程睡眠到期后就开始立刻执行。
    - `sleep()` 和锁无关，只能回到 `Run` 状态
    - `wait()` 和锁有关，没有锁就回到 `blocked` 状态
- ``notify()`` ：唤醒随机的一个等待线程
  - 如果一个等待的线程被唤醒了，但是没有获取到锁对象，那么CPU也不会执行该线程，此时该线程处于 Blocked 锁阻塞状态
- `notifyAll()`  ：唤醒所有等待线程

**重点**

- 线程通信必须要有锁，必须同步，以上方法只能在同步中使用
- 以上的方法都定义在`Object` 中，锁对象是任意的对象，所以必须位于所有的类的共同父类Object中。



### 3、案列

```java
public class Factory {
    private String  brand;
    private int     price;
    private boolean hasPhone = false;
    private int i = 0;

    public synchronized void produce () {
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
        if (hasPhone) {
            try {this.wait();} catch (InterruptedException e) {e.printStackTrace();}
        }
        if (i % 2 == 0) {
            this.brand = "xiaomi";
            this.price = 1111;
        } else {
            this.brand = "iPhone";
            this.price = 8888;
        }
        i++;
        hasPhone = true;
        this.notify();
        System.out.println(this.brand +"\t 正在生产...");
    }

    public synchronized void sell () {
        if (!hasPhone) {// 如果没有手机，就停止售卖
            try {this.wait();} catch (InterruptedException e) {e.printStackTrace();}
        }
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println(this.brand +"\t"+ this.price);
        hasPhone = false;
        this.notify();
    }   
}

// == produce
public class Produce implements Runnable {
    private Factory factory;
    public Produce(Factory factory) {
        this.factory = factory;
    }
    @Override
    public void run() {
        while (true) {
            factory.produce();
        }
    }
}

// == sell
public class Sell implements Runnable {
    private Factory factory;
    public Sell(Factory factory) {
        this.factory = factory;
    }
    @Override
    public void run() {
        while (true) {
            factory.sell();
        }
    }
}

// == test
public class R01 {
    public static void main(String[] args) {
        Factory factory = new Factory();

        Thread produce = new Thread(new Produce(factory), "生产");
        Thread sell = new Thread(new Sell(factory), "销售");

        sell.start();
        produce.start();
    }
}
```









## 二、线程池

在JDK5之前，我们必须手动实现自己的线程池，从JDK5开始，Java内置支持线程池。

Java里面线程池的顶级接口是`java.util.concurrent.Executor`，但是严格意义上讲`Executor`并不是一个线程池，而只是一个执行线程的工具。真正的线程池接口是`java.util.concurrent.ExecutorService`。

**案例**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class R01 {
    public static void main(String[] args) {
        Tickets t = new Tickets();
//        Thread t1 = new Thread(t, "t1");
//        Thread t2 = new Thread(t, "t2");
//        Thread t3 = new Thread(t, "t3");
//        Thread t4 = new Thread(t, "t4");
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();

        ExecutorService es = Executors.newFixedThreadPool(4);
        es.execute(t);
        es.execute(t);
        es.execute(t);
        es.execute(t);
    }
}
```



## 三、Lambda

**案例**

```java
import java.util.Arrays;
import java.util.Comparator;

public class R01 {
    public static void main(String[] args) {
        Student[] stu = {
                new Student("lili", 18),
                new Student("lucy", 20),
                new Student("jim", 17),
                new Student("green", 19),
        };

//        Arrays.sort(stu, new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return o2.getAge() - o1.getAge();
//            }
//        });

//        Arrays.sort(stu, (Student o1, Student o2) -> {// lambda格式
//            return o1.getAge() - o2.getAge();
//        });
		// lambda简化格式
        Arrays.sort(stu, (Student o1, Student o2) -> o2.getAge() - o1.getAge());

        System.out.println(Arrays.toString(stu));
    }
}
```


# 0208	File类

### 1. construction

> 共有6种构造方法，其中有2个是私有的。

1. `public File (String pathname)`
2. `public File (File parent, String child)`
3. `public File (String parent, String child)`
4. `public File (URI uri)` 


```java
public class P01 {
    public static void main(String[] args) {
        File f = new File("G:\\abc");
        System.out.println(f);

        File parent = new File("G:\\abc");
        String child = "test";
        File f2 = new File(parent,child);//G:\abc\test
        System.out.println(f2);

        String parent3 = "G:\\abc";
        String child3 = "test";
        File f3 = new File(parent3,child3);//G:\abc\test
        System.out.println(f3);
    }
}
```


### 2. API

- 创建和删除
    - `public boolean createNewFile() throws IOException`
    - `public boolean mkdir ()`
    - `public boolean mkdirs ()`
    - `public boolean delete ()`

- 获取

```java
public class P01 {
    public static void main(String[] args) {
        File f = new File("G:\\java-learn\\pro02-src\\base.js");
        System.out.println(f.getAbsolutePath());// G:\java-learn\pro02-src\base.js
        System.out.println(f.getPath());// G:\java-learn\pro02-src\base.js
        System.out.println(f.getName());// base.js
        System.out.println(f.length());// 45275
    }
}
```

- 判断

```java
public class P01 {
    public static void main(String[] args) {
        File f = new File("G:\\java-learn\\pro02-src\\base.js");
        System.out.println(f.exists());// true
        System.out.println(f.isHidden());// false
        System.out.println(f.isDirectory());// false
        System.out.println(f.isFile());// true
    }
}
```

- 列表
    - `public String[] list ()`
    - `public Sting[] list (FilenameFilter filter)`
    - `public File[] listFiles ()`
    - `pubnlic File[] listFiles (FilenameFilter filter)` 

```java
public class P01 {
    public static void main(String[] args) {
        File f = new File("E:\\00data\\file");
        String[] list = f.list();
        for (String s : list) {
            System.out.println(s);// 文件名
        }

        File[] files = f.listFiles();
        for (File file : files) {
            System.out.println(file);// 全路径 + 文件名
        }        
    }
}
```


### 3. Filter

- `public File[] listFiles (FilenameFilter filter)`

```java
public class P01 {
    public static void main(String[] args) {
        File file = new File("E:\\00data\\file");
        File[] files = file.listFiles((File f) -> f.isFile() && f.getName().endsWith(".png"));
        for (File f : files) {
            System.out.println(f);
        }
    }
}
```

### 4. 递归

```java
public class P01 {
    public static void main(String[] args) {
        File folder = new File("E:\\00data\\folder");
        scanFiles(folder);
    }

    private static void scanFiles(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                scanFiles(f);
            } else {
                if (f.getName().endsWith(".jpg")) {
                    System.out.println(f.getName());
                }
            }
        }
    }
}
```


# 0209	I/O流

- 字节流：输入、输出
- 字符流：输入、输出
- 注意：细节点，区别
- io异常

### 1. 字节输出流

- ``public abstract class OutputStream extends Object implements Closeable, Flushable``  : 抽象类， **比输入流多实现一个接口**
  -  `public class FileOutputStream extends OutputStream` ：通过子类实现
     -  `FileOutputStream (String name）`
     -  `FileOutputStream (File file)` 

- **常用方法**
  - `void close()`
  - `void write (int b)`
  - `void write (byte[] b)`
  - `void write (byte[] b, int off, int len)` 
- **数据的追加和换行**
  - `\r\n`
  - `System.lineSparator()` 
  - 追加： `new FileOutputStream (String name, Boolean append)`  

```java
public class a_OutputStream {
    public static void main(String[] args) throws Exception {
        String line_feed = System.lineSeparator();
        OutputStream os = new FileOutputStream("a.txt");// 删除文件再新建
        //OutputStream os = new FileOutputStream("a.txt", true);// 追加
        os.write("吃饭".getBytes());
        os.write("\t\n".getBytes());
        os.write("睡觉".getBytes());
        os.write("\t\n".getBytes());
        os.write(("打豆豆"+ line_feed ).getBytes());
        os.write(89);
        os.write(36);
        os.write("\t\n".getBytes());
        os.close();
    }
}
```

#### 2. 字节输入流
- ``public abstract class InputStream extends Object implements Closeable``  ：抽象类
  - ``public class FileInputStream extends InputStream`` ：通过子类实现
    - `FileInputStream(File file) throws FileNotFoundException`
    - `FileInputStream(String name) throws FileNotFoundException`  
      - 一定是文件，不能是目录
      - 如果是不存在的文件会报错
- **常用方法**
  - `void close()`
  - `abstract in read ()` ：一次只能读取一个字节，调用一次移动一次光标，读完之后返回 **-1** 
  - `abstract int read (byte[] b)` 
    - 返回的是读取的个数。
    - 使用前要先定义 **byte** 数组的长度，建议是1024 的整倍数。



```java 
public class b_InputStream {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("a.txt");// hello,world,java
//        System.out.println((char)fis.read());
//        System.out.println((char)fis.read());
//        System.out.println((char)fis.read());
//        System.out.println((char)fis.read());
//        System.out.println(fis.read());

//        int x = 0;
//        while ((x = fis.read()) !=-1){
//            System.out.println((char)x);
//        }

        byte[] bs = new byte[5];
        int len = fis.read(bs);
        System.out.println(new String(bs,0,3));// hel

        fis.read(bs);
        System.out.println(new String(bs,0,3));// ,wo
        System.out.println(new String(bs,3,2));// rl

        fis.close();
    }
}
```

- **byte** 数组转字符串，也可以用偏移量

#### 复制歌曲

```java
public class d_FileReader {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("wukong.mp3");// 先往内存读
        FileOutputStream fos = new FileOutputStream("G:\\java-learn\\kongwu.mp3");// 再往硬盘写

        long start = System.currentTimeMillis();
        byte[] buf = new byte[1024 *8];
        int len = 0;
        while((len = fis.read(buf)) != -1){
            fos.write(buf, 0, len);
        }
        long end = System.currentTimeMillis();
        System.out.println(start - end);
        
        fis.close();
        fos.close();
    }
}
```

#### 3. 字符流输入

- `public abstract class Reader extends Object implements Readable, Closeable` ：抽象类
  - ``public class InputStreamReader extends Reader`` ：子类
    - ``public class FileReader extends InputStreamReader`` ：通过**孙子类**实现



```java
public class d_FileReader {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("a.txt");

        char[] buf = new char[1024];
        int len  = 0;
        while ((len = fr.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
        }

        fr.close();
    }
}
```

#### 4. 字符流输出

- `public abstract class Writer extends Object implements Appendable, Closeable, Flushable`  ：抽象类，  **和字符流输入的接口不一样** 
  - ``public class OutputStreamWriter extends Writer`` ： 子类
    - `public class FileWriter extends OutputStreamWriter`  ：通过**孙子类**实现



```java
public class e_FileWriter {
    public static void main(String[] args) throws Exception {
        String line_feed = System.lineSeparator();
        Writer fw = new FileWriter("b.txt", true);// 追加
        fw.write(line_feed+"沧海一声笑");// new String(buf, 0, len)
        fw.write(line_feed+"涛涛两岸潮");// new String(buf, 0, len)
        fw.close();
    }
}
```

#### 5. I/O异常

```java
public class io_Exception {
    public static void main(String[] args) throws Exception {
        
    }// main

    private static void jdk_19() throws Exception {// 这里还是要声明异常
        System.out.println(1.9);
        FileInputStream fis = new FileInputStream("wukong.mp3");
        FileOutputStream fos = new FileOutputStream("E:\\00data\\file\\wukong04.mp3");
        try (fis; fos) {
            long start = System.currentTimeMillis();
            byte[] buf = new byte[1024 *8];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // jdk1.7之后，所有和关闭资源的类都实现了一个接口 AutoCloseable. 重写 void close()关闭此资源，放弃任何基础资源
    // 目的：简化关闭资源代码
    private static void jdk_17() {
        try (FileInputStream fis = new FileInputStream("wukong.mp3");
             FileOutputStream fos = new FileOutputStream("E:\\00data\\file\\wukong03.mp3");){
            long start = System.currentTimeMillis();
            byte[] buf = new byte[1024 *8];
            int len = 0;
            while ((len = fis.read()) != -1) {
                fos.write(buf, 0, len);
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        } catch (Exception e) {// FileNotFoundException, 如果是这个异常类型，还是会报异常
            e.printStackTrace();
        }
    }

    private static void ago_17() throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("wukong.mp3");
            fos = new FileOutputStream("E:\\00data\\file\\wukong02.mp3");

            byte[] buf = new byte[1024 *8];
            int len = 0;
            while ((len = fis.read()) != -1) {
                fos.write(buf, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}// end
```


# 0210	

### 1. 缓冲流
- 字节缓冲流
    - 输入： ``public class BufferedInputStream extends FilterInputStream``
    - 输出： ``public class BufferedOutputStream extends FilterOutputStream``

```java
// 字节流复制文件
public class A_buffer {
    public static void main(String[] args) {
        // base();
        buffer();
    }// main

    // 缓冲流
    private static void buffer() {
        long start = System.currentTimeMillis();
      	// 注意构造函数参数的类型
        try (BufferedInputStream bis
                     = new BufferedInputStream(new FileInputStream("webstorm.exe"));
             BufferedOutputStream bos
                     = new BufferedOutputStream(new FileOutputStream("F:\\web.exe"));) {

            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);// 933
    }

    // 基本流
    private static void base() {
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream("webstorm.exe");
             FileOutputStream fos = new FileOutputStream("F:\\web.exe");) {

            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);// 3061
    }
}
```

- 字符缓冲流
    - 输入： ``public class BufferedReader extends Reader``
    - 输出： ``public class BufferedWriter extends Writer``

```java
// 字符流复制文件
public class B_buffer_character {
    public static void main(String[] args) {
        buffer();
    }

    private static void buffer() {
        long start = System.currentTimeMillis();
        // 注意构造函数参数的类型
        try (BufferedReader bis = new BufferedReader(new FileReader("a.txt"));
             BufferedWriter bos = new BufferedWriter(new FileWriter("F:\\2.txt"));){
            String txt = null;
            while ((txt = bis.readLine()) != null) {
                bos.write(txt);
                bos.newLine();// 换行
                bos.flush();// 写一定要刷新
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);// 8
    }

    private static void base() {
        long start = System.currentTimeMillis();
        try (FileReader fr = new FileReader("a.txt");
             FileWriter fw = new FileWriter("F:\\1.txt");){
            char[] buf = new char[1024];
            int len = 0;
            while ((len = fr.read(buf)) != -1) {
                fw.write(new String(buf, 0, len));
                fw.flush();// 写的时候要刷新
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);// 3
    }
}// end
```

**案例**
```java
public class C_case {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, String> hm = new HashMap<>();
        BufferedReader bis = new BufferedReader(new FileReader("chushibiao.txt"));
        BufferedWriter bos = new BufferedWriter(new FileWriter("F:\\chushibiao.txt"));
        String txt = null;
        while ((txt = bis.readLine()) != null) {
            String[] str = txt.split("\\.");
            hm.put(Integer.parseInt(str[0]), str[1]);
        }
        Set<Map.Entry<Integer, String>> kvs = hm.entrySet();
        for (Map.Entry<Integer, String> kv : kvs) {
            bos.write(kv.getKey() +"."+ kv.getValue());
            bos.newLine();
            bos.flush();
        }
    }
}
```

### 2. 转换流
- 两个类名
```java
public class D_exchange {
    public static void main(String[] args) throws Exception {
        //output();
        input();
    }

    private static void output() throws Exception {
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream("gbk.txt"), "utf-8");
        write.write("hello, java, 中文");
        write.flush();

        write.close();
    }

    private static void input() throws IOException {
        InputStreamReader read = new InputStreamReader(new FileInputStream("gbk.txt"), "gbk");
        char[] buf = new char[1024];
        int len = 0;
        while ((len = read.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
        }

        read.close();
    }
}
```


### 3. 序列化，反序列化
- `java.io.InvalidClassException`

- 概述
    - Java 提供了一种对象**序列化**的机制。用一个字节序列可以表示一个对象，该字节序列包含该`对象的数据`、`对象的类型`和`对象中存储的数据`等信息。字节序列写出到文件之后，相当于文件中**持久保存**了一个对象的信息。
反之，该字节序列还可以从文件中读取回来，重构对象，对它进行**反序列化**。`对象的数据`、`对象的类型`和`对象中存储的数据`信息，都可以用来在内存中创建对象。

- 注意点
    - 该类必须实现`java.io.Serializable ` 接口，`Serializable` 是一个标记接口，不实现接口会抛出`NotSerializableException` 。
`Serializable`：是一个标记性接口。接口中没有任何的方法，所以称为标记型接口！它仅仅是一个标识。是一个标记接口为了启动一个序列化功能。只有具备了这个接口标识的类才能通过Java中的序列化和反序列化流操作这个对象。
注意：只要一个类实现了Serializable接口，那么都会给每个实现类分配一个序列版本号作为唯一标识。
    - 如果有一个属性不需要可序列化的，该属性必须注明是瞬态的，使用`transient` 关键字修饰。
    - 序列化版本号  `serialVersionUID`

- 构造函数、方法
    - `ObjectOutputStream (OutputStream out)`  
      - `public final void writeObject (Object obj)` : 将指定的对象写出。
    - `ObjectInputStream (InputStream in)` 
      - `public final Object readObject ()` : 读取一个对象。

```java
public class Person implements Serializable {
    private static final long serialVersionUID = -1992128268871889058L;
    private String  name;
    private int     age;
  
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
  
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

// ==

public class E_serializable {
    public static void main(String[] args) throws Exception {
        Person lucy = new Person("jim", 18);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.txt"));
        oos.writeObject(lucy);
        oos.close();
        
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.txt"));
        Person person = (Person)ois.readObject();
        System.out.println(person.getName() +" -> "+ person.getAge());
        ois.close();
    }
}
```


### 4.	打印流

- 可以向硬盘文件输出内容

```
java.lang.Object
  java.io.OutputStream
      java.io.FilterOutputStream
          java.io.PrintStream
```

`public class PrintStream extends FilterOutputStream implements Appendable, Closeable` 

```java
public class A_PrintStream {
    public static void main(String[] args) {
        PrintStream ps = System.out;
        ps.println("sout");

        try (PrintStream print = new PrintStream("a.txt")) {
            print.println("print java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```


### 5. 属性流

```
java.lang.Object
  java.util.Dictionary<K,V>
      java.util.Hashtable<Object,Object>
          java.util.Properties
```

``public class Properties extends Hashtable<Object, Object>`` 

- 主要的特点是能从硬盘加载数据
- 没有泛型的双列集合，k , v 都是String

**方法**

- `setProperty<String key, String vaule>`  : 相当于调用父类的 put 方法
- `stringPropertyName` : 获取所有的 key 值。
- `getProperty<String key>` : 获取 value 值

```java
public class B_Properties {
    public static void main(String[] args) {
        Properties p = new Properties();
        p.setProperty("a", "9");// 存值
        p.setProperty("c", "8");
        p.setProperty("e", "7");
        p.setProperty("n", "6");
        Set<String> keys = p.stringPropertyNames();// key值是 Set 类型
        for (String key : keys) {
            System.out.println(key +" -> "+ p.getProperty(key));// 取值
        }
    }
}
```


# 0211	Socket

- **复制文件：字节流 -> 字符流 -> 字符缓冲流** 

```java
public class D_server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8081);
        InputStream is = server.accept().getInputStream();
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));// 转换流
        BufferedWriter bos = new BufferedWriter(new FileWriter("copy.txt"));// 转换流

        String txt = null;
        while ((txt = bis.readLine()) != null) {
            bos.write(txt);
            bos.newLine();// 换行
            bos.flush();// 写一定要刷新
        }

        is.close();
        server.close();
    }
}

public class D_client {
    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localhost", 8081);
        OutputStream os = client.getOutputStream();
        InputStream is = new FileInputStream("a.txt");
        byte[] buf = new byte[1024];
        int    len = 0;
        while((len = is.read(buf)) != -1){
            os.write(buf, 0, len);
        }

        os.close();
        client.close();
    }
}
```



1.  文件只有一个，多人上传只会看到最后一个人的。（随机文件名）
2.  服务器运行一次就关闭。（while 循环）
3.  ServerSocket不能重复创建，多个端口冲突，所以不能关闭。
4.  多人上传要排队。（多线程）
5.  给Client反馈接收完毕信息。（卡主了）
6.  数据丢失

```java
public class F_server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8083);

        while (true) {
            Socket accept = server.accept();
            new Thread(() -> {
                try {
                    String fileName = new Date().toString().substring(11, 19).replace(":", "-");
                    InputStream is = accept.getInputStream();
                    OutputStream os = new FileOutputStream(fileName + ".jpg");

                    byte[] buf = new byte[1024];
                    int    len = 0;
                    while((len = is.read(buf)) != -1){
                        os.write(buf, 0, len);
                    }
                    os.close();
                    is.close();
                    System.out.println("update success!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

// ==

public class F_client {
    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localhost", 8083);
        OutputStream os = client.getOutputStream();
        InputStream is = new FileInputStream("pic.jpg");

        byte[] buf = new byte[1024];
        int    len = 0;
        while((len = is.read(buf)) != -1){
            //System.out.println(new String(buf, 0, len));
            os.write(buf, 0, len);
        }
        os.close();
        is.close();
        System.out.println("update finish!");

    }
}
```


# 0212

## 一、函数式编程

### 1. lambda实现

```java
public interface Inter {
    int sum(int a, int b);
}

// ==
public class R01 {
    public static void main(String[] args) {
//        int sum = implementMethod(3, 5, new Inter() {
//            @Override
//            public int sum(int a, int b) {
//                return a * b;
//            }
//        });

        int sum = implementMethod(5, 6, (a, b) -> {
            return a * b;
        });

        System.out.println("sum = " + sum);
    }

    private static int implementMethod(int a, int b,Inter lam) {
        return lam.sum(a, b);
    }
}
```



### 2. lambda作为参数





### 3. lambda作为返回值



## 二、方法引用


# 0213

## 一、函数式接口

### 1. 	Supplier	生产对象



### 2.	Consumer	消费对象

- `void accept (T t)` 

```java
public class B_Consumer {
    public static void main(String[] args) {
        String[] str = {"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "德玛西亚,男"};
        method(str, s -> {
            String result = "姓名："+ s.split(",")[0];
            System.out.print(result);
        }, s -> {
            String result = "   性别："+ s.split(",")[1];
            System.out.println(result);
        });
    }

    private static void method(String[] str, Consumer<String> one, Consumer<String> two) {
        for (String s : str) {
            one.andThen(two).accept(s);
        }
    }
}
```



### 3.	Predicte	判断接口

- `boolean test(T t);`
- `default Predicate<T> and (Predicate<? super T> other)`
- `default Predicate<T> or (Predicate<? super T> other)`
- `default Predicate<T> negate ()`



```java
public class C_Predicate {
    public static void main(String[] args) {
        String str = "apple";
        test(str, (s) -> {
            return str.contains("a");
        });

        and(str, s -> {
            return s.contains("a");
        }, s -> {
            return s.length() > 5;
        });

        negate(str, s -> {
            return s.contains("a");
        });
    }

    private static void negate(String str, Predicate<String> lambda) {
        boolean negate = lambda.negate().test(str);
        System.out.println("negate = " + negate);
    }

    private static void and(String str, Predicate<String> one, Predicate<String> two) {
        boolean and = one.and(two).test(str);// 判断多个条件
        System.out.println("flag = " + and);
    }

    private static void test(String str, Predicate<String> lambda) {
        boolean test = lambda.test(str);// 判断一个条件
        System.out.println("flag = " + test);
    }
}
```





### 4.	Function	转换接口

- `R apply (T t)`

```java
public class D_Function {
    // 转换接口     String str = "lucy ,18"; -> 年份转月份
    public static void main(String[] args) {
        String str = "lucy,18";
        method(str, s -> {
            return s.split(",")[1];
        }, s -> {
            return Integer.parseInt(s);
        }, s -> {
            return (s *12) +"";// 计算后再转成字符串
        });
    }

    private static void method
    (String str, Function<String, String> one, Function<String, Integer> two, Function<Integer, String> three) {
        String result = one.andThen(two).andThen(three).apply(str);
        System.out.println("result = " + result);
    }
}
```





## 二、Stream流

> `Interface Stream<T>` 
>
> Stream本身并不存储任何元素，也不存储地址，是一个集合元素的函数模型。

- **函数式编程**

```java
public class F_Stream {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("张三丰");
        list.add("张无忌");
        list.add("赵敏");
        list.add("周芷若");
        list.add("灭绝师太");
        list.add("张翠山");
        list.add("张三");
        list.stream()
          .filter(item -> item.length() == 3)
          .filter(item -> item.startsWith("张"))
          .forEach(System.out::println);// 张三丰， 张无忌， 张翠山
    }
}
```



### 1.	获取流

- 单列集合
    - 在单列集合的父接口 `Collection` 里有个默认方法 `default Stream<E> stream()` 
    
- 双列集合
    - 转化成单列集合 `keySet`  `entrySet` 
    
- 数组
    - 使用 `Stream` 接口中的方法： `static <T> Stream<T> of (T... values)` ，参数是可变参数，就是数组
    - 数组类型是 **引用类型**
    



```java
public class E_Stream_get {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        LinkedList<String> linked = new LinkedList<>();
        HashSet<Integer> hash = new HashSet<>();

        Stream<Integer> arrayStream = array.stream();
        Stream<String> linkedStream = linked.stream();
        Stream<Integer> hashStream = hash.stream();

        // 双列集合
        HashMap<Integer, String> hm = new HashMap<>();
        hm.put(11, "chicken");
        hm.put(22, "duck");
        Set<Integer> keys = hm.keySet();
        for (Integer key : keys) {
            System.out.println(key +" -> "+ hm.get(key));
        }
        Set<Map.Entry<Integer, String>> kvs = hm.entrySet();
        for (Map.Entry<Integer, String> kv : kvs) {
            System.out.println(kv.getKey() +" -> "+ kv.getValue());
        }

        // 数组
        Integer[] nums = {1,34,56,234,78,97,9};// int[] nums = {}; 报错
        Stream<Integer> numStream = Stream.of(nums);
        
    }
}
```





### 2.	常用方法

> Stream只能消费一次

- `long concat ()` ：返回流中元素的个数。
- `static <T> Stream<T> concat (Stream<? extends T> a, Stream<? extends T> b)`  ：创建一个新的懒惰连接流
- `Stream<T> limit (long maxSize) ` 
- `Stream<T> skip (long n)` 
- `void forEach (Consumer<? super T> action)`  
- `Stream <T> filter (Predicate<? super T> predicate)` 
- `<R> Stream<R> map (Function<? super T,? extends R> mapper)` 



```java
public class G_Stream_methods {
    public static void main(String[] args) {
        filter();
    }

    private static void filter() {
        Stream<String> one = Stream.of("acc", "bcc", "add");
        one.filter(item -> item.startsWith("a")).forEach(System.out::println);
    }

    private static void concatMethod() {
        Stream<String> one = Stream.of("apple");
        Stream<String> two = Stream.of("banana");
        Stream<String> result = Stream.concat(one, two);
        result.forEach(System.out::println);
    }

    private static void map() {
        Stream<String> flow = Stream.of("11", "22", "33", "44", "55");
        flow.map(Integer::parseInt).forEach(System.out::println);
        // flow.map(item -> {
        //     return Integer.parseInt(item);
        // }).forEach(System.out::println);
    }

    private static void skip() {
        Stream<String> flow = Stream.of("q", "w", "e", "r", "t", "y");
        flow.skip(4).forEach(item -> System.out.println(item));
    }

    private static void limit() {
        Stream<String> flow = Stream.of("q", "w", "e", "r", "t", "y");
        flow.limit(4).forEach(item -> System.out.println(item));
    }

    private static void count() {
        Stream<String> one = Stream.of("acc", "bcc", "add");
        System.out.println(one.count());
    }
}
```



**拼接方法和终结方法** 

- 终结方法
  - `count`	`filter`
- 拼接方法



### 3.	并发流

> 当需要对集合或数组中的元素进行并发操作时，需要仔细考虑多线程环境下的竞争和锁的问题。

**关键字：parallel , parallelStream**



#### 1. 	获取方式

1.   转换获取
   - `Stream` 的父接口 `java.util.stream.BaseStream` 中定义了 `parallel` 方法。
2.   直接获取
   - 使用集合 `Collection` 中的 `parallelStream` 方法直接获取支持并发操作的流。

```java
public class H_concurrence {
    public static void main(String[] args) {
        secondMethod();
    }

    private static void secondMethod() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "q", "w", "e", "r", "t", "y");
        list.forEach(System.out::print);// 也可以用forEach()

        Stream<String> listStream = list.parallelStream();
        listStream.forEach(System.out::println);

    }

    private static void firstMethod() {
        Stream<String> flow = Stream.of("q", "w", "e", "r", "t", "y");
        Stream<String> parallel = flow.parallel();
        parallel.forEach(System.out::println);
    }
}
```



#### 2.	收集结果

**关键字：collect , Collectors**

1.   收集到集合中

    - `Stream` 流提供方法 `R collect (Collector c)` 
    - 参数是确定收集到哪种集合中，`List` 、`Set` 
        - `public static <T> Collector<T, ?, List<T>> toList()`：转换为`List`集合。
        - `public static <T> Collector<T, ?, Set<T>> toSet()`：转换为`Set`集合。

2.   收集到数组中

    - `Stream` 内部有 `toArray()` 方法，根据**参数列表**分为两种 

        - `Object[] toArray();` 不传参返回的是 Object 类型，不建议使用。

        - `<A> A[] toArray (IntFunction<A[]> generator);` 参数传什么类型的数组，那么返回的就是对应类型的数组

       ​

```java
public class J_collect {
    public static void main(String[] args) {
        thirdMethod();
    }

    private static void thirdMethod() {
        Stream<String> flow = Stream.of("q", "w", "e", "r", "t", "y", "w");
        // String[] array = flow.toArray(new IntFunction<String[]>() {
        //     @Override
        //     public String[] apply(int value) {
        //         return new String[value];
        //     }
        // });
        
        // String[] array = flow.toArray(value -> new String[value]);

        String[] array = flow.toArray(String[]::new);
    }

    private static void secondMethod() {
        Stream<String> flow = Stream.of("q", "w", "e", "r", "t", "y", "w");
        Object[] array = flow.toArray();// Object类型，不建议使用
    }

    private static void firstMethod() {
        Stream<String> flow = Stream.of("q", "w", "e", "r", "t", "y", "w");
      
        // operated upon or closed
        //List<String> list = flow.collect(Collectors.toList());
        
        Set<String> set = flow.collect(Collectors.toSet());

        for (String s : set) {
            System.out.println(s);// 没有最后的 "w", 而且打印顺序不一样
        }
    }
}
```


# 0214

## 一、	Class对象 获取方式

1.  所有类中有都有一个静态成员变量 `class => Person.class`
2.  `Object`类中有个`getClass(); => Person.getClass()`
3.  `Class`类中有个静态方法 `static Class forName (String className) => Class.forName("类的全名")`




```java
public class A_forName {
    public static void main(String[] args) throws Exception {
        Class person = Person.class;// 1. 类.class

        Class lucy = new Person("lucy", 18).getClass();// 2. 实例.class()

        Class clazz = Class.forName("cn.num0214.Person");// 3. Class类的静态方法
        System.out.println("clazz = " + clazz);
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());
    }
}
```


## 二、	预定义对象

> 任何一种数据都有自己的字节码对象



```java
	    Class i = int.class;
        System.out.println("i = " + i);// int

        Class v = void.class;
        System.out.println("v = " + v);// void

        Class arr = int[].class;
        System.out.println("arr = " + arr);// class [I
        
        public String toString() {
            return getClass().getName() + "@" + Integer.toHexString(hashCode());
        }
```


## 三、	reflect 反射

> 反射是通过一个**类的Class对象**把类中的各种成员映射成对应的java类。 类的Class对象 == 字节码对象

### 1. 	反射获取类中的构造函数

- `public Constructor<T> getDeclaredConstructor (Class<?>... parameterTypes)`
  - params: 是Class的类型
  - return： 返回的构造方法是Construction类型
- 访问私有的Filed、Method、Construction => 权限检测 => 爷爷类`AccessibleObject` 的方法解除
  - `c.setAccessible(true)`

### 2.	反射获取成员变量

- `Field getDeclaredField (String name)`  
  - params:	字段名
  - return:   返回的字段是 Field类型，有`get` 和 `set` 方法
  - `Object get (Object obj)` 
    - params: 实例
    - return：对应的值
  - `void set (Object obj, Object value)`
    - params: 实例， 对应的值

### 3.	反射获取成员方法

- `Method getDeclaredMethod(String name, Class<?>... parameterTypes)`
  - params: 方法名， 方法参数类型 - 必须是Class类型；
  - return：执行方法的返回值，如果没有则返回null



```java
    public static void main(String[] args) throws Exception{
        Class clazz = Class.forName("cn.num0214.Person");
        Constructor c = clazz.getDeclaredConstructor(String.class, int.class);
        Person lucy = (Person)c.newInstance("lucy", 18);
        System.out.println("lucy = " + lucy);
		
        // 获取成员变量
        Field f = clazz.getDeclaredField("name");
        System.out.println("f = " + f);// private java.lang.String cn.num0214.Person.name
        f.setAccessible(true);// cannot access a member of class
        String name = (String)f.get(lucy);// lucy是实例
        System.out.println("name = " + name);
	   
        Method getAge = clazz.getDeclaredMethod("getAge");
        Integer age = (Integer)getAge.invoke(lucy);// lucy是实例
        System.out.println("age = " + age);

        Method getName = clazz.getDeclaredMethod("getName");
        String get_name = (String)getName.invoke(lucy);// lucy是实例
        System.out.println("get_name = " + get_name);


        Method setName = clazz.getDeclaredMethod("setName", String.class);
        //需要传参， 用参数及类型区分重载, 而且参数是 .class 类型
        setName.setAccessible(true);
        Object lili = setName.invoke(lucy, "lili");
        System.out.println("lili = " + lili);
        System.out.println(getName.invoke(lucy));
    }
```


## 四、	JUnit 单元测试
- test
- before
- after


## 五、	annotation 注解

### 1.	自定义注解

```java
// 1. public abstract 可以省略，属于固定修饰符
// 2. 数组形式的赋值
// 3. 如果只有一个属性，并且属性名是value，可以省略
// 4. 同一个方法或类上只能使用一次同一个注解，可以使用多个不同的注解
public @interface G_annotation {
    public abstract String name();

    String gender() default "male";// 给默认值

    String[] interest();// 可以用数组

    int    age();
}
```

### 2.	元注解

- `@target` 修饰注解使用的位置
- `@retention` 修饰注解的生命周期



### 3.	反射获取注解及使用


# 0215 proxy
## 1.  Class对象的介绍

## 2.  Class对象获取的方式
- 所有类中有都有一个静态成员变量 `class` => `Person.class`
- `Object` 类中有个 `getClass()` => `Person.getClass()`
- `Class` 类中有个静态方法 `static Class forName (String className)` => `Class.forName("类的全名")`

## 3.  预定义对象
- 任何一种数据都有自己的字节码，

## 4.  反射的概念
> 反射是通过一个`类的Class对象`把类中的各种成员映射成对应的java类。 类的Class对象 == 字节码对象

- 1.反射获取类中的构造函数

- 2.反射获取成员变量
    - Field getDeclaredField (String name): 获取某个类中的成员变量
    - 参数：name = 字段名
    - 返回值：返回成员变量的所属类型
        - 获取值：Object get (Object obj)
            - params: 获取哪个实例的值
            - return: 返回对应的值
        - 设置值：void set (Object obj, Object value)
        
- 3.反射获取成员方法
    - Method getDeclaredMethod(String name, Class<?>... parameterTypes)
        - params:
            - name: 方法的名字
            - Type: 方法参数的类型，=> 必须Class的类型
        - return: 执行方法的返回值，如果没有，返回null。

## 5.  Junit 单元测试

## 6.  注解
- JDK注解
- 自定义注解
- 源注解
- 解析注解
    
## 7.  ClassLoader 类加载器
- `.java`(内存) => `jvm` => `.class文件`(硬盘) => `ClassLoader` => `Class类`的对象(内存)

- Bootstrap         = 根类加载器
    - 最顶级的类加载器，加载的都是sun公司定义好的类，ex: String, Integer, File...

- ExtClassLoader    = 扩展类加载器
    - 加载的是JDK内部自己使用的类

- APPClassLoader    = 应用类加载器
    - 加载ClassPath指定的jar或目录，ClassPath存放的是路径，默认是IDEA工程下的out目录，里面存放的是我们写好的.class文件

- classLoader获取方式
    - clazz.getClassLoader()

- classLoader加载文件的路径是相对`src`, File文件加载的路径是相对 `project`

## 8.  动态代理
- 动态代理是哪个版本的 ? = jdk1.6 和 jdk9.0 不一样

- 1. 获取某个被代理类的代理对象，使用java中的 Proxy 类完成
    - java.lang.reflect.Proxy
    - public class Proxy extends Object implements Serializable
        - static Object newProxyInstance
            (ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
            - return: 返回一个指定接口的代理类实例或对象。
            - params:
                - loader: 类加载器
                - interfaces: 被代理对象的所有接口的数组
                - InvocationHandler h: 调用处理器，是一个接口

## 9.  XML
- 作用: XML就是一种数据格式，自定义的数据格式
    
- 如何写XML
    - 声明
        - `<?xml verson="1.0" encoding="UTF-8"?>`
    - 标签
    - 属性
    - 转义符
    
- 约束XML
    - DTD (Document Type Definition)
    - Schema (.xsd)
    
- 解析XML
    - DOM: 把文档整体加载到内存形成DOM树在解析，支持增删改查。
    - SAX: 逐行解析，只支持查询，不支持增删改查。
    - PULL: 类似SAX, Android常用解析技术
    - JAXP: SUN公司的官方解析技术，支持DOM和SAX
    - JSOUP: 一种解析HTML的特定解析的开发包
    - JDOM: 开源组织的DOM方式的解析技术
        - DOM4J: JDOM的分离版、增强版，开源，DOM解析方式，Hibernate底层的XML解析技术

↑ ↓ ← → ↖ ↗ ↙ ↘

# 0301

## 一、	SQL分类

- DDL 
    - 数据库定义语言 `Data Definition Language` ，用来定义数据库对象
    - create , drop , alter, truncate
   
- DML
    - 数据操纵语言 `Data Manipulation Language` ，数据表中更新、增加、删除记录
    - update , insert , delete
   
- DCL
    - 数据控制语言 `Data Contrlol Language` ，设置用户权限和控制事务
    - `grant`, `revoke`, `start transaction`, `commit`, `rollback`
    - 1.创建用户
        - `create user '<username>'@'<ip>' identified by <pwd>`
        - `<ip>` : 指定用户在哪个主机上可以登陆，本地用户 `localhost`,  任意主机 `%`
    - 2.用户授权
        - `grant <grant1>, <grant2> on <dbName>.<tableName> to '<user>'@'<ip>'`
            - 权限：`create`,`alter`, `select`, `insert`, `delete`, `update` ... `all`
    - 3.撤销权限        
        - `revoke <grant1>, <grant2> on <dbName>.<tableName> from '<user>'@'<ip>'`
    - 4.查看权限
        - `show grants for '<user>'@'<ip>'`
    - 5.删除用户
        - `drop user '<user>'@'<ip>'`
    - 6.修改密码
        - `mysqladmin -u root -p password <pwd>` ：超级管理员修改密码
        - `set password for '<user>'@'<tableName>' = password('<pwd>')` ：普通用户使用超级管理员修改
   
- DQL
    - 数据查询语言 `Data Query Language` ，记录的查询
    - select ...

## 二、	SQL语句	

### 1.  数据库的介绍、安装、卸载

- 连接数据库     `mysql -h root -p`
- 连接其他数据库 `mysql -h userIP -P userPORT -u root -p`

### 2.  数据库的操作

- 退出            `exit;`
- 查询所有DB       `show databases;`
- 创建DB          `create databese <first> character set utf8`
- 查看DB编码       `show create database <first>`
- 修改DB编码      `alter database <first> character set utf8`
- 删除DB         `drop database <first>`
- 查看当前使用DB   `select database()`
- 切换DB         `use <first>`
- 导入sql文件     `source <fileAbsolutePath>`

### 3.  表的操作

- 查看所有表     `show tables`
- 查看表结构     `desc <tableName>`
- 创建表        `create table <tableName> (id int(10) not null primary key auto_increment, name varchar(50), pwd varchar(50));`
- 修改表名      `rename table <oldName> to <newName>`
- 删除表        `drop table <tableName>` **表不存在了**
- 删除表再新建    `truncate <tableName>` **清空**
- 查看表编码     `show create table <tableName>`
- 修改表编码     `alter table <tableName> character set <utf8>`
- 增加字段       `alter table <tableName> add <fileName> <type>(length)`
- 删除字段       `alter table <tableName> drop <filedName>`
- 字段约束
    - 主键  `PRIMARY KEY`   
    - 自增长 `AUTO_INCREMENT`
    - 唯一  `UNIQUE`
    - 非空  `NOT NULL`
- 字段(名字、类型、长度、约束)的添加和修改，都相当于是重写 
    - `alter table <tableName> modify id int(11) auto_increment;`

### 4.  数据的操作(insert, delete, update)

- 查看表数据             `select* from <tableName>`
- 插入数据(指定字段)      `insert into <tableName> (id, name) values (null, 'jim')`
- 插入数据(省略所有字段名) `insert into person values (null, 'luogeger', '522933', '186...', 'China')`
- 修改字段值(where)      `update <tableName> set pwd='123456'` => 所有记录的字段值都被修改成123456
    - `update <tableName> set pwd='522933' where id=2`
- 删除记录(where)        `delete from <tbaleName> where id=2;`
- 删除表中所有记录        `delete from <tableName>` **表清空了**

### 5.  查询语法    
- `null` 不参与运算

- 运算符
    - `<> !=` 不相等 
    - `and` =>         `select name, age from person where age = 23 and name = 'lisi';`
    - `between and` => `select name, score from person where score between 81 and 93;`
    - `in` => 指定值中任意取值 `where age in (val1, val2, val3)`
    - `or` =>               `where age = val1 or age = val2 or age = val3`

- 模糊查询 
    - `like '%刘%'`, 只要有刘就可以, => `%` => 表示任意个数的字符： >=0
    - `like '%刘‘` , 结尾
    - `like '刘%‘`, 开始
    - `like '_刘_`, 前后只能有一个， `where name like '_刘_'`
    - `like '_刘`
    - `like '刘_`
    - `is null`         `where birthday is null`
    - `is not null`     `where name is not null`

- 过滤重复  
    - `distinct` => `select distinct age from person`, => 显示不重复的年龄

- 结果排序  
    - `order by` 对结果进行排序，默认是升序，=> `asc` 
    - `null`  在排序是最小的
    - `select * from person order by age asc`
    - `select * from person order by age dasc`

- 起别名  
    - `as` , 也可以省略
    - `select name as 姓名, age as 年龄 from person;`

### 6.  SQL聚合函数

- count
    - 统计多少条记录, `null` 不参与统计
    - `select count(name) from person`
    - `select count(name) from person where age > 18`

- sum
    - 多列求和, `null` 不参与计算 
        - `select sum(score) from students`
    - `ifnull (列值，整体返回值)`  
        - `select sum( ifnull(score, 0)) from students` : 如果分数为 null, 提供默认值0
    - `truncate (数值，保留位数)`
        - `select truncate(sum(score), 2) from students`

- avg
    - 平均数 `select avg(score) / count(*) from students`

- max, min
    - `null` 不参与计算
    - `select max(score) 最高分, min(score) 最低分 from students;`
- group by 
    - `select product, sum(price) from shoppingcart group by product`
    - `having`
        - `select product, sum(price) from shoppingcart group by product having sum(price) > 10;`
        - `having` 是在分组之后进行过滤，`where`是在分组之前进行过滤，
- 查询的执行顺序：
    - `from`     = 表名     
    - `where`    = 条件过滤  
    - `group by` = 分组              
    - `having`   = 分组之后进行过滤          
    - `select`   = 执行完毕之后显示内容          
    - `order by` = 把内容进行排序输出          

### 7.  MYSQL备份和恢复

### 8.  多表设计

- 外键约束
    - `foreign key(<currentFiled>) reference(<foreignFiled>)` , 约束从表，保证数据有效性， 
    - 已经存在的表添加外键约束
        - `alter table coder_project add foreign key(coder_id) references coder(id);`
    - 新建表的时候添加外键约束
        ``` 
        create table coder_project (
            coder_id int,
            project_id int,
            foreign key (coder_id) references coder(id),
            foreign key (project_id) references project(id)
        );
        ```
    - 删除：先删从表，再删主表
    - 添加：

### 9.  数据库设计

- 范式
    - 原子性，不可拆分
    - 完全依赖主键    
    - 不产生传递依赖

### 10. 多表查询

- 避免笛卡尔积 => 内连接
    - 隐式 `select * from a,b where a.id = b.id;`
    - 显示 `select * from a inner join b on a.id = b.id;`        

- 外连接
    - 左外连接  `select * from a left outer join b on a.id = b.id`
    - 右外连接  `select * from a right outer join b on a.id = b.id;`
    - 全外连接： 不去掉重复 => `union all`
    ``` 
    select * from a left outer join b  on a.id = b.id
    union
    select * from a right outer join b on a.id = b.id;
    ```

### 11. 关联子查询

- `in`

``` mysql
    select * from student where id in (
        select student_id from studentcourse where score <= 60);
    
    
    select project from project where id in (
        select project_id from coder_project where coder_id in (
            select id from coder where coder = 'jack'));
```

- `exists`

```mysql
    // 主表查询不及格的学生
    // student 和 temp 的所有字段
    select * from 
        student, (select * from studentcourse where score < 70) as temp 
    where student.id = temp.student_id order by temp.score;
    
    // 只有 student 的字段，  ↓ :意注 
    select * from student where exists (
        select * from studentcourse where score < 70 and studentcourse.student_id = student.id
```

- `all`

- `any`  `some`

- `as`

- `limit`
    `select * from stu_info order by stu_id limit 0, 300;`// 前300条数据排序

- `as` 临时表

```mysql
    select student.name '姓名', course.name '课程', temp.score '分数' 
    from student, course,
    (select * from studentcourse where score between 60 and 70) as temp
    where temp.student_id = student.id and temp.course_id = course.id
    order by temp.score;
```

### 12. MYSQL自带函数

- 获取当前系统时间 `select now()`

- alter table <> drop foreign key (field) 


# 0304 JDBC

- **java database connection**
- JDBC有关的类和接口：``java.sql`` , ``javax.sql`` (扩展包)  

## 1. 快速入门

```java
public class Ajdbc {
    @Test
    public void main (){
        // 1.  注册数据库驱动
        // 2.  获取数据库连接
        // 3.  创建发送SQL对象
        // 4.  执行SQL语句，获取结果
        // 5.  遍历结果集
        // 6.  关闭资源
        String url = "jdbc:mysql://localhost:3306/fourth";
        String user = "root";
        String pwd = "123456";
        String sql = "select * from user";

        DriverManager.registerDriver(new Driver());
        Connection conn = DriverManager.getConnection(url, user, pwd);
        Statement stt = conn.createStatement();
        ResultSet result = stt.executeQuery(sql);

        while (result.next()) {
            int id = result.getInt("id");
            String _user = result.getString("username");
            String _pwd = result.getString("password");

            System.out.println(id + " -> " + _user + " -> " + _pwd);
        }

        result.close();
        stt.close();
        conn.close();
    }// main
}

```

## 2. API详解

```
DriverManager → Connection → Statement → ResrltSet
                                 ↑ PreparedStatement ← CallableStatement
```

- `DriverManager`   驱动管理类
- `Connection`      数据库连接接口
- `Statement`       发送SQL语句
- `Result`          获取结果
- `PrearedStatement`    发送预编译的SQL语句到数据库
- `CallableStatement`   操作存储过程

## 3. 工具类抽取
```java
public class _jdbc {
    private static String  url  = null;
    private static String  user = null;
    private static String  pwd  = null;

    static {
        try {
            // classLoader 相对 src 目录
            Properties p = new Properties();
            InputStream is = _jdbc.class.getClassLoader().getResourceAsStream("user.properties");
            p.load(is);
            url = p.getProperty("url");
            user = p.getProperty("username");
            pwd = p.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection () {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void release
    (ResultSet rs, Statement stt, Connection conn) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stt != null) stt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}// end
```

## 4. _jdbc CRUD操作
```java
public class CjdbcUtils {
    @Test
    public void query () {
        Connection conn = null;
        Statement stt = null;
        ResultSet rs = null;
        try {
            conn = _jdbc.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select * from user where id = 5");
            if (rs.next()) {
                System.out.println(rs.getString("password"));
            } else {
                System.out.println("query  -> error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _jdbc.release(rs, stt, conn);
        }

    }
    
    @Test
    public void update () {
        String sql = "update user set password = '522933' where username = 'lucy'";
        Connection conn = null;
        Statement stt = null;
        try {
            conn = _jdbc.getConnection();
            stt = conn.createStatement();
            int i = stt.executeUpdate(sql);
            System.out.println("i => " + i);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _jdbc.release(null, stt, conn);
        }
    }

    @Test
    public void insert () {
        String sql = "insert into user values (null, 'luo', 'gege')";
        Connection conn = null;
        Statement stt = null;
        try {
            conn = _jdbc.getConnection();
            stt = conn.createStatement();
            int i = stt.executeUpdate(sql);
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _jdbc.release(null, stt, conn);
        }
    }

    @Test
    public void delete () {
        String sql = "delete from user where username = 'wangwu'";
        Connection conn = null;
        Statement stt = null;

        try {
            conn = _jdbc.getConnection();
            stt = conn.createStatement();
            int i = stt.executeUpdate(sql);
            System.out.println("i -> " + i );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _jdbc.release(null, stt, conn );
        }
    }
}// end
```

## 5.SQL注入

- `String user = "lucy' -- ";`  `--`  注释的方式不需要密码
- `String pwd = "xxx' or '1' = '1";`  `or`  能全部查出来

```java
public class Dinject {
    @Test
    public void or () {
        String user = "lucy";
        String pwd = "999";
        String sql = "select * from user where username = ? and password = ?";
        //String user = "luo' -- ";// -- 注释的方式不需要密码
        //String pwd = "xxx' or '1' = '1";// or 全部查出来
        //String sql = "select * from user where username = '"+ user +"' and password = '"+ pwd +"'";// replace

        Connection conn = null;
        //Statement stt = null;// replace
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = _jdbc.getConnection();
            //stt = conn.createStatement();// replace
            //rs = stt.executeQuery(sql);// replace

            pst = conn.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pwd);
            rs = pst.executeQuery();

            while (rs.next()) {
                String _user = rs.getString("username");
                String _pwd = rs.getString("password");
                System.out.println(_user +" -> "+ _pwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _jdbc.release(rs, pst, conn);
        }
        
    }
    
    @Test
    public void update_insert () {
        String update = "update user set password = ? where username = 'lucy'";
        String insert = "insert into user values (null, ?, ?, null, null);";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = _jdbc.getConnection();

            // update
            pst = conn.prepareStatement(update);
            pst.setString(1, "lucy777");
            int i = pst.executeUpdate();

            // insert
            pst = conn.prepareStatement(insert);
            pst.setString(1, "omg");
            pst.setString(2, "omg777");
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            _jdbc.release(rs, pst, conn);
        }
    }
    
    @Test
    public void delete () {
        String delete = "delete from user where username = ?";
        Connection conn         = null;
        PreparedStatement pst   = null;
        ResultSet rs            = null;
        try {
            conn = _jdbc.getConnection();
            pst = conn.prepareStatement(delete);
            pst.setString(1, "omg");
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _jdbc.release(rs, pst, conn);
        }
    }
}
```

# 0305

## 1. 事务

- 概念：事务指的是逻辑上的一组操作，组成这组操作的各个单元，要么全部成功，要么全部失败。
- 作用：保证在一个事务中多次操作数据库中的数据时，要么全部成功，要么全都失败。

- `start transaction` ： 开启事务。 
- `commit` ：            提交事务。
- `rollback` ：          回滚事务。
- `show variables like '%commit%'` ：查看`autocommit`的模式，默认是 **on**, 打开状态。
- `set autocommit = 0` ：关闭自动提交

### 事务特性

- `Atomicity`   原子性：
- `Consistensy` 一致性：
    - 一个事务在执行之前和执行之后，数据库都必须是一致性的。提交成功，数据库变化生效，提交失败，数据库回滚到原始状态。
- `Durabitity`  持久性：
    - 一个事务提交之后，对数据库的改变时永久性的，就算数据库出现异常，重启之后，数据依然存在。
- `Isolation`   隔离性：
    - 多个用户并发访问数据库的时候，一个用户的事务不能被其他用户的事务干扰。
    
### 事务隔离性
> 如果不考虑事务隔离性会出现以下问题

- 脏读
    - 一个事务读取了另一个事务**未提交**的数据。

- 不可重复读
    - 一个事务读取到另一个事务**已提交**的数据。(不考虑时效性)

- 虚读(幻读)
    - 一个事务多次读取了另一个事务已提交的数据的**数量**，强调的是`insert` , `delete`。

### jdbc操作事务

- `public interface Connection extends Wrapper`
    - `void setAutoCommit(boolean autoCommit)`
    
```java
public class Transaction {
    @Test
    public void main () {
        String query = "select ? from user where name = ?";
        String update = "update user set pwd = ? where username = ?";
        Connection conn         = null;
        PreparedStatement pst   = null;
        ResultSet rs            = null;

        try {
            conn = _jdbc.getConnection();
            conn.setAutoCommit(false);// 开启事务
            pst = conn.prepareStatement(update);
            pst.setString(1, "789789");
            pst.setString(2, "jerry");
            pst.executeUpdate();
            conn.commit();// 提交
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();// 如果出异常就回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            // 不出异常就提交
            _jdbc.release(rs, pst, conn);
        }
    }
}
```

## 2. 数据库连接池
- 优化获取连接，主要是从性能上优化
- `public interface DataSource extends CommonDataSource, Wrapper` : 厂商实现这个规定的接口
    - `Connection getConnection()`

### 自定义连接池
- 适配器模式：定义父类实现接口，在用子类实现部分接口。`23种模式`

```java
public class B_adaptor implements DataSource {
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
    
    // ... 其他的重写方法
}   

// ==
public class C_myComboPooled extends B_adaptor {
    private LinkedList<Connection> list = new LinkedList<>();
    
    public C_myComboPooled () {// 构造函数
        for (int i = 0; i < 5; i++) {
            list.addLast(_jdbc.getConnection());
        }
    }
    
    @Override
    public Connection getConnection() throws SQLException {
        return list.getFirst();
    }
    
    public void backConnection (Connection c) {
        list.addLast(c);
    }
}

// ==
public class D_comboPooled_test {
    @Test
    public void main() {
        C_myComboPooled pool = new C_myComboPooled();
        String sql = "select * from students";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = pool.getConnection();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") +" = "+
                    rs.getString("name") +" = "+
                    rs.getString("score")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _jdbc.release(rs, pst, conn);// 释放资源
            pool.backConnection(conn);// 还给连接池，连接池是通过动态代理完成的，
        }
    }
}
```

### 常用连接池
#### C3P0
- 基本用法：

```javascript
    ComboPooledDataSource cpds = new ComboPooledDataSource();
    cpds.setDriverClass("com.mysql.jdbc.Driver");
    cpds.setJdbcUrl("jdbc:mysql://localhost:3306/fourth");
    cpds.setUser("root");
    cpds.setPassword("123456");
    
    Connection conn =null;
    PreparedStatement pst =null;
    ResultSet rs =null;
```

- 默认文件名：`c3p0-config.xml`， 在`src`目录下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<c3p0-config>
    <default-config>
        <property name="driverClass">com.mysql.jdbc.Driver</property>
        <property name="jdbcUrl">jdbc:mysql://localhost:3306/fourth</property>
        <property name="user">root</property>
        <property name="password">123456</property>
    </default-config>

    <named-config name="third">
        <property name="driverClass">com.mysql.jdbc.Driver</property>
        <property name="jdbcUrl">jdbc:mysql://localhost:3306/third</property>
        <property name="user">root</property>
        <property name="password">123456</property>
    </named-config>
</c3p0-config>
```
- 配置文件的使用

```javascript
ComboPooledDataSource cpbs = new ComboPooledDataSource("third");// 数据库名字
Connection conn =null;
PreparedStatement pst =null;
ResultSet rs =null;
```

#### DRUID
- 配置文件的参数，通常放在`src`目录下
    - `url` : `"jdbc:mysql://localhost:3306/third"`
    - `username` : `"root"`
    - `password` : `"123456"`
    - `driverClassName` : `"com.mysql.jdbc.Driver"`, 驱动类名。根据 `url`自动识别，可以不配置，
    - `initialSize` : 建立的物理连接个数
    - `maxActive` : 连接池中最大连接数
    - `maxWait` : 获取连接时最长等待时间
    
- 1.导包

- 2.加载`properties` 配置文件到 `Properties` 对象中，通过类加载器加载文件，因为文件在`src` 目录下
    - `所在类.class.getClassLoader().getResourceAsStream("druid.properties");`
    
- 3.创建`druid` 连接池，使用配置文件的参数    
    - `DataSource ds = DruidDataSourceFactory.createDataSource(p);`
    
- 4.从连接池取出连接，相当于创建连接
    - `Connection conn = ds.getConnection();`
    
- **案例**
```javascript
    Properties p = new Properties();
    InputStream file = E_druid.class.getClassLoader().getResourceAsStream("druid.properties");
    p.load(file);
    DataSource ds = DruidDataSourceFactory.createDataSource(p);
    // String url = p.getProperty("url");
    // String user = p.getProperty("username");
    // String pwd = p.getProperty("password");
    // Connection conn = DriverManager.getConnection(url, user, pwd);
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    conn = ds.getConnection();
    pst = conn.prepareStatement("select * from students");
    rs = pst.executeQuery();

    while (rs.next()) {
        System.out.println(
            rs.getInt("id") +" - "+
            rs.getString("name")
        );
    }

    _jdbc.release(rs, pst, conn);
```
#### DBCP

### JDBCTemplate
- `JDBCTemplate` 是 `Spring` 对 `JDBC`的封装，目的是使`JDBC` 更加易于使用，处理了资源的建立和释放。
    - `commons-logging-1.2.jar` ：日志记录
    - `spring-beans-5.0.0.RELEASE.jar` ：JavaBean管理包
    - `spring-core-5.0.0.RELEASE.jar` ：核心包
    - `spring-jdbc-5.0.0.RELEASE.jar` ：jdbc支持包
    - `spring-tx-5.0.0.RELEASE.jar` ：事务支持包
- 使用方法
    - `JdbcTemplate jt = new JdbcTemplate(_jdbc.getDruidComboPool)`
- 常用方法
    - `execute(String sql)` : 建表
        - `jt.execute("create table user(id int primay key auto_increment, name varchar(20) not null)")`
    - `update(String sql)` : 增删改
        - `jt.update("insert into user values (null, 'cat', '999', null, null)")`
        - `jt.update("update user set pwd = '000' where username = 'cat'")`
        - `jt.update("delete from user where username = 'cat'")`
    - `queryXxx(String sql)` : 查询
        - `public <T> T queryForObject (String sql, Class<T> requiredType, Object... args)`
        - `public Map<String, Object> queryForMap (String sql, Object... args)`
        - `public List<Map<String, Object>> queryForList(String sql, Object... args)`
        - `public <T> List<T> query(String sql, RowMapper<T> rowMapper)`
        
```java 
    public class H_query {
        private JdbcTemplate jt = new JdbcTemplate(_jdbc.getComboPooled("DRUID"));
    
        @Test// 单个字段
        public void queryForObject_onlyFile () {
            String pwd = jt.queryForObject("select password from user where id = ?", String.class, 1);
            // 返回的是 String
            System.out.println("pwd = " + pwd);
        }
        
        @Test// 聚合函数
        public void queryForObject_function () {
            Integer count = jt.queryForObject("select count(*) from user", Integer.class);
            // 返回的是 Integer
            System.out.println("count = " + count);
        }
        
        @Test // 单行返回实例// 返回的是 instance
        public void queryForObject_object () {
            User u = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getEmail(), user.getPassword());
        }
    
        @Test// 单行返回对象
        public void row () {
            Map<String, Object> map = jt.queryForMap("select * from user where id = ?", 1);
            Set<String> keys = map.keySet();
            for (String key : keys) {
                System.out.println(key + map.get(key));
            }
        }
    
        @Test// 单列
        public void column () {
            List<String> list = jt.queryForList("select password from user", String.class);
            System.out.println("list = " + list);
        }
    
        @Test// 所有
        public void all () {
            List<User> users = jt.query("select * from user", new BeanPropertyRowMapper<>(User.class));
            for (User user : users) {
                System.out.println("user = " + user);// user = javabean
            }
        }
        
    }
```

满目山河空念远

this inspection reports all fields, methods or classes, 
found in the specified inspection scope, 
that may have their access modifier narrowed down




# 0501

### 1. Tomcat

    
### 2. Web

    
### 3. Servlet
> Servlet是运行在服务端的小程序，是SUN公司提供的一套规范，用来处理客户端请求、响应动态web资源给浏览器

```java
public interface Servlet {
    void init(ServletConfig var1) throws ServletException;

    ServletConfig getServletConfig();

    void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;

    String getServletInfo();

    void destroy();
}
``` 

- Servlet入门开发
    - a.创建java类实现Servlet接口
    - b.注册这个类
        - 在`web.xml`配置这个类的信息
        - 配置这个类的映射信息
        ``` xml
            <servlet>
                <servlet-name>Test_servlet</servlet-name>
                <servlet-class>cn.item.num01_servlet.Test_servlet</servlet-class>
            </servlet>
            <servlet-mapping>
                <servlet-name>Test_servlet</servlet-name>
                <url-pattern>/testServlet</url-pattern>
            </servlet-mapping>
        ```
    - c.在`service`方法中处理请求和响应
    
    - d.发布、启动，访问路径
 
 
- Servlet优化 `GenericServlet` 
    - GenericServlet 是一个抽象类， 重写了 Servlet的所有方法
    - `public abstract class GenericServlet implements Servlet, ServletConfig, Serializable { }`
    - `destroy ():void`  
    - `genInitParanmeter (String):String`
    - `getInitParameterNames ():Emumeration<String>`
    - `getServletConfig ():ServletConfig`
    - `getServletContext ():ServletContext`
    - `getServletInfo ():String`
    - `init (ServletConfig):void`
    - `log (String):void`
    - `log (String, Throwable):void`
    - `service (ServletRequest, ServletResponse):void`
    - `getServletName ():String`
 
- Servlet优化 `HttpServlet`
    - `public abstract class HttpServlet extends GenericServlet { }`
    - `doDelete (HttpServletRequest, HttpServletResponse):void`
    - `doGet ():void`
    - `doHead ():void`
    - `doOptions ():void`
    - `doPost ():void`
    - `doPut ():void`
    - `doTrace ():void`
    - `getLastModified (HttpServletRequest):long`
    - `HttpServlet()`
    - `service (HttpServletRequest, HttpServletResponse):void`
    - `service (ServletRequese, ServletResponse):void`
    
- Servlet生命周期
    - 创建：第一次访问 Servlet 的时候创建，只创建一次，创建时执行`init()`
    - 执行：每次访问 Servlet 都执行 `service()`
    - 销毁：服务器正常关闭，销毁前执行 `destory()`

- 服务器启动，立即加载 Servlet对象
    - `<load-on-startup>2</load-on-startup>`
        - 传入正整数，整数越小，被创建的优先级越高
    
- Servlet映射路径配置规范
    - 1.完全匹配 `/user/hello` 路径为/user/hello时可以访问
    - 2.目录匹配 `/user/*` 路径中含有/user都可以访问
    - 3.后缀名匹配 `*.do` 路径中以.do结尾的都可以访问
    - 4.缺省路径 `/` 访问路径找不到就去找缺省路径
    - `tomcat`获得匹配路径时的优先级，1 > 2 > 3 > 4
    

### 4.注解开发
- **Servlet3.0新特性**
    - 支持注解：用于简化`Servlet`, `Filter`, `Listener`,  
    - 支持 web模块
    - 支持 Servlet异步处理
    - 文件上传 API简化
 
 # 0502
 ### 1. HTTP协议
 > HTTP：HyperText Transfer Protocol, 超文本传输协议
 <br>
 > HTTP/1.0 获得一个web资源，连接断开，HTTP/1.1 可以获得多个web资源，连接断开
 
- 常见状态码
    - 302 `move temporaily` (暂时的)：重定向，资源临时从不同的URI 响应
    - 304 `not modified`
    - 403 `forbidden` (forbid, permit)
    - 405 `method not allowed`
    - 500 `internal server error`
 
- 请求方式：
    - 1.`OPTIONS`: 
    - 2.`HEAD`:
    - 3.`GET`:
    - 4.`POST`:
    - 5.`PUT`:
    - 6.`DELETE`:
    - 7.`TRACE`:
    - 8.`CONNECT`:
- 请求结构：
    - 请求报文：
        - 请求行，请求头，请求体
    - 响应报文：
        - 响应行，响应头，响应体
- `get`, `post` 区别
    - get 不安全，参数在URL 后面
    - get 数据量小，不能传输非文本数据
    - get 请求体没有内容
    - post 的请求参数以请求体的形式发送到服务器，安全

            
 
 ### 2. HttpServletRequest
 > **HttpServletRequest**对象代表客户端的请求，HTTP请求中的所有信息都封装在这个对象中
 
- 获取请求行信息
    - `String getMethod()`
    - `StringBuffer getRequestURL()`
    - `String getRemoteAddr()`
    - `String getProtocol()`
    
- 获取请求头信息
    - `String getHeader(String name)`: 
    - `Enumeration getHeaderNames()`: 返回所有头信息的枚举
        - `referer`
        - `if-modified-since`
        - `cookie`
        - `user-agent`
        - `connection`
        - `host`
        - `content-length`
        - `content-type`
        - `accept`
        - `mime`
        - `accept-encoding`
        - `accept-language`
        
- 获取请求体信息    
    - `String getParameter(String name)`: 获取指定值，没有返回 null，有多个返回第一个
    - `String[] getParameterValues(name)`: 获取请求数据 Key相同的多个数据
    - `request.getParameterMap()`: 获取所有表单的数据
    
- `request.setCharacterEncoding("utf-8");`     
 
 
 ### 3. request 作用域
- request 生命周期
    - 1.浏览器向 `Servlet`发送请求
    - 2.`Tomcat`收到请求，创建`Request`和`Response`对象，将请求参数封装到`Request`对象中，然后传递给`Servlet`
    - 3.`Servlet`接收到请求，调用`doget`和`dopost`方法。处理请求信息，然后通过`Response`返回消息
    - 4.`Tomcat`接收到消息，再返回给浏览器
    - 5.浏览器接收到返回消息后，`Tomcat`销毁`Request`和`Response`对象，同时销毁这俩对象获取的信息

- request 域对象
    - 一个存储数据的区域对象
    - `void setAttribute(String name, Object obj)`
    - `Object getAttribute(String name)`
    - `void removeAttribute(String name)`
    - ```javascript 
      request.setAttribute("flag", "help me !");
      Object falg = request.getAttribute("falg");// 域中数据共享，所有 Servlet都可以拿到数据
      System.out.println(falg);// help me !
      ```
    
- 请求转发
    - ```
       1.请求转发，域中的数据共享
       2.转发可以去 html静态资源，也可以区域 Servlet
       ```
    - `RequestDispatcher getRequestDispatcher (String path)`
    - `void forward (ServletRequest request, ServletResponse response)`
        - ```javascript 
            request.getRequestDispatcher("/about.html").forward(request, response);
            ```
 
### 4. HttpServletResponse
- 响应行
    - `setStatus (int code)` , 发送状态码, 配合设置响应头完成重定向
        - ```javascript
            response.setStatus(302);
            response.setHeader("location", "login.html")
            response.sendRedirect("login.html");// 简写
        ```
 
- 响应头
    - `content-disposition` , 通知浏览器以附件的形式解析正文
    - `content-type` ，设置响应**数据的类型（MIME类型）** 和 **编码格式**
        - ```javascript
            //response.setHeader("content-type", "text/html;charset=utf-8");
            response.setContentType("text/html;charset=utf-8");// 简写
            ```    
    - `location` , 指定响应的路径，需要配合 `setStatus()`使用，完成重定向
        - ```javascript
            //response.setStatus(302);
            //response.setHeader("location", "/error.html");
            //response.setHeader("location", "contentType");
            response.sendRedirect("/success.html");// 简写
            ```
    - `refresh` , 定时刷新
        - ```javascript
        response.setHeader("refresh", "3;url=https://www.jd.com")
        ```
- 乱码
    - 服务器响应 **中文** 给浏览器，而`tomcat`默认编码是`ISO-8859-1`，该码表不支持中文编码
    - ```javascript
    response.setHeader("content-type", "text/html;charset=utf-8");// 设置响应头
    response.setContentType("text/html;charset=utf-8");// 简写方式
    ```    
    
- 重定向 
    - 重定向和转发的区别
    - 重定向和转发的应用场景

- 响应体
    - `ServletOutputStream getOutputStream ()` : 向浏览器输出字节数据
    - `java.io.PrintWriter getWriter ()` : 向浏览器输出字符数据
    
- `String encode = URLEncoder.encode(fileName, "utf-8");`    

### 5. ServletContext
> 1. 存储网站的访问量
<br>
> 2. 存储整个项目的配置信息，相当于项目对象，可以获取项目资源的真实路径和类型

- **API**
    - `getAttribute (String name)`
    - `setAttribute (String name, Object obj)`
    - `removeAttribute (String name)`
    - `String getRealPath (String path)`
    - `getServletContext().getMimeType( fileName)`
 
 
 ```javascript
ServletContext context = request.getServletContext();
String fileName = request.getParameter("filename");
String path = context.getRealPath("/WEB-INF/"+ fileName);
String type = context.getMimeType(fileName)
File filePath = new File(path);

response.setContentType(type);
response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
```
 
 
# 0504
> cookie 和 session 都是在会话期间产生，作用都是保存数据

### cookie
> cookie 是浏览器存储数据的一种方式，4K * 300,
> 服务器创建 cookie并且响应给浏览器， 浏览器自动保存，以后每次请求都会携带cookie

- `cookie` 使用
    - 获取name值：`String getName ()` , `c.getName()`
    - 获取value值：`String getValue ()` , `c.getValue()` 
    - 获取所有：`Cookie[] getCookies ()`, `request.getCookies()`
    - 创建：`Cookie (String name, String value)` , `Cookie c = new Cookie("name", "lucy")` 
    - 设置值：`void setValue (String value)` , `c.setValue("jim")`
    - 发送：`void addCookie(Cookie cookie)` , `response.addCookie(c)`
- cookie特殊字符的转义
    - ```javascript
        Cookie name = new Cookie("username", URLEncoder.encode(request.getParameter("username")));
        Cookie pwd = new Cookie("password", URLEncoder.encode(request.getParameter("password")));
        response.addCookie(name);
        response.addCookie(pwd);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            System.out.println(URLDecoder.decode(cookie.getValue()));
        }
    ```
    
- `cookie` 存活时间
    - `void setMaxAge (int seconds)` , `c.setMaxAge(60 * 60);`
        - 如果没有设置时间，cookie 只会保存在浏览器的缓存中，浏览器关闭之后 cookie会被删除，
          设置时间以后， cookie被写入到浏览器端，直到过期

- `cookie` 有效路径
    - `void setPath (String path)` 
        - `c.setPath("/")` ,表示当前 Tomcat下的所有 web项目都可以访问这个 cookie
        - `c.setPath("/web")` ,表示 /web路径及其子路径都可以访问这个 cookie

- `cookie` 删除
    - ```javascript
    Cookie cookie = new Cookie("name","");//1. 创建相同名称cookie
    //2. 保证cookie有效路径一致
    cookie.setMaxAge(0);//3. 设置cookie存活时间 0
     response.addCookie(cookie);//4.将cookie响应回到浏览器中，自动置换          
    ```

### session
> 为客户端用户创建一个容器，容器中存储的数据能够在多个`request`之间共享，并且，
这个容器只属于当前用户，**当前用户** 指的是 **当前用户的当前浏览器**, 数据是保存在服务器的内存中

- `session`创建
    - ```javascript
        request.getSession();
        request.getSession(true);// 等同于不传参数，如果存在 session, 获取，不存在，创建
        request.getSession(false);// 如果存在 session，获取，不存在...,不创建
    ```
    
- `session`使用：存取数据
    - ```javascript
        // @WebServlet("/login")
        HttpSession session = request.getSession();
        session.setAttribute("name","lucy");
            
        // @WebServlet("/success")
        String msg = (String) request.getSession().getAttribute("name");
        System.out.println(msg);
    ```
   
- `session`持久化
    - ```javascript
        String id = request.getSession().getId();
        System.out.println(id);
        Cookie cookie = new Cookie("JSESSIONID",id);
        cookie.setMaxAge(60*60*24*30);
        response.addCookie(cookie);
    ```   

- `session`移除 和 销毁
    - ```javascript
        request.getSession().removeSession("user");

        request.getSession().invalidate("user");
    ```

# 0505

### JSP 
> `Java Server Page`, 本质是**简化的Servlet**，一种**动态网页**的技术标准，其实就是一个既能书写**Servlet**代码，又能书写**HTML**代码的文件

- jsp 运行原理
    - `Using CATALINA_BASE` : `C:\Users\luogeger\.IntelliJIdea2017.3\system\tomcat\Tomcat_8_5_282_item_0505`
    - `.jsp` => `.java` => `.class` , `.java`文件里的 `_jspService`方法有两个参数 `request` , `response`, 由此`.jsp`文件本质上就是一个`Servlet`

- jsp 书写 java代码
    - 脚本片段: 不能定义变量和方法（方法内部不能定义方法）
    - 脚本声明: 成员变量和成员方法
    - 脚本表达式: 
    - **注意：** 不同的脚本表达式，会出现书序不一致
    - `write()` 和 `print()` 的不同点
    
- jsp 常用对象
    - `HttpServletRequeset` , `HttpServletResponse`
    - ```javascript
    <% String msg = request.getAttribute("msg"); %>
    <span> <%= msg %> </span>
    <span> ${msg} </span>
    ```

### EL
- 取值
    - `pageScope`
        - 指的是当前的jsp页面，存储的数据只在当前jsp有效
        - ```bash
        pageContext.setAttribute("page_key", "page_val");
        ${pageScope.page_key}
        ```
    - `requestScope`
        - 一次请求或请求链中的 request域
        - ```bash
        request.setAttribute("request_key", "request_val");
        ${requestScope.request_key}
        ```
    - `sessionScope`
        - 一次会话过程中，session域
        - ```bash
        request.getSession().setAttribute("session_key", "session_val");
        ${sessionScope.session_key}
        ```
    - `applicationScope`
        - 服务器启动后，整个项目的ServletContext域
        - ```bash
        application.setAttribute("app_key", "app_val");
        ${applicationScope.app_key}
        ```

- 运算


### JSTL     
> **The JavaServer Pages Standard Tag Library**

- `倒包引入`
    - `javax.servlet.jsp.jstl.jar`
    - `jstl-impl.jar`
    - `<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`
    
- `常用标签`    
    - `c:out` ：输出文本内容
    - `c:set` ：设置各种Wed域中的属性 
    - `c:remove` ：删除各种Web域中的属性
    - `c:catch` 
    - `c:if`
    - `c:choose`
        - `c:when`
        - `c:otherWise`
    - `c:forEach`
    - `c:forTokens` ：迭代操作String字符串
    - `c:param` ：给请求路径添加参数
    - `c:url` ：重写URL
    - `c:import`：jsp页面找那个导入一个URL地址指向的资源
    - `c:redirect` ：将当前请求转发或重定向

- `c:if`
    - ```bash
    <c:if test="${user != null}">
        <c:out value="登陆成功"></c:out>
    </c:if>
    ```

- `c:forEach`    
    - ```bash
    <c:forEach items="${list}" var="item">
        <span>&{item.name}</span>
        <span>&{item.age}</span>
    </c:forEach>
    ```
    
- `c:choose`    
    - ```bash
    <c:choose>
        <c:when test="${result == 1}">
            <c:out value="一号选手"></c:out>
        </c:when>
    </c:choose>
    ```



# 0507

### 1. Filter
> 过滤器本质是Java中预先定义好了的不同的接口，和Servlet类似。具体怎么过滤，需要定义一个实现类，实现接口中的过滤方法。

- `Filter` 是由`Tomcat`启动时创建， 是`javax.servlet`包下面的一个接口，接口中定义了3个方法

    ```java 
        package javax.servlet;
        import java.io.IOException;
        
        public interface Filter {
            void init(FilterConfig var1) throws ServletException;
            void doFilter(ServletRequest var1, ServletResponse var2, FilterChain var3) throws IOException, ServletException;
            void destroy();
        }
    ```


### 2. 执行流程

### 3. 映射路径




# 0509 Redis

- 概述
- 特点
- 应用
    - `redis-benchmark` : 性能测试工具
    - `redis-check-aof` : AOF文件修复工具
    - `redis-check-dump` : RDB文件检查工具（快照持久化文件）
    - `redis-cli` : 命令行客户端
    - `redis-server` : redis服务器启动命令
    - `redis.windows.conf` : redis核心配置文件
        - `maxmemory`
    - `redis-install.bat` : 启动脚本
        - `redis-server --service-install redis.windows.conf --loglevel verbose` 
        - `redis-install.bat`
    - `uninstall-redis.bat` : 关闭脚本
        - `redis-server --service-stop`
        - `redis-server --service-uninstall`

- 使用
    - 1.`redis-server.exe`
    - 2.`redis-cli.exe`
    - 3.`ping`

- 测试性能命令：`redis-benchmark.exe -c 50 -n 10000 -t get`
    - `-c` 表示连接数
    - `-n` 表示请求数
    - 50个并发连接，10000个请求
    
    
### 常用命令
- `select 0` : 选择数据库，`0`表示索引，默认有16个
- `flushdb` : 删除当前数据所有数据，不影响其他数据库
- `flushall` : 删除所有数据库数据



### 数据类型

- `String`
    - `set name 'lucy'` : 重复设置就是修改
    - `get name` 
    - `del name`
    - `mset`
    - `mget`
    
- `hash`
    - `hget`
    - `hset` 
    - `del`
    - `hmset`
    - `hmget`
    - `hgetall`
    - `hkeys`
    - `hvals`

- `list` : 底层是 LinkedList
    - `lpush`
    - `rpush`
    - `lpop`
    - `rpop`
    - `lrange key start end`
    - `lrange key 0 -1`
    - `index key 2`

- `set`
    - `sadd`
    - `smembers key`
    - `srem`
    - `scard`

- `sorted set`  

### 通用命令

- keys pattern
- del key1 key2...
- exists key
- type key

### Jedis
> 通过Jedis用Java代码连接Redis



# 0510
### maven

- `bin` : 该目录包含了mvn运行的脚本，分别为mvn、mvn.bat、mvnDebug、mvnDebug.bat和m2.conf，这些脚本用来配置 Java 命令，
    准备 CLASSPATH 和相关的 Java 系统属性，然后执行 Java 命令。其中，mvn是基于 UNIX 平台的shell脚本，
    mvn.bat是基于 Windows 平台的bat脚本；同理，mvnDebug是基于 UNIX 平台的shell脚本，
    mvnDebug.bat是基于 Windows 平台的bat脚本。在命令行输入任何一条mvn命令时，实际上就是调用这些脚本。
    而mvn和mvnDebug的区别就在于后者比前者多了一条MAVEN_DEBUG_OPTS配置，
    其作用就是在运行 Maven 时开启debug，以便调试 Maven 本身。此外，m2.conf是classworlds的配置文件。
- `boot` : 该目录只包含一个文件，以maven 3.0为例，该文件为plexus-classworlds-2.2.3.jar。plexus-classworlds是一个类加载器框架，
    相对于默认的 Java 类加载器，它提供了更丰富的语法以方便配置，Maven 使用该框架加载自己的类库。
- `conf` : 该目录包含了要给非常重要的文件settings.xml。直接修改该文件，就能再机器上全局地定制 Maven 的行为。
    一般情况下，我们更偏向于复制该文件至~/.m2/目录下（~表示用户目录），然后修改该文件，在用户范围定制 Maven 的行为。
- `lib` : 该目录包含了所有 Maven 运行时需要的 Java 类库，Maven 本身是分模块开发的，
    因此用户能看到诸如maven-core-3.0.jar和maven-model-3.0.jar之类的文件。此外，
    这里还包含一些 Maven 用到的第三方依赖，如common-cli-1.2.jar和google-collection-1.0.jar等。
    对于 Maven 2 来说，该目录只包含一个如maven-2.2.1-uber.jar的文件，原本各为独立的 JAR 文件
    的 Maven 模块和第三方类库都被拆解后重新合并到了这个 JAR 文件中。可以说，lib目录就是真正的 Maven。
    还有一点值得一提的是，用户可以在这个目录中找到 Maven 内置的超级 POM。
- `LICENSE.txt` : 该文件记录了 Maven 使用的软件许可证 Apache License Version 2.0。
- `NOTICE.txt` : 该文件记录了 Maven 包含的第三方软件。
- `README.txt` : 该文件则包含了 Maven 的简要介绍，包括安装需求以及如何安装的简要指令等。
    
    
    
- 安装maven
    - `repository` : 本地仓库
        - `settings.xml` : `<localRepository>G:\apache-maven-3.2.3\repository</localRepository>`
    - `mirrors` : 私服
        - `settings.xml` : 
- 搭建maven工程
    - idea配置maven参数
        - `Maven hone directory` : `G:\apache-maven-3.2.3`
        - `User settings file` : `G:\apache-maven-3.2.3\conf\settings.xml`
        - `Runner` ==> `VM Options` : `-DarchetypeCatalog=internal`
    - 创建web工程
        - `packaging`
        - `Facets`
            - `Web Resource`
            - `Deployment`
    - 发布web项目            
        - `Tomcat`            
        
- **pom** Project Object Model
    - 描述项目
    - 管理依赖
    - 管理插件
            


            