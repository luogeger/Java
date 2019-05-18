# SQL分类

- **DDL** : 数据库定义语言 `Data Definition Language` ，用来定义数据库对象 
    - create , drop , alter, truncate
   
- **DML** : 数据操纵语言 `Data Manipulation Language` ，数据表中更新、增加、删除记录
    - update , insert , delete
   
- **DCL** :数据控制语言 `Data Contrlol Language` ，设置用户权限和控制事务
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
   
- **DQL** : 数据查询语言 `Data Query Language` ，记录的查询
    - select ...

# SQL语句	

### database desgin

- 范式
    - 原子性，不可拆分
    - 完全依赖主键    
    - 不产生传递依赖
    
### DCL

- 连接数据库     `mysql -h root -p`
- 连接其他数据库 `mysql -h userIP -P userPORT -u root -p`


### DDL

- 退出            `exit;`
- 查询所有DB       `show databases;`
- 创建DB          `create databese <first> character set utf8`
- 查看DB编码       `show create database <first>`
- 修改DB编码      `alter database <first> character set utf8`
- 删除DB         `drop database <first>`
- 查看当前使用DB   `select database()`
- 切换DB         `use <first>`
- 导入sql文件     `source <fileAbsolutePath>`

### TABLE

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

### DML

- 查看表数据             `select* from <tableName>`
- 插入数据(指定字段)      `insert into <tableName> (id, name) values (null, 'jim')`
- 插入数据(省略所有字段名) `insert into person values (null, 'luogeger', '522933', '186...', 'China')`
- 修改字段值(where)      `update <tableName> set pwd='123456'` => 所有记录的字段值都被修改成123456
    - `update <tableName> set pwd='522933' where id=2`
- 删除记录(where)        `delete from <tbaleName> where id=2;`
- 删除表中所有记录        `delete from <tableName>` **表清空了**

### DQL    
- `null` 不参与运算
- `is null`：`where birthday is null`
- `is not null`：`where name is not null`

- 运算符
    - `<> !=` 不相等 
    - `and` =>         `select name, age from person where age = 23 and name = 'lisi';`
    - `between and` => `select name, score from person where score between 81 and 93;`
        - 分数在81-93之间的人的姓名及分数
    - `in` => `select * from person where age in (17， 18， 19)` 
    - `or` => `select * from person where age = 17 or age = 18 or age = 19`
        - 和上面`in`的结果一样

- 模糊查询 
    - `like '%刘%'`： 只要有刘就可以
    - `like '%明'`： 以明结尾
    - `like '刘%'`： 姓刘的
    - `like '_晓_`： 姓名中间是晓
    - `like '_冲`： 单名叫冲
    - `like '张_`： 姓张单名

- 过滤重复  
    - `distinct`：`select distinct age from person`：显示不重复的年龄
    - `SELECT DISTINCT role_name FROM tbl_cop2sys_role`: 查询所有的角色
    - 查询所有角色的数量
    ```sql
        SELECT COUNT(temp.role_name) FROM
            (SELECT DISTINCT role_name FROM tbl_cop2sys_role) as temp
    ```

- 结果排序  
    - `order by`：对结果进行排序，`null`在排序是最小的
    - 分数不为`null`的降序
        - `SELECT * FROM a_student WHERE score IS NOT NULL ORDER BY age DESC`

- 起别名  
    - `select name, score from a_student`, 只需要`student`里的2个字段
    - `select name as 姓名, age as 年龄 from a_student`, 给这个2个字段起别名
    - `select name score from a_student`, 省略之后就意思相当给`name`起了个`score`的别名
        - 等同于`select name 姓名 from a_student`
    

### aggregate function

- **count**
    - `null` 不参与统计
    - `SELECT COUNT(SCORE) FROM A_STUDENT`, 
    - `SELECT COUNT(*) FROM PERSON WHERE AGE > 18`

