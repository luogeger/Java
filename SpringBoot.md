# Lucene

> 文档列表

> 倒排索引列表


#### 创建索引

- `Document` 文档类 
    - 文档对象，是一条原始的数据

- `Field` 字段类 
    - 一个Document中可以有很多个不同的字段，每一个字段都是一个Field类的对象。一个Document中的每个字段类型是不确定的，   所以Field类就提供了不同的子类
    - Field子类一定会被创建索引，但是不一定会被存储。需要同过构造函数中的参数`Store`来指定
    - 索引 -> 分词 -> 存储
    - `TextField` 既创建索引，又会被分词
    - `StringField` 只创建索引，不会被分词，不分词会造成整个字段作为一个词条，除非用户完全匹配，否则搜索不到。
    - `StoreFiled` 一定会存储，一定不会创建索引，但是可以创建各种数据类型的字段。 == `url`

- `Directory` 目录类 
    - 指定索引存储的位置
    - `FSDirectory` 文件系统目录，索引库指向本地磁盘，速度慢，安全
    - `RAMDirectory` 内存目录，索引库保存在内存，速度快，不安全

- `Analyzer` 分词器类    
    - `IKAnalyzer` 中文分词器
    ```xml
        <dependency>
            <groupId>com.janeluo</groupId>
            <artifactId>ikanalyzer</artifactId>
            <version>2012_u6</version>
        </dependency>
    ```
    - 扩展词典和停用词典
        - `ext.dic`
        - `IKAnalyzer.cfg.xml`
        - `stopword.dic`

- `IndexWriterConfig` 写出器配置类

- `IndexWriter` 写出类

#### 查询索引

#### 修改索引

#### 删除索引

#### 高级使用

- 高亮显示
- 排序
- 分页
- 得分算法


