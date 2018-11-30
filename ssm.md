# 0701 Mybatis
- `font-family: 'Source Code Pro','DejaVu Sans Mono','Ubuntu Mono','Anonymous Pro','Droid Sans Mono',Menlo,Monaco,Consolas,Inconsolata,Courier,monospace,"PingFang SC","Microsoft YaHei",sans-serif;`

```
    │─user
    │     
    │─dev_env      
    │   ├─jdk
    │   ├─maven
    │   ├─tomcat
    │   ├─mysql
    │   ├─redis
    │   ├─nginx
    │ 
    │─dev_tools
    │   ├─IDEA
    │   ├─VMware
    │   ├─liunx
    │   ├─iso
    │   ├─navicat
    │   ├─vscode
        
        
```

- 1.引入依赖
- 2.全局配置文件
- 3.映射文件

- 概述
- 解决哪些问题
- 怎么使用



# 0704 Spring
#### 概述


#### 搭建工程
- `pom.xml` 依赖管理
- `applicationContext.xml` 
- `log4j.properties`


#### 控制反转 
> IoC **Inverse of Control**：把实例化的权利交给第三方工厂
<br>
> 具体实现：Spring工厂 + 反射 + 配置文件

- 代码的演变
    - 传统方式：三层架构，Dao层和Service层高度耦合，
        - `Dao dao = new Dao_c();`
    - 自定义工厂：还是需要创建实体类
        - `return new Dao_c();`
    - 反射：返回Object对象，还是必须要实体类的路径,(硬编码)
        - `Class.forName("cn.item.spring_01.Dao_c").newInstance();` 


- Spring工厂的使用
    - `applicationContext.xml`
    ```xml
        <bean id="Dao_c" class="cn.item.spring01.Dao_c"></bean>
    ```

    - `Service_c.java`
    ```java
        // 1.读取配置文件创建工厂对象
        // 2.获取Bean对象，得到的是Object，向下转型，参数是Bean的id
        // 3.调用方法
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Dao dao = (Dao) ac.getBean("Dao_c");// bean的id
        dao.findUser();
    ```

    - `Test.java`
    ```java
        Service service = new Service_c();
        service.login();
    ```

#### 依赖注入
```bash
    现在Service的功能需要依赖Dao, 对于Service来说要创建工厂获取Dao，是一种主动的行为，
    有没有一种方法在执行Service方法的时候，自动的解决自身需要的依赖

    DI：dependency injection

    DI的做法是由Spring容器在创建Sercvice、Dao对象，并且在配置中将Dao传入到Service，那么Service对象就包含了Dao对象的引用

    在Spring容器创建管理多个对象，通过property标签将对象注入到需要依赖的对象中
```

- 代码改造
    - `applicationContext.xml`
    ```xml
        <bean id="Dao_c" class="cn.item.spring01.Dao_c"></bean>

        <bean id="service_c" class="cn.item.spring01.Service_c">
            <property name="dao" ref="Dao_c"></property>
            <!-- name: set方法的后缀， ref: 代表复杂数据，是依赖的id -->
        </bean>
    ```

    - `Service_c.java`
    ```java
        // 以下两行代码不需要
        //ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml")
        //Dao dao = (Dao) ac.getBean("Dao_c");

        // 因为Dao注入到Service里，所以内部需要一个私有的成员变量来接收
        private Dao dao;

        public void setDao(Dao dao) {
            this.dao = dao;
        }

        dao.findUser();
    ```

    - `Test.java`
    ```java
        //Service service = new Service_c();
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Service service = (Service) ac.getBean("service_c");// bean的id
        service.login();
    ```


#### Spring工厂的了解

