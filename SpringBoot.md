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
    - mysql驱动
    - jdbc启动器
        - `spring-boot-starter-jdbc` 内置连接池 `Starter for using JDBC with the HikariCP connection pool`, 只需要配置内置的jdbc启动器就可以了
        - `application.properties` 配置连接信息 
- mybatis
    - mybatis启动器
    - 通用Mapper启动器：pojo使用了通用mapper的注解
    - `UserMapper.java`继承通用Mapper`Mapper<User>`
    - 加注解，实现接口扫描 `org.apache.ibatis.annotations.Mapper`

#### service    
- transaction









# SpringCloud
### Eureka
- `/jʊ'rikə/`
- 服务注册中心
    - `Eureka`的服务端应用，提供服务 **注册和发现**功能
- 服务提供者
    - 提供服务的应用，可以是`SpringBoot`应用，也可以是其他技术实现，只要对外提供的Rest风格的服务就行。
- 服务消费者
    - 消费应用从注册中心获取服务列表，得知每个服务提供者的信息，知道去哪里调用服务

#### provider_service: 服务提供者

#### consumer_service: 服务消费者

#### eureka_service: 服务注册中心

- 导入依赖
- `application.yml` 覆盖默认配置
    - `server.port` : `10086`
    - `spring.application.name` : `eureka_service`
    - `eureka.client.service-url.defaultZone` : `http://localhost:10086/eureka`
- `ServiceEurekaApplication.java` 添加开启服务端注解
    - `@EnableEurekaServer` 

#### 从服务中心获取服务

> **service-provider**

- `provider.pom`
    - 版本号
        - ```xml
            <spring-cloud.version>Finchley.SR2</spring-cloud.version>
        ```
    - 依赖管理
        - ```xml
            <dependencyManagement>
                <dependencies>
                    <dependency>
                        <groupId>org.springframework.cloud</groupId>
                        <artifactId>spring-cloud-dependencies</artifactId>
                        <version>${spring-cloud.version}</version>
                        <type>pom</type>
                        <scope>import</scope>
                    </dependency>
                </dependencies>
            </dependencyManagement>
        ```
    - 组件
        - ```xml
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            </dependency>
        ```
- `application.yml` 注册到服务中心
    - `spring.application.name` : `provider_service`
    - `erueka.client.service-url.defaultZone` : `http://localhost:10086/eureka`
- `ProviderApplication.java` 启动客户端功能
    - `@EnableDiscoveryClient` : `SpringCloud`

> **service-consumer**    

- 和 **provider_service**配置一样，但是消费服务要动态的从服务列表中获取服务实例
    - 在`UserController.java`使用`DiscoveryClient`类
        - `import org.springframework.cloud.client.discovery.DiscoveryClient;`
    - 获取对应的服务实例，需要知道服务名字`provider_service`
    - ```java
        @Controller
        @RequestMapping("customer/user")
        public class UserController {
            @Autowired
            private RestTemplate restTemplate;

            @Autowired
            private DiscoveryClient discoveryClient; // eureka客户端，可以获取到eureka中服务的信息

            @GetMapping
            @ResponseBody
            public User queryUserById(@RequestParam("id") Long id){
                // 根据服务名称，获取服务实例。有可能是集群，所以是service实例集合
                List<ServiceInstance> instances = discoveryClient.getInstances("provider_service");
                // 因为只有一个UserService。所以获取第一个实例
                System.out.println(id);
                ServiceInstance instance = instances.get(0);
                // 获取ip和端口信息，拼接成服务地址
                String baseUrl = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;
                User user = this.restTemplate.getForObject(baseUrl, User.class);
                return user;
            }
        }
    ```

#### 高可用的Eureka Server

> **EurekaServer**也可以是一个集群，形成高可用的**Eureka**中心

- 当**服务提供者**注册到Eureka Server集群中的某个节点时，这个节点会把服务的信息同步给集群中的每个节点，就可以实现**数据同步**，无论客户端访问到Eureka Server集群中的任意节点，都可以获取到完整的**服务列表**信息

- 重复部署同一个注册中心，只是修改端口，相互配置地址

> **服务提供者**

