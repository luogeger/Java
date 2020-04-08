Mysql

- 范式
    - 原子性，不可拆分
    - 完全依赖主键    
    - 不产生传递依赖

### DCL
	数据控制语言Data Contrlol Language ，设置用户权限和控制事务

- 连接数据库     `mysql -h root -p`
- 连接其他数据库 `mysql -h userIP -P userPORT -u root -p`
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

### DDL

	数据库定义语言Data Definition Language，用来定义数据库对象create , drop , alter, truncate

- 退出            `exit;`
- 查询所有DB       `show databases;`
- 创建DB          `create databese <first> character set utf8`
- 查看DB编码       `show create database <first>`
- 修改DB编码      `alter database <first> character set utf8`
- 删除DB         `drop database <first>`
- 查看当前使用DB   `select database()`
- 切换DB         `use <first>`
- 导入sql文件     `source <fileAbsolutePath>`
- 查看所有表     `show tables`
- 查看表结构     `desc <tableName>`
- 创建表        `create table <tableName> (id int(10) not null primary key auto_increment, name varchar(50), pwd varchar(50));`

```sql
create table if not exists user
(
  id          int(20) auto_increment                         comment '自增ID' primary key,
  name        varchar(32) default ''                not null comment '姓名',
  age         int(8)      default 0                 not null comment '年龄',
  create_time timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
  comment '用户表';
```

- 修改表名      `rename table <oldName> to <newName>`

- 删除表        `drop table <tableName>` **表不存在了**

- 删除表再新建   `truncate <tableName>` **清空**

- 查看表编码     `show create table <tableName>`

- 修改表编码     `alter table <tableName> character set <utf8>`

- 增加字段       `alter table <tableName> add <fileName> <type>(length)`

    ```sql
    # 增加字段
    alter table tt_user add password varchar(20) not null default '' comment '用户密码';
    alter table tt_user add teacher varchar(20) not null default '' comment '老师';
    alter table tt_user add course varchar(20) not null default '' comment '课程';
    ```

- 删除字段       `alter table <tableName> drop <filedName>`

    ```sql
    # 删除字段
    alter table tt_user drop password;
    ```

- 修改字段   `alter table <tableName> modify id int(11) auto_increment;`

    ```sql
    # 修改表字段
    alter table tt_user modify age int(4) not null default -1 comment '用户年龄';
    ```

**约束**

- 主键    `primary key`   
- 自增长   `auto_increment`
- 唯一    `unique`
- 非空    `not null`

**索引**    

- 唯一索引
- 联引索引

```sql
# 添加索引
alter table tt_user add index index_name(name);

# 添加唯一索引
alter table tt_user add unique unique_name(name);

# 添加联合索引
alter table tt_user add index joint_index(teacher, course);
```













### DML
	数据操纵语言Data Manipulation Language，  insert， create， update操作

- 查看表数据             `select* from <tableName>`
- 插入数据(指定字段)      `insert into <tableName> (id, name) values (null, 'jim')`
- 插入数据(省略所有字段名) `insert into person values (null, 'luogeger', '522933', '186...', 'China')`
- 修改字段值(where)      `update <tableName> set pwd='123456'` => 所有记录的字段值都被修改成123456
    - `update <tableName> set pwd='522933' where id=2`
- 删除记录(where)        `delete from <tbaleName> where id=2;`
- 删除表中所有记录        `delete from <tableName>` **表清空了**

### DQL
	数据查询语言Data Query Language， select操作
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
    

### 聚合函数

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

|      |      |
| ---- | :---- |
|  `from`     |    表名  |
|   `where`    |   条件过滤   |
|   `group by`   |分组|
|   `having`   |   分组之后进行过滤   |
|   `select`   |   执行完毕之后显示内容   |
| `order by` |       把内容进行排序输出        |