#### IoC装配Bean => xml
**xml装配的4种方式**
- 无参构造方法：默认调用的是无参构造方法
- 静态工厂方法：在实例化之前可以有一些其他的操作，conn...
- 实例工厂方法：先有实例才能获取
- FactoryBean：基于某一种类型创建对象的接口
    - class路径是Bean4Factory，在实例化`Bean4Factory`的时候，会判断时候实现了`FactoryBean`接口，如果实现了就自动调用`getObject`方法，并返回结果


- `Bean.java`
    - `Bean1.java`
    ```java
    public class Bean1 {
        public Bean1 (String str){// 默认调用无参，但是有参就会报错
            System.out.print(str)
        }
    }
    ```
    - `Bean2.java`
    ```java
    public class Bean2 {

        public static Bean2 getBean2 () {
            // connection .,...
            return new Bean2();
        }
    }
    ```
    - `Bean3.java`
    ```java
    public class Bean3 {
    }
    ```
    - `Bean3Factory.java`
    ```java
    public class Bean3Factory {
        public Bean3 getBean3 (){
            return new Bean3();
        }
    }
    ```
    - `Bean4.java`
    ```java
    public class Bean4 {
    }
    ```
    - `Bean4Factory.java`
    ```java
    public class Bean4Factory implements FactoryBean<Bean4> {
        @Override
        public Bean4 getObject() throws Exception {
            return new Bean4();
        }

        @Override
        public Class<?> getObjectType() {
            return null;
        }

        @Override
        public boolean isSingleton() {
            return false;
        }
    }
    ```
    
    

- `applicationContext.xml`
```xml
    <bean class="cn.item.get_bean.Bean1" id="bean1"/>

    <bean class="cn.item.get_bean.Bean2" id="bean2" factory-method="getBean2"/>

    <bean class="cn.item.get_bean.Bean3Factory" id="factory3"/>
    <bean id="bean3" factory-bean="factory3" factory-method="getBean3"/>

    <bean class="cn.item.get_bean.Bean4Factory" id="bean4"/>
```

- `Test.java`
```java
public void getBean () {
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

    // 无参构造方法
    Bean1 bean1 = (Bean1) ac.getBean("bean1");
    System.out.println("bean1 = " + bean1);

    // 静态工厂方法
    Bean2 bean2 = (Bean2) ac.getBean("bean2");
    System.out.println("bean2 = " + bean2);

    // 静态工厂方法
    Bean3 bean3 = (Bean3) ac.getBean("bean3");
    System.out.println("bean3 = " + bean3);

    // 静态工厂方法
    Bean4 bean4 = (Bean4) ac.getBean("bean4");
    System.out.println("bean4 = " + bean4);
}
```

##### Bean的作用域
##### Bean的生命周期
##### Bean属性的依赖注入

> Bean属性 == 类的成员变量

- 构造参数注入
- setter注入
- p名称空间
- spEL表达式


- `Car.java`

```java
    public class Car {
        private Integer id;
        private  String name;
        private Double price;

        public Car(Integer id, String name, Double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public Car( String name, Double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        // @Override toString...
    }

````
- `Person.java`

```java
    public class Person {
        private  Integer id;
        private String name;
        private Car car;
        // getter setter toString
    }
```
- `applicationContext.xml`

```xml
    <!--方式一: 带参构造器注入-->
    <bean class="com.itheima.spring.e_xmlpropertydi.Car" id="car" >
        <!--name:参数名称定位
            index: 参数索引定位
            type: 参数类型
            value: 基本类型赋值
            ref: 对象类型赋值
            -->
        <constructor-arg index="0" value="1246789" />
        <constructor-arg name="name" value="跑跑卡丁车" />
        <!--<constructor-arg type="java.lang.Double" index="2" value="12469d" />-->
        <constructor-arg type="java.lang.Double" index="2">
            <value>234d</value>
        </constructor-arg>
    </bean>

    <!--方式二:setter方法注入-->
    <bean class="com.itheima.spring.e_xmlpropertydi.Person" id="person">
        <property name="id" value="12345678"/>
        <property name="name" value="柳岩"/>
        <!--<property name="car"  ref="car"/>-->
        <property name="car" >
            <ref bean="car" />
        </property>
    </bean>

    <!--p名称空间的使用-->
    <bean class="com.itheima.spring.e_xmlpropertydi.Person" id="person1" p:id="123456" p:name="老王" p:car-ref="car" />

    <!--spEL表达式的使用-->
    <bean class="com.itheima.spring.e_xmlpropertydi.Person" id="person2" p:id="#{person.id}" p:name="#{person.name}" p:car="#{car}" />
