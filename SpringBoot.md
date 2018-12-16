# SpringBoot

#### 概述
- spring体系的一部分，创建独立的spring应用程序
- 内嵌tomcat，jetty，undertow，不需要打包成war包部署，自动配置spring和第三方库
- 启动器依赖，不会有版本冲突，简化配置文件


#### 启动器
- 启动器的父工程
- 引导类
    - `@EnableAutoConfiguration`
    - `@ComponentScan` :开启终结扫描
    - `@SpringBootApplication` ：相当于上面两个注解 + `@SpringBootCongfiguration`
        - `@SpringBootCongfiguration` ：相当于`@Configuration`
   


#### 默认配置    
- **配置文件**注入
    - `jdbc.properties`
    - `JdbcConfig.java`
    ```java
        @Configuration
        @PropertySource("classpath:jdbc.properties")
        public class JdbcConfig {
            @Value("${jdbc.url}")
            private String url;

            @Value("${jdbc.driverClassName}")
            private String driveClassName;

            @Value("${jdbc.username}")
            private String username;

            @Value("${jdbc.password}")
            private String password;
            @Bean
            public DataSource dataSource() {
                DruidDataSource dds = new DruidDataSource();
                dds.setUrl(this.url);
                dds.setDriverClassName(this.driveClassName);
                dds.setUsername(this.username);
                dds.setPassword(this.password);
                return dds;
            }
        }
    ```
    - `TestApplication.java`
    ```java
        @SpringBootApplication
        public class TestApplication {
            public static void main(String[] args) {
                SpringApplication.run(TestApplication.class, args);
            }
        }
    ```
    - `HelloController.java`
    ```java
        @Autowired
        private DataSource dds;
    ```


- **java配置**注入
    - `application.properties`
    ```properties
        jdbc.driverClassName=com.mysql.jdbc.Driver
        jdbc.url=jdbc:mysql://127.0.0.1:3306/items
        jdbc.username=root
        jdbc.password=123456
    ```
    - `JavaProperties.java`
    ```java
        @ConfigurationProperties(prefix = "jdbc")
        public class JdbcProperties {
            private String url;
            private String driveClassName;
            private String username;
            private String password;
            // getter, setter
        }
    ```
    - `JdbcConfig.java`
    ```java
        @Configuration
        @EnableConfigurationProperties(JdbcProperties.class)// 启用资源配置读取类
        public class JdbcConfig {
            @Autowired
            private JdbcProperties jp;

            @Bean
            public DataSource dataSource() {
                DruidDataSource dds = new DruidDataSource();
                dds.setUrl(this.url);
                dds.setDriverClassName(this.driveClassName);
                dds.setUsername(this.username);
                dds.setPassword(this.password);
                return dds;
            }
        }
    ```    

- 构造方法注入
    - `JdbcConfig.java`
    ```java
        @Configuration
        @EnableConfigurationProperties(JdbcProperties.class)// 启用资源配置读取类
        public class JdbcConfig {
            private JdbcProperties jp;

            public JdbcConfig (JdbcProperties jp) {// 2.构造方法注入, 
                this.jp = jp;
            }

            @Bean
            public DataSource dataSource() {
                DruidDataSource dds = new DruidDataSource();
                dds.setUrl(this.url);
                dds.setDriverClassName(this.driveClassName);
                dds.setUsername(this.username);
                dds.setPassword(this.password);
                return dds;
            }
        }
    ``` 
    
- 方法形参的方式注入
    - `JdbcConfig.java`
    ```java
        @Configuration
        @EnableConfigurationProperties(JdbcProperties.class)// 启用资源配置读取类
        public class JdbcConfig {

            @Bean
            public DataSource dataSource(JdbcProperties jp) {// 3.方法形参注入
                DruidDataSource dds = new DruidDataSource();
                dds.setUrl(jp.getUrl());
                dds.setDriverClassName(jp.getDriveClassName());
                dds.setUsername(jp.getUsername());
                dds.setPassword(jp.getPassword());
                return dds;
            }
        }
    ``` 

#### controller
- service.port
- static
- interceptor    

#### mapper
- jdbc
    - `spring-boot-starter-jdbc` 内置连接池 `Starter for using JDBC with the HikariCP connection pool`
    - 
- mybatis
- transaction