### 连接查询
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
    select * from b_product, b_price
    
    # 隐式内连接
    select * from b_product as result, b_price as b where result.id = b.b_product_id 
    
    # 显示内连接
    select * from b_product as result inner join b_price as b on result.id = b.b_product_id 
    
    # 左外连接
    select * from b_product as result left outer join b_price as b on result.id = b.b_product_id 

    select b.*, s.dict_name from brand as b left outer join	system_dict as s on	b.exhibition_type = s.id
    
    # 右外连接
    select * from b_product as result right outer join b_price as b on result.id = b.b_product_id 
    
    # 全连接 会自动去重
    select * from b_product as result left outer join b_price as b on result.id = b.b_product_id 
    union 
    select * from b_product as result right outer join b_price as b on result.id = b.b_product_id 
    
    # 全连接 不会去重
    select * from b_product as result left outer join b_price as b on result.id = b.b_product_id 
    union all
    select * from b_product as result right outer join b_price as b on result.id = b.b_product_id 
```



### 子查询

> 概述：当一个查询是另一个查询的条件时，称之为子查询

- `in`
```sql
    # 不及格的学生的信息 -- student
    select * from student 
    where id in ( select student_id from studentcourse where score <= 60)

    # jack参与了哪些项目 -- project        
    select project from project where id in (
        select project_id from coder_project where coder_id in (
            select id from coder where coder = 'jack'))
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
      select max(age) from student 
      
      select * from student where age =(select max(age) from student)
      
      # 必须用 >=
      select * from student where age >= all(select age from student)
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
    

### 原生函数
- 日期
```sql
    select now(); -- 2018-06-26 11:07:24
    select current_date(); -- 2018-06-26
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

###  JDBC

- **java database connection**

> **快速入门**

```java
import org.junit.Test;
import com.mysql.jdbc.Driver;
import java.sql.*;

public class JdbcTest {
    @Test
    public void main1 (){
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
    }
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


> **jdbcUtils抽取 CRUD操作**

```java
public class JdbcCrud {
    @Test
    public void query () {
        Connection conn = null;
        Statement stt = null;
        ResultSet rs = null;
        try {
            conn = jdbcUtils.getConnection();
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
            jdbcUtils.release(rs, stt, conn);
        }

    }
    
    @Test
    public void update () {
        String sql = "update user set password = '522933' where username = 'lucy'";
        Connection conn = null;
        Statement stt = null;
        try {
            conn = jdbcUtils.getConnection();
            stt = conn.createStatement();
            int i = stt.executeUpdate(sql);
            System.out.println("i => " + i);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.release(null, stt, conn);
        }
    }
}
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
            conn = jdbcUtils.getConnection();
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
            jdbcUtils.release(rs, pst, conn);
        }
        
    }
    
    @Test
    public void updateOrInsert () {
        String update = "update user set password = ? where username = 'lucy'";
        String insert = "insert into user values (null, ?, ?, null, null);";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = jdbcUtils.getConnection();

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
            jdbcUtils.release(rs, pst, conn);
        }
    }
    
    @Test
    public void delete () {
        String delete = "delete from user where username = ?";
        Connection conn         = null;
        PreparedStatement pst   = null;
        ResultSet rs            = null;
        try {
            conn = jdbcUtils.getConnection();
            pst = conn.prepareStatement(delete);
            pst.setString(1, "omg");
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.release(rs, pst, conn);
        }
    }
}
```

### Transaction

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
    
> **事务隔离级别**

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
            conn = jdbcUtils.getConnection();
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
            jdbcUtils.release(rs, pst, conn);
        }
    }
}
```

### Connection Pool

- 优化获取连接，主要是从性能上优化
- `public interface DataSource extends CommonDataSource, Wrapper` : 厂商实现这个规定的接口
    - `Connection getConnection()`

```java

/**
*  实现接口
*/
public class Adaptor implements DataSource {
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
    
    // ... 其他的重写方法
}   

/**
* 实现父类
*/
public class MyComboPooled extends Adaptor {
    private LinkedList<Connection> list = new LinkedList<>();
    
