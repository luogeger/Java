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
    - 配置文件解决实体类路径带来的问题
    ```xml
        <bean id="Dao_c" class="cn.item.spring01.Dao_c"></bean>
    ```

    - 使用步骤
    ```java
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Dao dao = (Dao) ac.getBean("Dao_c");
        dao.findUser();
    ```

#### 依赖注入


#### Spring工厂的了解

#### IoC装配Bean => xml
##### 四种方式
##### Bean的作用域
##### Bean的生命周期

#### IoC装配Bean => annotation

#### xml、annatation混合配置Bean

#### Spring的web集成

#### SpringTest

#### AOP
> AOP **Aspect Oriented Programing** 面向切面编程