- **sum**
    - 多列求和, `null` 不参与计算 
        - `select sum(score) from students`
    - 分别统计总人数，总年龄，总分数，`null`不参与计算
        - `SELECT COUNT(NAME) 总人数, SUM(AGE) 总年龄, SUM(SCORE) 总分数 FROM A_STUDENT`  
    - `sum()`可以参与计算
        - `select sum(age + score) from a_student`, 这里是先把`age`和`score`相加，如果有值为`null`就返回`null`了，总和会变小
        - `select sum(age) + sum(score) from a_student`, 这里才是正确的              
    - `ifnull`：列值，整体返回值  
        - `select sum(ifnull(age, 0) + ifnull(score, 0)) from a_student` : 如果值为 null, 提供默认值0
    - `truncate (数值，保留小数位)`
        - `select truncate(sum(ifnull(age, 0) + ifnull(score, 0)), 2) from a_student`

- **avg**
    - 平均数 `select avg(score) / count(*) from students`

- **max**, **min**
    - `null` 不参与计算
    - `select max(score) 最高分, min(score) 最低分 from students;`

- **group by**
    - 按照某列或某几列，把相同的数据进行合并输出
    - `SELECT * FROM A_COURSE GROUP BY COURSE DESC`， 默认是降序, 有过滤重复字段的效果
    - `SELECT ID, COURSE, SUM(SCORE) FROM A_COURSE GROUP BY COURSE`
    - `SELECT ID, COURSE, SUM(SCORE) FROM A_COURSE GROUP BY COURSE, SCORE`, 如果语文成绩的分数不一样，达不到分类的效果
        - 先按照科目名分组，在按分数进行分组，再合并输出
    
- **having**
    - `having`必须和`group by`一起使用，用法和`where`一样，但是`where`后面不可以跟聚合函数
    - `SELECT COURSE, SUM(SCORE) FROM A_COURSE WHERE SCORE > 5 GROUP BY COURSE HAVING SUM(SCORE) > 20`
        - `select`后面是输出形式，`group by`后面只根据科目名分类，不需要再根据分数分类，不然分数不相同科目相同也会被分类出来
    - `having` 是在分组之后进行过滤，`where`是在分组之前进行过滤，

- **查询的执行顺序:**
    
| - | - |    
| :--- | :--- |
| `from`     | 表名     
| `where`    | 条件过滤  
| `group by` | 分组              
| `having`   | 分组之后进行过滤          
| `select`   | 执行完毕之后显示内容          
| `order by` | 把内容进行排序输出          


### multi-table queries
- 外键约束
    - `foreign key(<currentFiled>) reference(<foreignFiled>)` , 约束从表，保证数据有效性， 
    - 已经存在的表添加外键约束
        - `alter table coder_project add foreign key(coder_id) references coder(id);`
    - 新建表的时候添加外键约束
        ``` sql
        create table coder_project (
            coder_id int,
            project_id int,
            foreign key (coder_id) references coder(id),
            foreign key (project_id) references project(id)
        );
        ```
    - 删除：先删从表，再删主表
    - 添加：

- 笛卡尔积：
```sql
    # 笛卡尔积
    SELECT * FROM B_PRODUCT, B_PRICE
    
    # 隐式内连接
    SELECT * FROM B_PRODUCT AS A, B_PRICE AS B WHERE A.ID = B.B_PRODUCT_ID 
    
    # 显示内连接
    SELECT * FROM B_PRODUCT AS A INNER JOIN B_PRICE AS B ON A.ID = B.B_PRODUCT_ID 
    
    # 左外连接
    SELECT * FROM B_PRODUCT AS A LEFT OUTER JOIN B_PRICE AS B ON A.ID = B.B_PRODUCT_ID 

    select b.*, s.dict_name from brand as b left outer join	system_dict as s on	b.exhibition_type = s.id
    
    # 右外连接
    SELECT * FROM B_PRODUCT AS A RIGHT OUTER JOIN B_PRICE AS B ON A.ID = B.B_PRODUCT_ID 
    
    # 全连接 会自动去重
    SELECT * FROM B_PRODUCT AS A LEFT OUTER JOIN B_PRICE AS B ON A.ID = B.B_PRODUCT_ID 
    UNION 
    SELECT * FROM B_PRODUCT AS A RIGHT OUTER JOIN B_PRICE AS B ON A.ID = B.B_PRODUCT_ID 
    
    # 全连接 不会去重
    SELECT * FROM B_PRODUCT AS A LEFT OUTER JOIN B_PRICE AS B ON A.ID = B.B_PRODUCT_ID 
    UNION ALL
    SELECT * FROM B_PRODUCT AS A RIGHT OUTER JOIN B_PRICE AS B ON A.ID = B.B_PRODUCT_ID 
```