```java
public class CRUD {
    @Test
    public void createTest () throws IOException {
        Document doc = new Document();

        doc.add(new StringField("id", "1", YES));
        doc.add(new TextField("title", "有事儿漂流瓶联系", YES));

        Directory dir = FSDirectory.open(new File("indexDir"));

        //Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();// 中文分词器

        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, analyzer);

        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);// 每次都清空文档列表
        IndexWriter indexWriter = new IndexWriter(dir, conf);

        indexWriter.addDocument(doc);
        indexWriter.commit();
        indexWriter.close();

    }

    @Test// 批量创建索引
    public void test2 () throws IOException {
        List<Document> docs = new ArrayList<>();
        Document doc1 = new Document();
        doc1.add(new LongField("id", 1, YES));
        doc1.add(new TextField("title", "谷歌地图之父跳槽facebook", YES));
        docs.add(doc1);

        Document doc2 = new Document();
        doc2.add(new LongField("id", 2, YES));
        doc2.add(new TextField("title", "谷歌地图之父加盟FaceBook", YES));
        docs.add(doc2);

        Document doc3 = new Document();
        doc3.add(new LongField("id", 3, YES));
        doc3.add(new TextField("title", "谷歌地图创始人拉斯离开谷歌加盟facebook", YES));
        docs.add(doc3);

        Document doc4 = new Document();
        doc4.add(new LongField("id", 4, YES));
        doc4.add(new TextField("title", "谷歌地图之父跳槽Facebook与Wave项目取消有关", YES));
        docs.add(doc4);

        Document doc5 = new Document();
        doc5.add(new LongField("id", 5, YES));
        doc5.add(new TextField("title", "谷歌地图之父拉斯加盟社交网站Facebook", YES));
        docs.add(doc5);

        Directory dir = FSDirectory.open(new File("indexDir"));
        Analyzer ik = new IKAnalyzer();
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, ik);
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter writer = new IndexWriter(dir, conf);
        writer.addDocuments(docs);
        writer.commit();
        writer.close();

    }


    public void queryTest (Query query) throws Exception {
        Directory dir = FSDirectory.open(new File("indexDir"));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        //QueryParser parser = new QueryParser("title", new IKAnalyzer());
        //Query query = parser.parse("谷歌地图之父拉斯");

        TopDocs topDocs = searcher.search(query, 7);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for (ScoreDoc docObj : scoreDocs) {
            float docScore = docObj.score;// 文档得分
            int docID = docObj.doc;// 取出文档编号
            Document doc = reader.document(docID);// 根据编号找文档

            System.out.println("  ["+ docID +"]  "+ docScore);
            System.out.println(doc.get("id") +" >> "+ doc.get("title"));
        }
    }

    @Test// 单一查询解析器
    public void test3 () throws Exception {
        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        Query query = parser.parse("拉斯");
        queryTest(query);
    }

    @Test// 多字段查询
    public void test4 () throws Exception {
        MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[]{"id", "title"}, new IKAnalyzer());
        Query query = parser.parse("3");
        queryTest(query);
    }

    @Test// 自定义查询对象，通过Query的子类，直接创建查询对象，实现高级查询
    public void test5 () {

    }

    // 索引搜索对象，执行搜索功能， IndexSearcher依赖IndexReader类，实现快速搜索、排序、打分
    @Test// Term: 词条查询， 词条是不能再分词的单位，用于不可分割的字段查询，要求字段的类型必须是字符串
    public void test6 () throws Exception {
        TermQuery query = new TermQuery(new Term("id", "1"));
        queryTest(query);
    }

    @Test// WildcardQuery: 通配符查询， ？表示任意一个字符，*表示任意多个任意字符
    public void test7 () throws Exception {
        Query query = new WildcardQuery(new Term("title", "*斯*"));
        queryTest(query);
    }

    @Test// FuzzyQuery: 模糊查询，最大的编辑距离不能超过2
    public void test8 () throws Exception {
        FuzzyQuery query = new FuzzyQuery(new Term("title", "aave"));
        queryTest(query);
    }

    @Test// NumericRangeQuery: 数值范围查询，可以用来对非String类型的ID进行精确的查找
    public void test9 () throws Exception {
        Query query = NumericRangeQuery.newLongRange("id", 1L, 3L, true, true);
        queryTest(query);
    }

    @Test// BooleanQuery: 组合查询 Occur.MUST == Occur.SHOULD == Occur.MUST_NOT
    public void test10 () throws Exception {
        Query q1 = NumericRangeQuery.newLongRange("id", 1L, 3L, true, true);
        Query q2 = NumericRangeQuery.newLongRange("id", 3L, 4L, true, true);

        BooleanQuery query = new BooleanQuery();
        query.add(q1, BooleanClause.Occur.MUST);
        query.add(q2, BooleanClause.Occur.MUST);

        queryTest(query);
    }

    // 修改索引
    // a: lucene修改功能底层会先删除，再添加
    // b: 修改功能会根据Term进行匹配，所有匹配到的都会被删除，不安全
    // c: 所以，一般修改的时候，会根据唯一不重复字段进行匹配修改，(ID)
    // d: 但是，词条搜索要求ID必须是字符串，如果不是这个方法就不能用
    // 如果ID非数值类型，不能直接修改，可以手动删除(数值范围查询锁定ID)，再添加
    @Test
    public void test11 () throws Exception{
        Directory directory = FSDirectory.open(new File("indexDir"));
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,new IKAnalyzer());
        IndexWriter writer = new IndexWriter(directory,config);

        Document document = new Document();
        document.add(new StringField("id","1", YES));
        document.add(new TextField("title","修改id为1的词条", YES));
        //底层先根据词条查询数据，删掉；再添加
        writer.updateDocument(new Term("id","5"),document);
        writer.commit();
        writer.close();
    }

    // 删除索引
    // a: 根据ID精确删除
    // b: 如果是用Term删除，要求ID是字符串类型
    @Test
    public void test12 () throws Exception{
        Directory directory = FSDirectory.open(new File("indexDir"));
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,new IKAnalyzer());
        IndexWriter writer = new IndexWriter(directory,config);

        Query query = NumericRangeQuery.newLongRange("id", 2L, 2L, true, true);
		writer.deleteDocuments(query);

		//writer.deleteDocuments(new Term("id", "1"));//词条删除, == 字符串
        //writer.deleteAll();// 删除所有
        writer.commit();
        writer.close();
    }


    @Test// 高亮显示
    public void highLight () throws Exception {
        Directory directory = FSDirectory.open(new File("indexDir"));
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);


        QueryParser parser = new QueryParser("title",new IKAnalyzer());
        Query query = parser.parse("谷歌地图");


        Formatter formatter = new SimpleHTMLFormatter("<em>","</em>");//创建格式化工具
        Scorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter,scorer);//创建高亮显示工具

        TopDocs topDocs = searcher.search(query, 7);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;// 文档列表
        System.out.println("总共数据: "+ topDocs.totalHits);
        for (ScoreDoc docObj : scoreDocs) {
            float docScore = docObj.score;// 文档得分
            int docID = docObj.doc;// 文档编号

            Document doc = reader.document(docID);// 文档
            String title = doc.get("title");// 索引title字段
            String highTitle = highlighter.getBestFragment(new IKAnalyzer(), "title", title);

            System.out.println("id = "+ docID +"  score = "+ docScore +"");
            System.out.println(highTitle);
        }

    }
}// end
```


