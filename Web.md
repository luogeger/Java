

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




# Redis

- 概述
- 特点
- 应用
- 安装
- 使用

### 数据类型

- String
    - set
    - get 
    - del
    - mset
    - mget
    
- hash 
    - hget
    - hset 
    - del
    - hmset
    - hmget
    - hgetall
    - hkeys
    - hvals

- list
    - lpush
    - rpush
    - lpop
    - rpop
    - lrange key start end
    - lrange key 0 -1
    - index key 2

- set
    - sadd
    - smembers key
    - srem
    - scard

- sorted set  

### 通用命令

- keys pattern
- del key1 key2...
- exists key
- type key

### Jedis
> 通过Jedis用Java代码连接Redis














    