    public MyComboPooled () {// 构造函数
        for (int i = 0; i < 5; i++) {
            list.addLast(jdbcUtils.getConnection());
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

/**
* 测试
*/
public class ComboPooledTest {
    @Test
    public void main() {
        MyComboPooled pool = new MyComboPooled();
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
            jdbcUtils.release(rs, pst, conn);// 释放资源
            pool.backConnection(conn);// 还给连接池，连接池是通过动态代理完成的，
        }
    }
}
```



### JDBCTemplate

- `JDBCTemplate` 是 `Spring` 对 `JDBC`的封装，目的是使`JDBC` 更加易于使用，处理了资源的建立和释放。
    - `commons-logging-1.2.jar` ：日志记录
    - `spring-beans-5.0.0.RELEASE.jar` ：JavaBean管理包
    - `spring-core-5.0.0.RELEASE.jar` ：核心包
    - `spring-jdbc-5.0.0.RELEASE.jar` ：jdbc支持包
    - `spring-tx-5.0.0.RELEASE.jar` ：事务支持包
- 使用方法
    - `JdbcTemplate jt = new JdbcTemplate(javax.sql.DataSource dataSource)`
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
        private JdbcTemplate jt = new JdbcTemplate(jdbcUtils.getComboPooled("DRUID"));
    
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



###  SQL优化

- `show variables like 'test_0520';`    
- `show variables like '%log%';`
- `set global log_queries_not_using_indexes=on';` 打开慢查询
- `show variables like 'long_query_time';`
- `set global slow_query_log=on;` 开启了慢查询日志

![image-20200306153510410](C:\PC\workspace\Java\markdown-note\imgs\image-20200306153510410.png)

device_id加了索引以后

![image-20200306161617841](C:\PC\workspace\Java\markdown-note\imgs\image-20200306161617841.png)



> **索引没有命中**













# Oracle

### 系统用户及登录

>  **系统用户**

- `sys`, `system`
- `sysman`
- `scott`

> **系统用户登录SQLPlus**

- `system/password`
- `connect sys/password as sysdba`;  sys登录需要用dba的权限
- `show user`  查看当前用户
- `dba_users` 根据数据字典查看用户信息
    - 查看数据字典包含哪些字段
- `desc <table_name>` 查看表结构及字段信息

> **启用Scott用户**

- `alter user scott account unlock;`
- 使用Scott用户登录SQL Plus：scott用户的密码默认是tiger
    - `connect  scott/tiger`
    - `show user`  紧接着查看当前用户是不是scott

### 表空间

> **表空间的概述** 

数据库，表空间，数据文件之间的关系。 

> 表空间的分类

- 永久表空间
    - 表，视图，存储过程
- 临时表空间
    - 存放数据库当中操作的过程，执行完毕就自动释放
- UNDO表空间
    - 存放对数据修改之前的数据。

> **查看用户的表空间**

数据字典：`dba_tablespaces`,  `user_tablespaces`,  `dba_users`,  `user_users`

- `desc dba_tablespaces` 可以看到第一个字段就是 `TABLESPACE_NAME`

![image-20200307162753562](C:\PC\workspace\Java\markdown-note\imgs\image-20200307162753562.png)

- 设置用户的默认或临时表空间
    - **alter user username <defalut|temporary> tablespace tablespace_name**

> **创建，修改，删除表空间**

- 创建永久表空间
- 创建临时表空间
- 查看数据文件的路径
- 查看



### SQL查询

- 表里的**值**区分大小写

- 隐形转换，字符串转成数字

- `||` 字符串连接符

- 伪表

- 空值运算

- `where` 过滤子句

- 字符和日期

- `Espace` 转义字符

- 条件运算符

    - `between...and...` 包含边界值
    - `in(set)`
    - `like`
    - `is null` 
    - `and`
    - `or`
    - `not`

- `in`，`not in` 过滤时的空值

- 排序：根据别名、列号

- 条件运算的优先级

### 单行函数

操作数据对象，接收参数返回一个结果，**只对一行进行变换，每行返回一个结果**，可以转换数据类型，可以嵌套

> 函数的分类

- 字符函数
    - 大小写控制函数
    
    - 字符控制函数
    
    - | 函数                          | 结果               |
        | ----------------------------- | ------------------ |
        | concat('Hello', 'World')      | HelloWorld         |
        | substr('HelloWorld', 1, 5)    | Hello              |
        | length('HelloWorld')          | 10                 |
        | instr('HellorWorld', 'W')     | 6                  |
        | lpad(salary, 10, '*')         | *************24000 |
        | rpad(salary, 10, '*')         | 24000*********     |
        | trim('H'  from  'HelloWorld') | elloWorld          |
        | replace('abcd',  'b',  'm')   | emcd               |
    
- 数字函数 ：`round`， `trunc` 函数除了对数字起作用以外，对日期也起作用

    -  `round(45.926, 2)`  == 45.93  四舍五入
    - `trunc(45.926, 2)`  == 45.92  截断
    - `mod(1600, 300)` == 100  求余

- 日期函数

- 转化函数

- 滤空函数：也称为通用函数，其特点是：适用于任何数据类型，同时也适用于空值。

    - `nvl(result,c)`，当a为null的时候，返回c，否则，返回a本身。
    - `nvl2(result,b,c)`,当a为null的时候，返回c，否则返回b—三元运算
    - `nullif(result,b)`,当a=b的时候，返回null，否则返回a
    - `coalesce(result,b,c,d)`，从左往右查找，当找到第一个不为null的值的时候，就显示这第一个有值的值。

- 条件表达式

    - CASE 表达式：SQL99的语法，类似Basic，比较繁琐

    - DECODE 函数：Oracle自己的语法，类似Java，比较简单

        

        

### 多行函数

​		多行函数也称之为分组函数、聚集函数。简答的说就是把多行的值汇聚计算成一个值。常见的函数有

- `count`	统计时的对象不同，效率也不同

- `max`  

- `min`

- `sum`

- `avg` 

- `distinct`  关键字效率会比较低，如果仅仅是为了显示不重复的记录，建议使用`group by`

    - ```sql
        select distinct(deptno) from emp;
        select deptno from emp group by deptno;
        ```

    - ```sql
        select deptno, max(sal) from emp;
        -- 报错：不是单组分组函数
        聚合函数处理的是数据组，在本例中，MAX函数将整个EMP表看成一组，而deptno的数据没有进行任何分组，因此SELECT语句没有逻辑意义。
        要想解决这个问题，需要对deptno进行分组。
        select deptno,max(sal) from emp group by deptno;
        -- max函数是针对每一组的数据求最大值
        ```
    
- `group by` , `having`

    ```sql
    select deptno from emp group by emp.deptno having emp.deptno >= 20;
    -- having子句过滤，是先分组，再过滤，注意：分组的时候是全表扫描的，效率较低。
    
    select deptno from emp where deptno >= 20 group by deptno;
    -- 优化：where子句过滤，是先过滤再分组，注意：分组的时候仅需要扫描部分数据，效率较高。
    ```

    

### 多表查询

```
优化：连接查询的时候用别名
```

> 内连接

> 外连接
>

​		左外连接

```sql
--查询"所有"员工信息，要求显示员工号，姓名 ,和部门名称--要求使用左外连接
--sql99标准语法
select * from emp t1 left outer join dept t2 on t1.deptno = t2.deptno;

-- oracle私有语法（mysql不支持），+放到右边是左外，你可以认为(+)是附加补充的意思。
--要求查询所有的信息的表，我们可以称之为主表，而补充信息的表，称之为从表
select * from emp t1,dept t2 where t1.deptno = t2.deptno(+);
```

​		右外连接：oracle特有的，mysql没有

```sql
-- sql99,右边表（dept）数据全部显示。
select * from emp t1 right outer join dept t2 on t1.deptno = t2.deptno;

-- oracle语法
select  * from emp t1,dept t2 where t1.deptno(+) = t2.deptno;
```

> 自连接：就是将一张表当成两张表来使用
>

```sql
--查询员工信息，要求同时显示员工和员工的领导的姓名，没有领导员工不会出现
select t1.empno no, t1.ename name, t2.empno leader_no, t2.ename leader_name 
from emp t1,emp t2 where t1.mgr=t2.empno;

--查询“所有”员工信息，没有领导员工也要查出来
select t1.empno no, t1.ename name, t2.empno leader_no, t2.ename leader_name  
from emp t1,emp t2 where t1.mgr=t2.empno(+);
```



### 子查询

> 单行子查询 -- 比较操作符

```sql
--查询部门名称是SALES的员工信息
select * from emp where deptno=(select deptno from dept where dname ='sales');
```

注意：**使用子查询的时候，一定要保证子查询不能为空，否则数据就会出现异常。**



> 多行子查询  `in`, `any`, `all`

```sql
-- ## 多行子查询
-- 查找工作和'SMITH' 'ALLEN' 这两个人的工作一样的员工信息
select job from emp where ename in('smith','allen');
select * from emp where job in(select job from emp where ename in('smith','allen'));

-- 查找工作和'smith' 'allen' 这两个人的工作不一样的员工信息
select * from emp where job not in(select job from emp where ename in('smith','allen'));

-- 查询工资比30号部门任意一个员工的工资高的员工信息。--面试题
select * from emp where deptno =30;

-- 任意一个：比最低的那个高就ok。
select * from emp where sal >(select min(sal) from emp where deptno=30);

-- any(多行函数)
select * from emp where sal >any(select sal from emp where deptno=30);

-- 查询工资比30号部门所有员工的工资高的员工信息。
select * from emp where sal>(select max (sal) from emp where deptno=30);

--all(多个返回记录)--max(sal)
select * from emp where sal>all(select sal from emp where deptno=30);
```

**多行子查询和单行子查询之间的联系：**

- `> any(result, b, c)` === `> result or > b or > c `
- `> all(result, b, c)`  === `> result and > b and > c`

**子查询注意事项：**

- 子查询的位置：可以放在主查询的where、select、having、from的后面。不可以放在主查询的group by后面。

- 子查询和主查询可以是同一张表，也可以不是是不同一张表，只要子查询返回的结果在主查询中能使用即可。

- 关于使用操作符：单行操作符对应单行子查询，多行操作符对应多行子查询。
-  执行顺序：一般子查询先执行，再执行主查询；

- 关于排序：一般不在子查询中使用order by；但在top-N分析问题中，必须在子查询中使用order by。

- 多行子查询一般用于from后面，作为一张新的虚拟临时表来使用。

**子查询和多表关联查询的选择**

```
理论上，在都可以实现需求的情况下尽量选择多表查询。
原因：子查询会操作两次，多表查询只操作一次。多表的效率高。
但要注意的是，多表查询如果产生了笛卡尔集（语句上要注意条件的使用），则会出现严重的效率问题。
一般不在子查询中使用排序（order by），但在top-N分析问题中必须在子查询中使用排序。
```





### 伪列

​		伪列是oracle中虚拟的列，列的数据是由oracle进行维护和管理的。用户只能查看这个列，不能修改。所有的伪列要得到值必须要**显示**的指定。最常用的两个伪列：`rownum`,  `rowid` 

> **rownum**

- 执行查询操作时产生，由数据库产生，每次查询都会重新生成。

- 只能使用 `<`, `<=` 的符号，不能使用 `>`, `>=`。因为oracle是基于行的数据库，行号永远是从1开始的。

- 不受 `order by` 影响，因为 `rownum` 是在查询出来结果的时候就产生了，而 `order by` 是对查询结果的排序

    **如果要先排序再查询的话，可以使用子查询**

    ```sql
    --  order by是查询语句出来的结果之后再排序的，rownum是在查询出来结果（此时行号已经有了，已经和每一行数据绑定了）的时候产生。所以不会影响到行号
    select rownum,t.* from emp t order by deptno;
    
    --  先排序，再查询
    select rownum,t.* from (select * from emp order by deptno) t;
    ```

    **使用行号进行数据分页**

    ```sql
    
    ```

> **rowid**

- 用来标识表中唯一的一条记录，并间接的给出了表行的物理位置，定位表行最快的方式。
- 主要是给数据库用的，类似UUID
    - **主键：** 标识唯一的一条业务数据。不是给数据库用的，是给业务用的。

**rowid的查看：** 

**rowid的产生**：使用insert语句插入数据时，oracle会自动生成rowid并将其值与表数据一起存放到表行中。

![image-20200308222548450](C:\PC\workspace\Java\markdown-note\imgs\image-20200308222548450.png)

**rowID的作用**：l去除重复数据。--面试题—了解。在plsql Developer中，加上rowid可以更改数据。

**rownum,  rowid 两者之间的区别**

- rownum不是表中原本的数据，只是在查询的时候才生成的。rowID是插入数据的时候产生。

### 数据操作

- `insert`
- `update`
- `delete`





### 事务

**事务的开始和结束**

- **开始：** 以第一个DML语句(`insert`， `update`， `delete`)的执行作为开始，即是自动开启的事务。
- **结束：** 
    - 显式结束：`commit`， `rollback`（还是隐式`commit`）
    - 隐式结束（自动提交）：DDL（`create table…`）和DCL（所以不能回滚 ），`exit`(事务正常退出)
    -  隐式回滚（系统异常终止）：关闭窗口，死机，掉电。

**事务控制**

- `savepoint`
- `rollback`



### 数据库对象



### 约束



### 序列 Sequence



### 视图 View



### 同义词 Synonym



### 索引 Index









# Redis 

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
- `type <keyName>` ：查看key的类型
- `del key1 key2`： 删除可以
- `move keyName 1` ： 移动key到其他库
- 查看当前的所在库
- `dbsize`：查看当前库key的数量
- `rename key1 key2` ：重命名
- `expire key1 100`
- `persist keyName`
- `keys *` ：获取所有的key
- `keys user_*` :  获所有取带user_前缀的key
- `exists keyName` ： 查看key是否存在
- `ttl keyName`：  查看key的过期时间




### 数据类型

- `String`
    - `set name "lucy"` 
    - `get name` 
    - `del name`
    
- `Hash`
    - `hset` 设置字段值
        - `hset userInfo password "1234"`
        - `hset userInfo email "11@qq.com"`
    - `hget` 获取字段值
        - `hget userInfo email`
    - `hmset` 设置多个字段值
        - `hmset userInfo name "lucy" mobile "13756565"`
    - `hmget` 获取多个字段值        
        - `hmget userInfo name email`
    - `hdel` 删除字段：一次删除一个或多个字段，返回的是删除字段的个数
        - `hdel userInfo password age address`
    - `hmget` 一次设置`key`的多个值
        - `hmset info email "11@qq.com" address "jiangsu"`
    - `hkeys` 一次获取所有的`key`
        - `hkeys userInfo`
    - `hvals` 一次获取所有的值
        - `hvals userInfo`
    - `hgetall` 一次获取`key`对应的所有值
        - `hgetall userInfo`

- `List` : 底层是 LinkedList
    - `lpush`
    - `rpush`
    - `lpop`
    - `rpop`
    - `lrange key [start] [end]` 返回索引对应区间值
    - `lindex key [index]` 返回索引对应的指定值

- `Set`是`String`类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。
    - `sadd` 返回字段数量
    - `smembers key` 获取
    - `srem` 删除
    - `scard` 返回字段数量

- `sorted set`: 中的每一个成员都会有一个分数(score)与之关联，Redis正是通过分数来为集合中的成员进行从小到大的排序。成员是唯一的，但是分数(score)却是可以重复的。