```
- `Test.java`

```java
	public void test() {


		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		Car car = (Car) applicationContext.getBean("car");
		System.out.println("car = " + car);


		Person person = (Person) applicationContext.getBean("person2");
		System.out.println("person = " + person);
	}
```

#### IoC装配Bean => annotation
**注解的装配**
- 添加注解
    - @Component
    - @Repository
    - @Service
    - @controller
- 开启注解
    - `<context:annotation-config />`
    - 混合配置的时候使用
- 开启注解扫描
    - `<context:component-scan base-package="cn.item.spring"/>`
    - `"cn.item.spring"`: 也可以只给`cn`，所有子包都扫描
    - 包含了开启注解，单独使用

```bash
    1. 添加注解
    2. 开启注解
    3. ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
```

**注解的注入**

```java
    @Value("rose")
    private String name;

    @value("#{c_dao}")// c_dao == id
    private CustomerDao cDao;


    // @Autowired,单独使用时, 默认是根据对象类型装配， xml的调setter方法，一般只有一个类型的对象，因为都是单列，
    // @Autowired + 形参，Spring工厂创建的时候，扫描到注解对应的方法回去执行，再去找对应的形参再赋值，前提是形参对应的对象也属于Spring
    @Autowired     
    @Qualifier(value = "c_dao")// c_dao == id
    private CustomerDao cDao;

    // JSR-250 java自己提供的，单独使用时也是根据类型注入
    @Resource
    @Resource(name="id")


    // JSR-330  需要额外的导包
    @Inject
    @Name("id")

```



#### xml、annatation混合配置Bean

#### Spring的web集成

#### SpringTest

#### AOP
```bash
AOP Aspect Oriented Programing 面向切面编程

OCP原则：开闭原则，对拓展开放，对修改关闭

传统的方式：纵向继承体系重复性代码的编写，破坏了代码的封装，具有侵入性
    
AOP：横向抽取机制，在不修改原对象代码的情况下，通过代理对象，调用增强功能代码，从而对原有业务方法的增强

具体实现：动态代理

```

**应用场景**
- 记录日志
- 监控方法运行时间(监控性能)
- 权限控制
- 缓存优化
- 事务管理

**相关术语**
- `Aspect`      切面
- `Joinpoint`   连接点
- `Pointcut`    切入点
- `Advice`      通知
- `Target`      目标对象
- `Weaving`     织入
- `Introduction`引介

#### JDK底层实现AOP

- `applicationContext.xml`

```xml
    <bean class="cn.item.jdk.Inter_c" id="inter_c_jdk"/>
```

- `Inter.java`

```java
    public interface Inter {
        void save ();
        int find ();
    }

```

- `Inter_c.jav`

```java
    public class Inter_c implements Inter {
        @Override
        public void save() {
            System.out.println("save ...");
        }

        @Override
        public int find() {
            System.out.println("find ...");
            return 0;
        }
    }

```

- `JdkProxy.java`

```java
    public class JdkProxy implements InvocationHandler{
        private Object target;

        public JdkProxy(Object target) {
            this.target = target;
        }

        public Object getProxyObject() {
            return Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),// 类
                    target.getClass().getInterfaces(),// 接口
                    this);// 过程
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if ("save".equals(method.getName()))
                before();
            return method.invoke(target);
        }

        public void before () {
            System.out.println("before ...");
        }
    }