# Solr

- `Lucene`
    - 用于 **全文搜索**的开源程序库，由Apache基金会支持和提供
    - 提供简单强大的应用程序接口(API), 在Java开发环境里Lucene是成熟免费的开源代码工具
    - Lucene并不是现成的搜索引擎产品，但是可以用来制作搜索引擎产品
- `Solr`
    - **企业级搜索应用服务器** 对外提供API接口。用户通过http请求像搜索服务器提交相应格式的文件生成索引，也可以通过http访问提出查找请求。
    - **基于Lucene的全文搜索服务器** 同时进行了扩展，提供比Lucene更为丰富的查询语言，实现了可配置、可扩展，并且对查询性能进行了优化，还提供了完善的功能管理界面

#### jetty启动Solr服务
- 进入`solr-4.10.2\example`目录执行`java -jar start.jar`
- 访问`127.0.0.1:8983/solr`

#### tomcat启动Solr服务
- `solr.war`在`tomcat`启动需要额外的jar包, 在`jetty`运行不需要
- 在`tomcat\bin\catalina.bat`指定`solr`的目录：
    - 配置`set "JAVA_OPTS=-Dsolr.solr.home=D:\dev_src\solr-4.10.2\example\solr"`

#### Solr管理页面

#### Solr的Core
> 在 **Solr** 中，每一个 **Core** 代表一个索引库，里面包含索引数据及其配置信息。 **Solr** 中可以拥有多个 **Core**，也就是同事管理多个索引库，就像 **Mysql**中的 **database**

```bash
solr
├── contrib
├── core                      
│   ├── conf                  # 配置文件目录
│   │   ├─ schema.xml          # 字段及字段约束信息
│   │   ├─ solrconfig.xml      # 索引库的相关配置
│   │   └──...  
│   ├── data                  # 索引数据目录
│   ├── core.properties       # Core的自身属性
│   └── README.txt
└── dist
```

- `core.properties`
    - 记录当前 **Core** 的名称、索引位置、配置文件名称等信息，也可以不写，一般要求`name`值和文件夹名称一致
        - `name=core01`

- `schema.xml`

