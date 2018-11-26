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
- 四种方式
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

        @Override
        public String toString() {
            return "Car{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

```
- `Person.java`

```java
    public class Person {

        private  Integer id;
        private String name;
        private Car car;

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", car=" + car +
                    '}';
        }
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

#### xml、annatation混合配置Bean

#### Spring的web集成

#### SpringTest

#### AOP
> AOP **Aspect Oriented Programing** 面向切面编程