```

- **test.java**

```java
public void jdk () {
        // target       获取目标对象
        // proxy        获取代理对象工厂
        // proxyObject  获取代理对象
        // invoke       执行方法

        Inter target = new Inter_c();
        JdkProxy proxy = new JdkProxy(target);
        Inter proxyObject = (Inter)proxy.getProxyObject();

        proxyObject.save();
        proxyObject.find();

    }
```

#### Cglib底层实现AOP

- `applicationContext.xml`

```xml
    <bean class="cn.item.Cglib.Target" id="target_c_cglib"/>
```


- `Targer.java`

```java
    public class Target {
        public void save(){
            System.out.println("ProductService保存了");
        }

        public int find(){
            System.out.println("ProductService查询了");
            return 99;
        }
    }
```

- `CglibProxy.java`

```java
    import org.springframework.cglib.proxy.MethodInterceptor;// 注意导包
    public class CglibProxy implements MethodInterceptor {
        private Object target;
        public CglibProxy(Object target) {
            this.target = target;
        }

        public Object getProxyObject() {
            Enhancer e = new Enhancer();// 获取生成器
            e.setSuperclass(target.getClass());// 设置目标对象
            e.setCallback(this);// 设置回调函数
            return e.create();// 返回代理对象
        }

        @Override
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            before();
            Object result = method.invoke(target);
            return result;
        }

        public void before () {
            System.out.println("before .....");
        }
    }
```


- `test.java`

```java
    @Test
    public void cglib () {
        CglibService target = new CglibService();
        CglibProxy proxy = new CglibProxy(target);
        CglibService proxyObject = (CglibService) proxy.getProxyObject();

        proxyObject.save();
        proxyObject.find();
    }
```

**JDK 和 Cglib的区别**
```bash
    JDK: 基于接口的代理，一定是基于接口，会生成目标对象的接口类型的子对象
    Cglib: 基于类的代理，不需要接口，会生成目标对象类型的子对象

    Spring在运行期间，生成动态代理对象，不需要特殊的编译器

    Spring的两种代理方式：
    1.目标对象实现了若干接口，Spring使用JDK的 java.lang.reflect.Proxy类代理
    2.目标对象没有实现任何接口，Spring使用Cglib库生成目标对象的子类

    注意&提示：
    1.对接口创建代理优于对类创建代理，Spring推荐面向接口编程。
        因为这样会产生更加耦合的系统，所以Spring默认是使用JDK代理
    2.标记为final的方法不能够被通知。
        Spring是为目标类产生子类，任何需要通知的方法都会被重写，通知织入。
        final方法是不允许被重写的。
    3.Spring只支持方法连接点，不提供属性接入点，如果属性被拦截破坏了封装。

```

#### Spring AOP编程的两种方式

##### 传统动态AOP
> 使用纯Java实现，不需要专门的编译过程和类加载器，在运行期通过代理方式向目标类植入增强代码，相对复杂

- `applicationContext.xml`

```xml
    <bean class="cn.item.jdk.Inter_c" id="inter_c_jdk"/>

    <bean class="cn.item.Cglib.CglibService" id="c_cglib"/>

    <bean class="cn.item.old_aop.OldAopAdvice" id="old_aop_advice"/>

    <!-- 3.配置切面和切入点 -->
    <aop:config>
        <aop:pointcut id="allBeanAop" expression="execution(* cn.item..*.*(..))"/>

        <aop:advisor advice-ref="old_aop_advice" pointcut-ref="allBeanAop"/>
    </aop:config>
```

- `CglibService.java`

```java
    public class CglibService {
        public void save() {
            System.out.println("类save");
        }

        public int find() {
            System.out.println("类find");
            //int d = 1/0;
            return 99;
        }
    }
```

- `Inter_c.java`

```java
    public class Inter_c implements Inter {
        @Override
        public void save() {
            System.out.println("接口save");
        }

        @Override
        public int find() {
            System.out.println("接口find");
            return 0;
        }
    }