> Solr会提前对文档中的字段进行定义，并且在`schema.xml`中对这些字段的属性进行约束，例如：字段的数据类型、是否索引、是否存储、是否分词....

    ```xml
        <?xml version="1.0" encoding="UTF-8" ?>
        <schema name="example" version="1.5">
            <field name="_version_" type="long" indexed="true" stored="true"/>
            <field name="_root_" type="string" indexed="true" stored="false"/>
            <field name="id" type="string" indexed="true" stored="true" required="true" multiValued="false" /> 
            <field name="title" type="text_ik" indexed="true" stored="true" multiValued="false"/>
            <field name="text" type="text_ik" indexed="true" stored="false" multiValued="true"/>

            <dynamicField name="*_i"  type="int"    indexed="true"  stored="true"/>
            <uniqueKey>id</uniqueKey>

            <fieldType name="string" class="solr.StrField" sortMissingLast="true" />
            <fieldType name="boolean" class="solr.BoolField" sortMissingLast="true"/>
            <fieldType name="int" class="solr.TrieIntField" precisionStep="0" positionIncrementGap="0"/>
            <fieldType name="float" class="solr.TrieFloatField" precisionStep="0" positionIncrementGap="0"/>
            <fieldType name="long" class="solr.TrieLongField" precisionStep="0" positionIncrementGap="0"/>
            <fieldType name="double" class="solr.TrieDoubleField" precisionStep="0" positionIncrementGap="0"/>
            <fieldType name="text_ik" class="solr.TextField">
                <analyzer class="org.wltea.analyzer.lucene.IKAnalyzer"/>
                <!-- <tokenizer class="solr.WhitespaceTokenizerFactory"/> -->      
            </fieldType>
        </schema>
    ```

- `sclrconfig.xml`
    - 第三方扩展插件的jar包, 相对路径是相对于索引库的路径
    ```xml
        <lib dir="../contrib/extraction/lib" regex=".*\.jar" />
        <lib dir="../dist/" regex="solr-cell-\d.*\.jar" />

        <lib dir="../contrib/clustering/lib/" regex=".*\.jar" />
        <lib dir="../dist/" regex="solr-clustering-\d.*\.jar" />

        <lib dir="../contrib/langid/lib/" regex=".*\.jar" />
        <lib dir="../dist/" regex="solr-langid-\d.*\.jar" />

        <lib dir="../contrib/velocity/lib" regex=".*\.jar" />
        <lib dir="../dist/" regex="solr-velocity-\d.*\.jar" />
    ```
    - `<requestHandler/>`标签
    ```xml
        <requestHandler name="/select" class="solr.SearchHandler">
            <lst name="defaults">
                <str name="echoParams">explicit</str>
                <int name="rows">10</int>
                <str name="df">title</str>
            </lst>
        </requestHandler>
    ```
    - `<initParams/>`
    - `<updateHandler/>`
    - `<query/>`
    - `<requestDispatcher/>`
    - `<updateProcessor/>`
    - `<updateProcessorChain/>`

#### Solr引入Mysql数据
- 在`core\conf\solrconfig.xml`导入插件依赖
    ```xml
    <lib dir="../dist/" regex="solr-dataimporthandler-\d.*\.jar" />
    <requestHandler name="/import" class="org.apache.solr.handler.dataimport.DataImportHandler">
        <lst name="defaults">
            <str name="config">db-data-config.xml</str>
        </lst>
    </requestHandler>
    ```