### subquery

- `in`
```sql
    # 不及格的学生的信息 -- student
    SELECT * FROM STUDENT WHERE ID IN (
        SELECT STUDENT_ID FROM STUDENTCOURSE WHERE SCORE <= 60)
        
    # jack参与了哪些项目 -- project        
    SELECT PROJECT FROM PROJECT WHERE ID IN (
        SELECT PROJECT_ID FROM CODER_PROJECT WHERE CODER_ID IN (
            SELECT ID FROM CODER WHERE CODER = 'jack'))
```    

- **exists**
    - 不及格的学生的所有信息, `student`和`temp`的所有字段
    ```sql
        select * from 
            student, (select * from student_course where score < 70) as temp 
        where student.id = temp.student_id order by temp.score;
    ```    
    - 只有 student 的字段，  ↓ :意注       
    ```sql
        select * from student where exists (
            select * from student_course where score < 70 and student_course.student_id = student.id
    ```

- **all**
    - 查询年龄最大的学生的信息
    ```sql
      SELECT MAX(AGE) FROM STUDENT 
      
      SELECT * FROM STUDENT WHERE AGE =(SELECT MAX(AGE) FROM STUDENT)
      
      # 必须用 >=
      SELECT * FROM STUDENT WHERE AGE >= ALL(SELECT AGE FROM STUDENT)
    ```
    

- **any**, **some**

- **as**

- **limit**
    - `select * from stu_info order by stu_id limit 0, 300` 前300条数据排序

- **查询所有分数在60 - 70的学生对应的学科**
    - 
    ```sql
        select student.name '姓名', course.name '课程', temp.score '分数' from 
            student, 
            course,
            (select * from student_course where score between 60 and 70) as temp
        where 
            temp.student_id = student.id and temp.course_id = course.id
        order by temp.score;
    ```
    
### index
    

### native function
- 日期
```sql
    select now(); -- 2017-06-26 11:07:24
    select current_date(); -- 2017-06-26
    select current_time(); -- 11:08:23
    select year(now()); -- 2017
``` 

- 字符串
```sql
    select substring('黑旋风',0,1); -- 
    select substring('黑旋风',1,1); -- 黑
    select substring('黑旋风',2,1); -- 旋
```

- 数学
```sql
    select ceiling(1.2); -- 向上取整: 2
    select floor(1.2);	-- 向下取整: 1
    select format(3.1415926, 4); -- 截取位数: 3.1416, 有四舍五入的功能
```


# JDBC

- **java database connection**

> **快速入门**

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

> **API详解**

- `DriverManager`   驱动管理类
- `Connection`      数据库连接接口
- `Statement`       发送SQL语句
    - `PreparedStatement`  发送预编译的SQL语句到数据库
        - `pst.setString("lucy");`
        - `pst.setString("lili");`
- `ResultSet`          获取结果
- `CallableStatement`   操作存储过程


> **_jdbc抽取 CRUD操作**

```java
public class JdbdCrud {
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
}// end
```

> **SQL注入**

- `String user = "lucy' -- ";`  `--`  注释的方式不需要密码
- `String pwd = "xxx' or '1' = '1";`  `or`  能全部查出来

