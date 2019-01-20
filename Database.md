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

- MYSQL5.5以后支持事务，存储引擎`InnoDB`支持事务，特点是自动提交
- 概念：事务指的是逻辑上的一组操作，组成这组操作的各个单元，要么全部成功，要么全部失败(业务上市最小的工作单元，不可拆分)。
- 作用：保证在一个事务中多次操作数据库中的数据时，要么全部成功，要么全都失败。
- 只支持DML


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

```javascript 1.5
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
```java
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

# ORACLE

- 表里的值区分大小写
- 隐形转换，字符串转成数字