- 连接数据库配置文件`db-data-config.xml`, 放在在`core\conf`目录下
    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>  
    <dataConfig>
        <dataSource type="JdbcDataSource"
                driver="com.mysql.jdbc.Driver"
                url="jdbc:mysql://localhost:3306/solr" 
                user="root"
                password="123456"/>

        <document>
            <entity name="item" query="select id,title,price from items"><!-- 执行sql语句 -->
                <field column="id" name="id"/><!-- 如果字段与数据库列不一致，可以通过<field>来设置 -->
            </entity>
        </document>
    </dataConfig>   
    ```

- 在`core\conf\schemal.xml`设置Slolr索引库字段信息匹配Mysql数据库的字段类型
    ```xml
        <field name="id" type="long" indexed="true" stored="true" required="true" multiValued="false" /> 
        <field name="title" type="text_ik" indexed="true" stored="true" multiValued="false"/>     
        <field name="price"  type="long" indexed="true" stored="true"/>
    ```

- 数据库连接驱动jar包, 放在`tomcat\webapps\solr\WEB-INF\lib`目录下
    - `mysql-connector-java-5.1.32.jar`

- tomcat启动报错：`Error initializing QueryElevationComponent`
    - 因为修改了`id`字段的类型，`core\conf\solrconfig.xml`配置文件中的组件出错，注释即可
    ```xml
    <searchComponent name="elevator" class="solr.QueryElevationComponent" >
        <!-- pick a fieldType to analyze queries -->
        <str name="queryFieldType">string</str>
        <str name="config-file">elevate.xml</str>
    </searchComponent>
    ```

     
# SolrCloud

#### 单点服务器的问题
- 高并发，tomcat 200左右，最大连接数限制，导致无法承载高并发，
    - 访问同一个服务器，但是是不同的模块
    - 同一瞬间，触发同一个操作
- 单点故障
    - 停电，断网，死机，重启...
- 大数据
    - 单表的数据量很大，都是几千万的数据量

```
    最初提高计算机性能的主要方法是通过提高CPU主频和总线带宽，
    但是，这一方法对系统性能的提升是有限的。
    后来通过增加CPU个数和内存容量来提高性能，出现向量机，对称多处理机(SMP)...
    但是，当CPU的个数超过一定阈值，多处理机系统的可扩展性就变的极差。
    主要瓶颈在CPU访问内存的带宽并不能随着CPU数量的增加而有效增加。
    与SMP相反，集群系统的性能随着CPU数量的增加几乎是线性变化。