```java
public class inject {
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

# transaction

- MYSQL5.5以后支持事务，存储引擎`InnoDB`支持事务，特点是自动提交
- 概念：事务指的是逻辑上的一组操作，组成这组操作的各个单元，要么全部成功，要么全部失败(业务上市最小的工作单元，不可拆分)。
- 作用：保证在一个事务中多次操作数据库中的数据时，要么全部成功，要么全都失败。
- 只支持DML


- `start transaction` ： 开启事务。 
- `commit` ：            提交事务。
- `rollback` ：          回滚事务。
- `show variables like '%commit%'` ：查看`autocommit`的模式，默认是 **on**, 打开状态。
- `set autocommit = 0` ：关闭自动提交

> **事务特性**

- `Atomicity`   原子性：
- `Consistensy` 一致性：
    - 一个事务在执行之前和执行之后，数据库都必须是一致性的。提交成功，数据库变化生效，提交失败，数据库回滚到原始状态。
- `Durabitity`  持久性：
    - 一个事务提交之后，对数据库的改变时永久性的，就算数据库出现异常，重启之后，数据依然存在。
- `Isolation`   隔离性：
    - 多个用户并发访问数据库的时候，一个用户的事务不能被其他用户的事务干扰。
    
> **事务隔离性**
> 如果不考虑事务隔离性会出现以下问题

- 脏读
    - 一个事务读取了另一个事务**未提交**的数据。

- 不可重复读
    - 一个事务读取到另一个事务**已提交**的数据。(不考虑时效性)

- 虚读(幻读)
    - 一个事务多次读取了另一个事务已提交的数据的**数量**，强调的是`insert` , `delete`。

> **jdbc操作事务**

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

# connection pool
- 优化获取连接，主要是从性能上优化
- `public interface DataSource extends CommonDataSource, Wrapper` : 厂商实现这个规定的接口
    - `Connection getConnection()`

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

### C3P0
- 基本用法：

```java
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


### DRUID
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
    
        @Test// 一个字段
        public void test1 () {
            String pwd = jt.queryForObject("select password from user where id = ?", String.class, 1);// 返回的是 String
            System.out.println("pwd = " + pwd);
        }
    
        @Test// 多个字段 == List集合对象
        public void test4 () {
            List<String> list = jt.queryForList("select password from user", String.class);
            System.out.println("list = " + list);
        }
    
        @Test// 一行记录 == 返回对象
        public void test2 () {
            Map<String, Object> map = jt.queryForMap("select * from user where id = ?", 1);
            Set<String> keys = map.keySet();
            for (String key : keys) {
                System.out.println(key + map.get(key));
            }
        }
        
        @Test // 一行记录 == 返回实例
        public void test3 () {
            User u = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getEmail(), user.getPassword());
        }

        @Test// 多行记录 == queryForList
        public void test5 () {
            JdbcTemplate jt = before();
            String sql = "SELECT * FROM tab_user";
            List<Map<String, Object>> list = jt.queryForList(sql);
            for (Map<String, Object> user : list) {
                System.out.println(user);
            }
        }
    
        @Test// 多行记录 == query
        public void test6 () {
            JdbcTemplate jt = before();
            String sql = "select * from tab_user";
            List<User> users = jt.query(sql, new BeanPropertyRowMapper<>(User.class));
            for (User user : users) {
                System.out.println(user);
            }
        }
        
        @Test// 聚合函数
        public void test7 () {
            Integer count = jt.queryForObject("select count(*) from user", Integer.class);// 返回的是 Integer
            System.out.println("count = " + count);
        }
        
    }
```

# ORACLE
- sql增强
    - 表里的值区分大小写
    - 隐形转换，字符串转成数字
    - ”||“
    - 伪表
    - 空值运算
    
- 单行函数
    - 函数的分类
    - 字符函数
    - 数字函数
    - 日期函数
    - 转化函数
    - 滤空函数
    - 条件表达式
    
- 多表查询
    - 内连接
    - 外连接
    - 自连接
    
- sql99语法
- oracle自带语法

- 伪列
    - ROWNUM
    - ROWID        

- 索引

- SELECT * FROM emp t1, emp t2 WHERE t1.sal > t2.sal AND ename = 'SCOTT';

- 主键自增
- 没有驼峰