- 服务提供者要想EurekaServer注册服务，并且完成服务续约等工作

- **服务注册**：服务提供者在启动的时候，会检测配置属性中的`eureka.client.register-with-eureka`的值是否为`true`, 默认是`true`, 如果为`true`，就会想EurekaServer发起Rest请求，并携带自己的**元数据**信息，EurekaServer会把这些信息保存到一个双层Map结构中
    - 第一层Map的Key是**服务id**，一般是配置中的的`spring.application.name`属性值
    - 第二层Map的Key是服务的**实例id**，一般是`host`+`serviceID`+`port`, 例：`localhot:user_service:8081`
        - Value是服务的**实例对象**，就是一个服务，可以同时启动多个不同实例，形成集群

- **服务续约**：就是心跳机制，注册服务完成以后，服务提供者会维持一个心跳，就是定时想EurekaServer发起Rest请求，告诉EurekaServer，"我还活着"。这种状态称为服务续约`renew`, 有两个重要参数可以修改服务续约的行为
    - ```yaml
        eureka:
            instance:
                lease-expiration-duration-in-seconds: 90
                lease-renewal-interval-in-seconds: 30
    ```
    - `lease-renewal-interval-in-seconds` ：服务续约(renew)的间隔，默认为30秒
    - `lease-expiration-duration-in-seconds` ：服务失效时间，默认值90秒

    
默认情况下，间隔30秒服务会向注册中心发送一次心跳，证明自己还活着。如果超过90秒没有发送心跳，EurekaServer会认为这个服务宕机，会从服务列表中移除，这个两个值在生产环境不需要修改，默认就行。但是在开发环境中，时间太长，我们会关掉一个服务，会返现EurekaServer依然认为服务还活着。
    

> **服务消费者**

- **获取服务列表**，当服务消费者启动时，会检测`eureka.client.fetch-registry`的值是否为`true`, 默认是`true`。如果为`true`, 就会拉取EurekaServer的服务列表只读备份，缓存在本地。并且间隔30秒重新获取并更新数据。在开发阶段，依然设置的小点。生产环境中不需要修改。
    - ```yaml
        eureka:
            client:
                registry-fetch-interval-seconds: 5
    ```

- **服务下线**：服务正常关闭，会触发一个服务下线的Rest请求给EurekaServer，通知服务中心自己已经下线，服务中心接到通知会把该服务设置为下线状态。

- **失效剔除**：但是服务的关闭一般是非正常的，大多数是因为内存溢出，网络故障等原因导致无法工作。所以EurekaServer会开启一个定时任务，间隔60秒对所有失效的服务(超过90秒未响应)进行剔除。这个时间值在开发阶段极不方便，你重启服务了，EurekaServer需要60秒才能反应过来，所以需要重置时间值。
    - ```yaml
        server:
            eviction-interval-timer-in-ms: 10000 #单位是毫秒
    ```     

- **自我保护**：当服务未按时进行心跳续约时，EurekaServer会统计最近15分钟心跳失败的服务实例的比例是否超过了85%。因为在线上环境下，因为网络延迟等原因，心跳失败的比例很有可能超标，此时把服务从列表中剔除是不妥当的，因为服务可能并没有宕机。这时候，EurekaServer就会把当前实例的注册信息保护起来，不会剔除。在生产环境下这种方式很有效，保证了大多数服务依然可用。
    - 但是这种机制，在开发阶段会很麻烦，因此，在开发阶段需要关闭自我保护
    - `service_eureka`
    - ```yaml
        eureka:
        server:
            enable-self-preservation: false # 关闭自我保护模式（缺省为打开）
            eviction-interval-timer-in-ms: 1000 # 扫描失效服务的间隔时间（缺省为60*1000ms）
    ```

    - 当关停一个服务，会在EurekaServer面板看到一条警告
    - ```bash
        emergency! eureka may be incorrectly claiming instances are up when they're not. 
        renewals are lesser than threshold and hence the instances are not being expired judt to be safe. 
        DS replicas

        紧急！ eureka可能会错误地声称实例在不存在的情况下会被启动。 
        续订小于阈值，因此实例未过期判断为安全。
        DS副本
    ```    