```

#### 集群
> 解决高并发，单点故障转移。集群是将多台服务器集中在一起，每台服务器都 **实现相同的业务**，做相同的事情。但是每台服务器并不是缺一不可，存在的作用是缓解并发压力和故障转移问题。可以使用廉价的硬件构造高性能系统，实现高扩展、高性能、低成本、高可用。

- 伸缩性 `Scalability`
- 高性能 `High Performance`
- 高可用性 `High availability`
- 负载均衡 `Load balancing`

**实现**
- 每个`tomcat` **应用服务器**的访问地址不同(`ip`), 需要同一地址
- tomcat挂载到`NginX` **web服务器下面**，`NignX`的负载均衡有轮询策略
    -  但是也有问题，因为服务器有差异，处理请求的能力不一样。(线程池算法)
    - `NginX`能够承受有限`5万`
- 此时，每个tomcat的功能都是一样的，解决了高并发，大数据，单点故障的问题，但是有**NginX**的单点故障的问题，NginX需要备份，通过**keep-alived**保持联系，此时NginX2处于**冷备份**。
    - lvs 解决热备份也down机的问题， 只接受请求，由NginX响应给用户

**弊端**
- 系统庞大难以维护
- 所有的功能模块耦合在一起，无法对针对单个模块进行优化
- 每个功能模块的压力是不一样的，但是功能模块集中在一起，无法对高并发模块进行水平扩展

#### 分布式架构
- 分布式：把多台服务器集中到一起，每台实现总体业务中的 **不同功能**，从业务功能家督大幅度的提高效率，缓解服务器的访问和存储压力
- 如果某台服务器故障，网站对应的功能缺失，也可能导致依赖功能或全部功能丧失
- 因此，分布式系统需要运行在集群服务器中，甚至分布式系统的每个不同子任务都可以部署集群
                   

#### 分布式集群架构

#### 代理技术
- 正向代理 `Forward Proxy`
    - 访问本来无法访问的服务器
    - 加速访问
    - 缓存作用
    - 客户端访问授权
    - 隐藏访问者的行踪


- 反向代理 `Reverse Proxy`
    - 保护和隐藏原始资源服务器
    - 负载均衡
        - 当反向代理服务器不止一个的时候，可以做成集群，当很多用户访问资源服务器的时候，让不同的代理去响应不同的用户，然后发送不同的资源。而且反向代理和正向代理一样拥有缓存的作用，可以缓存资源服务器的资源，而不是每次都要像资源服务器请求数据，特别是一些静态资源，比如图片和文件


#### Zookeeper分布式协调服务

**集群结构**
- Zookeeper
    - `leader` 只能有一个，
    - `follower` 多个，
- 全局数据一致
- 数据更新原子性
- 实时性：**在一定时间范围内**， 用户能读到最新数据
- 半数机制

**leader选主机制**
- 全新集群
- 非全新集群

**Zookeeper作用**
- 命名服务
- 配置维护
- 集群选主
- 11:20 yum install lr
- 11:59
- jsp
- scr

# SpringBoot

> SpringBoot是基于Spring来构建，可以理解是对Spring的简化，快速构建Spring应用。SpringCloud是利用SpringBoot简化构建分布式应用。


#### overview
- spring体系的一部分，创建独立的spring应用程序
- 内嵌tomcat，jetty，undertow，不需要打包成war包部署，自动配置spring和第三方库
- 启动器依赖，不会有版本冲突，简化配置文件


#### 启动器starter
- 启动器的父工程
- 引导类
    - `@EnableAutoConfiguration`
    - `@ComponentScan` :开启组件扫描
    - `@SpringBootApplication` ：相当于上面两个注解 + `@SpringBootCongfiguration`
        - `@SpringBootCongfiguration` ：相当于`@Configuration`
   


#### default_config    
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

- `Eureka` 
    - `Eureka Server`
    - `Eureka Client`
    - `Eureka 高可用`
    - `服务发现机制`
- `Config` 同一配置中心
    - `Config Server`
    - `Config Client`
    - `Spring Cloud Bus` 结合`RabibtMQ`实现自动刷新
- `Ribbon` 服务通信，负载均衡
    - `RestTemplate` 
    - `Feign`
    - `分析源码，了解底层`
- `Hystrix` 微服务API
    - 动态路由，校验
- `Zuul`
    - 熔断机制

**容器编排，服务追踪**
- `docker`
- `RANCHER`    

### Eureka
- `/jʊ'rikə/`
- 服务注册中心
    - `Eureka`的服务端应用，提供服务**注册和发现**功能
- 服务提供者
    - 提供服务的应用，可以是`SpringBoot`应用，也可以是其他技术实现，只要对外提供的Rest风格的服务就行。
- 服务消费者
    - 消费应用从注册中心获取服务列表，得知每个服务提供者的信息，知道去哪里调用服务

> **provider_service: 服务提供者**

> **consumer_service: 服务消费者**

> **eureka_service: 服务注册中心**

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

### Ribbon
- Ribbon是Netflix发布的负载均衡器，助于控制HTTP合同TCP客户端的行为。为Ribbon配置服务提供者地址列表以后，可以基于算法，自动地帮助服务消费者去请求。Ribbon默认提供了一些算法，轮询、随机...

- 在`public RestTemplate restTemplate() {`上注解`@LoadBalanced` 就是开启了ribbon的负载均衡
- `this.restTemplate.getForObject("http://provider_service/user/" + id, User.class)`
- 测试
    - 启动多个服务提供者
    - ip，端口的配置，在`provider-service`
        - `instance: prefer-ip-address: false`,  `instance: ip-address: 192.168.150.1`
    - 随机的测试，在`consumer.yml`配置
        - ```yaml
            provider-service: #提供者的id
                ribbon:
                    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
        ```        

### Hystrix
- Hystix是Netflix开源的一个延迟和容错库，用于隔离访问远程服务、第三方库，防止出现级联失败。