```

- `OldAopAdvice.java`

```java
    public class OldAopAdvice implements MethodInterceptor{
        private static Logger LOGGER = Logger.getLogger(String.valueOf(OldAopAdvice.class));

        @Override
        public Object invoke(MethodInvocation i) throws Throwable {
            long start = System.currentTimeMillis();

            Object proceed = i.proceed();// == method.invoke();

            long end = System.currentTimeMillis();
            long time = end - start;

            System.out.println(i.getThis().getClass().getName() + " >> " + i.getMethod().getName() + " >> " + time);
            LOGGER.info(i.getThis().getClass().getName() + " >> " + i.getMethod().getName() + " >> " + time);

            return proceed;
        }
    }
```

- `test.java`

```java
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = "classpath:applicationContext.xml")
    public class OldAop {

        @Autowired
        private Inter i;

        @Autowired
        private CglibService c;

        @Test
        public void interfaceAndClass () {
            i.find();
            i.save();
            System.out.println("............... ");
            c.find();
            c.save();
        }
    }
```



##### AspectJ (.xml配置切面)
> AspectJ是一个基于Java语言的AOP框架，Spring2.0开始支持第三方AOP框架(AspectJ),实现另一种AOP编程

- `applicationContext.xml`

```xml
    <bean class="cn.item.jdk.Inter_c" id="inter_c_jdk"/>

    <bean class="cn.item.Cglib.CglibService" id="c_cglib"/>

    <bean class="cn.item.aspect.AspectAdvice" id="aspect_advice"/>


    <aop:config>
        <aop:aspect ref="aspect_advice"><!-- ref="通知的id" -->
            <!-- 切入点 -->
            <aop:pointcut id="allBeanAspect" expression="execution(* cn.item..*.*(..))"/>

            <!-- 前置通知
            <aop:before method="firstBefore" pointcut-ref="allBeanAspect"/>
            <aop:before method="secondBefore" pointcut-ref="allBeanAspect"/>-->

            <!-- 后置通知
            <aop:after method="firstAfter" pointcut-ref="allBeanAspect"/> -->

            <!-- 后置通知且带返回值 returning="val" val和getAfterVal的形参保持一致
            <aop:after-returning method="getAfterVal" returning="val" pointcut-ref="allBeanAspect"/>
            -->

            <!-- 环绕通知-->
            <aop:around method="aroundAdvice" pointcut-ref="allBeanAspect"/>

            <!-- 抛出通知 -->
            <aop:after-throwing method="afterThrowAdvice" throwing="ex" pointcut-ref="allBeanAspect"/>
            
            
            <!-- 最终通知: 就算方法发生异常，最终通知都会执行 -->
            <aop:after method="afterFinally" pointcut-ref="allBeanAspect"/>
            <!--<aop:after method="afterFinally" pointcut="bean(*Service)"/>-->
        </aop:aspect>
    </aop:config>
```

- `AspectAdvice.java`

```java
    public class AspectAdvice {
        public void firstBefore(JoinPoint joinPoint) throws Throwable {
            System.out.println("first before ... ");
        }

        public void secondBefore () {
            System.out.println("second before ... ");
        }

        public void firstAfter() {
            System.out.println("first after... ");
        }


        public void getAfterVal(JoinPoint joinPoint, Object val) throws Throwable {
            System.out.println(" get after value .."+ val);
        }


        public Object aroundAdvice(ProceedingJoinPoint pJP) throws Throwable {
            System.out.println("环绕前...");
            Object result = pJP.proceed();
            System.out.println("环绕后 。。...");// 如果有异常，环绕后不能执行
            return result;
        }


        public void afterThrowAdvice(JoinPoint jP, Throwable ex) throws Throwable {
            System.out.println("::"+ jP.getTarget().getClass().getName());// cn.item.Cglib.CglibService 类的路径
            System.out.println("::"+ jP.getSignature().getName());// find 方法名
            System.out.println("::"+ ex.getMessage());// int i = 1/0; >> / by zero
        }

        public void afterFinally(JoinPoint jP) throws Throwable {
            System.out.println("after finally ...");
        }
    }