> **熔断现象**：一个请求需要很多个微服务协同完成，如果卡在某个服务会造成内存溢出。需要采取**线程隔离**、**服务降级**的技术手段来解决。熔断的触发有2中情况，一种线程池满了，另一种请求超时。

- 如果一个服务经常熔断，那么这个服务就是故障服务，Hystrix会主动切断服务，处于`open`状态，**5秒**之内不再接受请求，之后处于半开状态`half open`，然后放行部分请求，查看还能不能连接上服务，如果能连上就进入闭合状态`closed`, 如果还连接又回到`open`状态，周而复始。
    - `closed`: 请求正常访问
    - `open`: 请求都会被降级。Hystrix会对请求计数，当一定时间请求的失败比例超过阈值，就会触发熔断，进入`open`状态。默认失败比例的阈值是50%，请求次数不低于20次。
    - `half open`: 

- 熔断的参数
    - ```xml
        requestVolumeThreshold：触发熔断的最小请求次数，默认20
        errorThresholdPercentage：触发熔断的失败请求最小占比，默认50%
        sleepWindowInMilliseconds：休眠时长，默认是5000毫秒
    ```    


> **服务降级**：优先保证核心服务，而非核心服务不可用或弱可用。用户的请求故障时，不会被阻塞，更不会无休止的等待或看到系统崩溃，至少可以看到一个执行结果(返回友好提示)。服务降级虽然会导致**请求失败**，但是不会导致阻塞，最多对这个依赖服务对应的线程池中的资源造成影响，**最主要**的是不会对其他服务有影响，更快的释放资源，不至于宕机。

- 消费方处理请求超时
- `依赖`
```xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>
```
- `覆盖默认配置`
- `添加注解`: 在`ServiceConsumerApplication.java`上添加`@EnableCircuitBreaker`, 开启熔断注解 
    - 组合注解：`@SpringCloudApplication`
- 首先，熔断是对远程调用进行熔断，    

> **线程隔离**：

### Feign
- Feign是Netflix开发的声明式、模板化的HTTP客户端。Feign可以把Rest请求进行隐藏，伪装成SpringMVC的Controller一样。不用拼接URL、参数等操作，一切可以交给Feign去做。
- Spring Cloud对Feign进行了增强，使得Feign支持了SpringMVC注解，并且整理Ribbon和Eureka，让Feign的使用更加方便
- Spring Cloud中使用Feign非常简单，创建一个接口，并且在接口上添加一些注解，代码就完成了
- 不需要`RestTemplate` == 远程调用，也不需要`@DefaultProperties` == 熔断功能

> **远程访问** 消费者从服务列表获取服务, 

- 依赖
- 注解`@EnableFeignClients`， 在引导类加
- 覆盖配置
- 定义**接口**，添加注解`@FeignClient("[服务名]")` ，接口里面定义的方法不用方法体，熔断方法来重写
- 通过注入接口的动态代理对象使用，底层也是`RestTemplate`

> Feign集成了Hystrix, 默认是关闭的

- `feign.hystrix.enabled: true`, 开启Feign的负载均衡功能，只需要继承
- Feign接口添加实现类，给哪个方法添加熔断方法，就实现哪个方法，
- 产生关联，添加注解`@FeignClient(value = "[服务名]", fallback = "[clazz]")`

### Zuul


# FastDFS

> 分布式文件系统（Distributed File System）是指文件系统管理的物理存储资源不一定直接连接在本地节点上，而是通过计算机网络与节点相连。


# ElasticSearch

- 介绍
- 安装
    - **不支持 root 用户**
    - 在`youyou`用户的权限下上传`elastic`，在`root`用户下修改tar包的权限
        - `chown youyou:youyou elasticsearch-6.3.0.tar.gz`
        - `chmod 777 elasticsearch-6.3.0.tar.gz`
    - 解压：切换到`youyou`用户下解压
        - `tar -zxvf elasticsearch-6.3.0.tar.gz`
    - 重命名，任何地方都可以用
- 修改配置
    - `jvm.options` 内存占用太多了，调小一些
        ```xml
        -Xms512m
        -Xmx512m
        ```
    - `elasticsearch.yml`
        - 日志目录
            - `path.logs:/home/youyou/elasticsearch/logs`
        - 数据保存在某个目录(索引库)
            - `path:data:/home/youyou/elasticsearch/data`
        - **mkdir data** 一定记住要创建这个目录，
        - 默认情况只允许本地连接
            - `network.host: 0.0.0.0`(允许所有ip访问)
- 运行
    - `bin`目录执行`./elasticsearch`, 会有**4**个报错

```xml
    [o.e.b.JNANatives         ] unable to install syscall filter: 
    java.lang.UnsupportedOperationException: seccomp unavailable: requires kernel 3.5+ with CONFIG_SECCOMP and CONFIG_SECCOMP_FILTER compiled in ......


    ERROR: [4] bootstrap checks failed
    [1]: max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536]
    [2]: max number of threads [1024] for user [youyou] is too low, increase to at least [4096]
    [3]: max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
    [4]: system call filters failed to install; check the logs and fix your configuration or disable system call filters at your own risk
```
- 处理运行报错
    - `4` 过滤器需要高版本的内核，所以禁用。
        - `elasticsearch.yml`禁用配置，最下方添加
            - `bootstrap.system_call_filter: false`
    - `1` 文件权限不足, 当前用户是`youyou`不是`root`
        - **切换到root用户**，修改配置文件
            - `vim /etc/security/limits.conf`, 添加一下内容
            ```yaml
                * soft nofile 65536
                * hard nofile 131072
                * soft nproc 4096
                * hard nproc 4096
            ```
    - `2` 线程数量不够        
        - `vim /etc/security/limits.d/90-nproc.conf`
            - `soft nproc 1024` 修改为 `4096`
    - `3` 虚拟内存不够: `vm.max_map_count`限制一个进程可以拥有的VMA(虚拟内存区域)的数量
        - `vim /etc/sysctl.conf` 添加 `vm.max_map_count=655360` 执行 `sysctl -p` 刷新
    - **切换youyou用户**再次执行， 部分用户修改完毕需要重启终端，否则配置无效
    - 启动很慢，成功以后`publish_address {192.168.150.129:9200}, bound_addresses {[::]:9200}`
        - `9200`：独立端口，`9300`：云服务端口

### Kibana   
- 修改配置，`config\kibana.yml`
    - `elasticsearch.url: "http://192.168.150.129:9200"`
- 双击`bin\kibana.bat`启动

- 测试分词
    - ```json
        POST _analyze
        {
            "text":     "我是中国人"
        }
    ```
    - 发现不能中文分词
- 安装`ik-analysis`
    - 复制到`/home/youyou/elasticsearch/plugins`目录下
    - `unzip elasticsearch.6.3.0.zip -d ik-analyzer`：解压到当前新建的`ik-analyzer`目录下
        - **一定要删除解压包**, 再重新启动
    - 测试ik分词器
    - ```json
        POST _analyze
        {
            "analyzer": "ik_max_word",
            "text":     "我是中国人"
        }
    ```

### API

- 索引库 `indices`
    - 类型 `type`
        - 文档 `document`
            - 字段 `field`

> 索引库

- 创建索引库: 请求方式，索引库名，json格式
    - ```json
        PUT second
        {
        "settings": {
            "number_of_shards": 1,
            "number_of_replicas": 0
        }
        }
    ```

- 查看索引库：`GET second`
    - 查看所有的索引库：`GET *`
- 删除索引库：`DELETE second`
    -     



> 映射配置

映射就是定义文档的过程，文档包含哪些字段，字段是否索引，是否分词，是否保存...



> 

> 

### 高级查询


# RabbitMQ

- simple
- work
- fanout
- direct
- topic