```

- `Test.java`

```java
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations="classpath:applicationContext-aspect.xml")
    public class Aspect {
        @Autowired
        private Inter i;

        @Autowired
        private CglibService c;

        @Test
        public void interfaceAndClass () {
            i.find();
            i.save();
            System.out.println(" >>>>>>>>>>> ");
            c.find();
            c.save();
        }

    }

```

##### 通知小结

- 只要掌握around通知类型，就可以实现其他四种效果

```java
    try{
        // 前置通知
        Object result = proceedingJoinPoint.proceed();
        // 后置通知
    } catch(Exception) {
        // 抛出通知
    } finally {
        // 最终通知
    }
```

##### AspectJ (注解配置切面)

- `applicationContext.xml`

```xml
    <!-- 开启注解扫描 -->
    <context:component-scan base-package="cn"/>
    <!-- 配置aop的aspectj的自动代理：
			自动扫描bean组件中，含有@Aspect的bean，将其作为aop管理，开启动态代理-->
    <aop:aspectj-autoproxy proxy-target-class="true"/>    
```

- `Advice.java`

```java
    @Component("annoAdvice")// 相当于<bean id="myAspect" class="cn.itcast.spring.a_aspectj.MyAspect"/>
    @Aspect// 相当于<aop:aspect ref="myAspect">
    public class Advice {


    /*    @Before("bean(*service)")
        public void before () {
            System.out.println("before ...");
        }*/


        // 自定义切入点
    /*    @Before("adviceCustomer()")
        public void before () {
            System.out.println("before ...");
        }
        @Pointcut("bean(p_service)")// 这里是自定义切入点
        public void adviceCustomer() throws Throwable{
            System.out.println("advice customer ...");
        }*/


    /*    @AfterReturning(value="cut1() || cut2()", returning = "val")
        public void afterReturn(JoinPoint joinPoint, Object val) {
            System.out.println(val);
            System.out.println("after return ...");
        }

        @Pointcut("bean(c_service)")
        public void cut1() {
        }

        @Pointcut("bean(p_service)")
        public void cut2() {
        }*/


    /*    @Around(value = "pServiceCut()")
        public Object around(ProceedingJoinPoint pJP) throws Throwable {
            System.out.println("环绕前..");
            Object proceed = pJP.proceed();
            System.out.println("环绕后..");
            return proceed;
        }

        @Pointcut("bean(p_service)")
        public void pServiceCut() {

        }*/

        @AfterThrowing(value = "execution(* cn.item.a_aspect_anno.CustomerService_c.*())", throwing = "ex")
        public void afterThrow(JoinPoint jp, Throwable ex) throws Throwable {
            System.out.println("..出异常了");
        }

        @After("bean(*service)")
    //    @After("execution(* cn.item.a_aspect_anno.CustomerService_c.find())")// 具体类的具体方法
        public void after(JoinPoint jp) {
            System.out.println(jp);// execution(void cn.item.a_aspect_anno.ProductService.find())
            System.out.println("最终通知..");
        }
    }
```

- `test.java`

```java
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations="classpath:applicationContext.xml")
    public class ATest {

        @Autowired
        private CustomerService c;

        @Autowired
        private ProductService p;

        @Test
        public void before () {
            c.save();
            c.find();
            ((CustomerService_c) c).update();// 会报错
            // java.lang.ClassCastException:
            // com.sun.proxy.$Proxy14 cannot be cast to cn.item.a_aspect_anno.CustomerService_c
            System.out.println(" -------------------");
            p.find();
            p.save();
        }
    }
```

### Spring JdbcTemplate


