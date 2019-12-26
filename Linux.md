# linux

### Shell 
- `shell`概述
    - Shell俗称壳，用来区别于Kernel（核），是指“提供使用者使用界面”的软件（命令解析器）。
        它类似于DOS下的command和后来的cmd.exe。它接收用户命令，然后调用相应的应用程序。
-  `shell`分类
    - 图形界面shell: 通过提供友好的可视化界面，调用相应应用程序，
        如windows系列操作系统，Linux系统上的图形化应用程序GNOME、KDE等。
    - 命令行shell：通过键盘输入特定命令的方式，调用相应的应用程序，
        如windows系统的cmd.exe、Windows PowerShell，Linux系统的Bourne shell ( sh)、Bourne Again shell ( bash)等

### Shell命令


#### 基本命令
- `reboot` : 立刻重启
- `poweroff`: 立刻关机
- `history` 查看操作历史

#### 查看版本信息
- `cat /etc/issue`
- `cat /proc/version`
- `yum -y install redhat-lsb`
- `lsb_release -a`

#### 用户操作    
- `whoami` 查看当前用户
- `su - roming` 切换用户
- `useradd roming` 添加用户
- `userdel -f roming` **彻底**删除用户

#### 权限操作
- `chown roming:roming elasticsearch-6.3.0.tar.gz`
- `chmod 777 elasticsearch-6.3.0.tar.gz`

#### 管道命令
- 过滤操作，将前面的命令的输出，作为后面命令的输入
    - `ps -ef | grep mysql`, **注意：**至少有一个进程
    - `ps -ef|grep java`
    - `ps -ef|grep public`

#### 查询
- `grep -A 10  开始同步商品 huapei-backend.log` 查看关键字后10行的日志
- `tail -f info.log` 查看日志    
- `head` , `head -10 info.log`, 查看文件前几行 
- `tail` , `tail -10 info.log`, 查看文件后几行
- `tail` , `tail -f info.log`, 动态查看文件
- `cat` , `cat /etc/sudo.conf`, 查看文件全部内容
- `more`  `less` 查看文件
- `grep`: == `ctrl + f`
    - `grep to /etc/sudo.conf --color`
- 在文件中精确查找信息
    - `grep "msg" info.log`
    - `grep "msg" info.log error.log`

#### 目录操作
-  `pwd` (Print Working Directory) 
-  `mkdir` (Make Directory) 创建目录
- `rmdir` (Remove Directory) 删除文件夹，只能删除空文件夹，不常用
-  `cd` (Change Directory) 切换目录 
    - `cd /` 根目录
    - `cd ~`  home目录
    - `cd ../` 上一级
    - `cd -` 上次的目录
    - `cd /usr/local/src/java` 指定目录 
-  `ls` (List) 查看当前目录下内容，如 ls -al,“.”(表示当前目录)和“..”(表示当前目录的父目录)。
    - `ls -l` == `ll`
    - `ls -a` 查看所有目录(隐藏的也能看到)
    - `ls /usr/local` 查看指定目录
    - `mkdir /ust/local/src/mysql`
-  `mv` (move) 剪切或重命名，
    - `mv index.html ./demo/index.html`
    - `mv elasticsearch-6.3.0/ elasticsearch` 重命名
-  `cp` (copy) 复制文件
    - `cp -r bbb/ /usr/local`: 复制当前目录`bbb`以及里面的内容听到`/usr/local`
- `find` : `find /usr/local/ -name 'b'`        

#### 文件操作
- `wc` (Word Count) 字数信息统计，如 wc index.html
- `touch` 创建文件， `touch index.html`
-  `rm` (remove) 删除文件，
    - `rm index.html` 删除文件
    - `rm -rf  blog` 删除目录
    - `rm -rf *` 删除当前目录下的所有

- 压缩文件的操作
    - `.tar` 打包文件
    - `.gz` 压缩文件
    - `.tar.gz` 打包并压缩的文件、
    - 压缩 a.bxt 和 b.txt
        - `tar -zcvf ab.tar a.txt b.txt`: 压缩
        - `rm -rf *.txt` :再删除`a.txt`和`b.txt`
        - `tar -zxvf ab.tar` ： 再解压
    - `unzip elasticsearch.6.3.0.zip -d ik-analyzer` 解压到当前新建的`ik-analyzer`目录下       

- 进程
    - `ps -ef`：查看进程
    - `kill -9 1`: 强制杀死进程，`-9`== 强制， `1`==PID

- 网络端口
    - `ping`
    - `ifconfig | more` ：查看网络配置信息
    - `netstat -an | grep 8080`: 查看端口  
    - `/etc/init.d/iptables status` 查看打开的端口
    - `vim /etc/sysconfig/iptables` 增加端口
        - `service iptables restart` 再重启
    - `service iptables status`: 查看防火墙状态
        - `service iptables stop`: 一次性关闭
        - `chkconfig iptables off` 永久关闭  
        - `chkconfig iptables on` 永久开启
    - 查看有哪些自启动服务        
    
- 其他
    - `ifconfig eth0 up` : 权限不够
    - `chkconfig --level 2345 network on` : 网络服务在系统启动级别是2345时默认启动


- 时间
    - `hwclock` 硬件时间
    - `date` 系统时间
    - 设置时间
        - `yum -y install ntp ntpdate`
        - `ntpdate cn.pool.ntp.org`

- `lrzsz`


# CentOS 7


## install

### jdk
    - 先卸载Linux自带的jdk
        - `java -version` 
        - `rpm -qa | grep java`
        - `rpm -e --nodeps java-1.7.0-openjdk-1.7.0.45-2.4.3.3.el6.x86_64`
        - `rpm -e --nodeps java-1.6.0-openjdk-1.6.0.0-1.66.1.13.0.el6.x86_64`
        - `mkdir /usr/local/src/jdk`： 新建文件夹
        - `tar -zxvf jdk-8u181-linux-x64.tar.gz` : [解压] 要在jdk的目录下执行命令
        - `vim /etc/profile`：
        - ```
            // 复制注意目录
            #set java environment
        	JAVA_HOME=/usr/local/src/jdk/jdk1.8.0_181
            CLASSPATH=.:$JAVA_HOME/lib.tools.jar
            PATH=$JAVA_HOME/bin:$PATH
            export JAVA_HOME CLASSPATH PATH
        ```
        - `source /etc/profile`
        - `java -version`

### mysql
    - `mkdir /usr/local/src/mysql5.6`：创建目录
    - `tar -xvf MySQL-5.6.34-1.rhel5.x86_64.rpm-bundle.tar` :[解压]  要在mysql5.6目录下执行命令
    - `rpm -qa | grep mysql` ：**注意：** 解压之后，先不要安装，把自带的Mysql删除再安装，先执行命令检测有没有安装
        - `rpm -e --nodeps  mysql-libs-5.1.71-1.el6.x86_64`: 然后在删除
    - `rpm -ivh MySQL-server-5.6.34-1.rhel5.x86_64.rpm`：安装服务端
    - `rpm -ivh MySQL-client-5.6.34-1.rhel5.x86_64.rpm`：安装客户端
    - `service mysql status` ：查看mysql状态，如果失败是因为没有启动
        - `service mysql start`：启动Mysql        
        - `service mysql status`: 再查看状态
    - mysql安装的时候，系统自动生成了密码，存放在`/root/.mysql_sceret`, 先修改密码
        - `mysql -uroot -p`
        - `SET PASSWORD = PASSWORD('123456');`    
    - 自动启动设置
        - `chkconfig --add mysql`：加入到系统服务
        - `chkconfig mysql on`：设置为自动启动
        - `chkconfig`：查看是不是自启状态，
    - 开启远程访问 
        - `mysql -uroot -p`先登陆           
        - `grant all privileges on *.* to 'root' @'%' identified by '123456'; `：设置远程访问，`'123456'`是密码
        - 退出mysql，分别执行以下命令
            - `/sbin/iptables -I INPUT -p tcp --dport 3306 -j ACCEPT`
            - `/etc/rc.d/init.d/iptables save` : 会看到 [确定]
            - `/etc/rc.d/init.d/iptables save` ：会看到 [表格： filter]


### redis   
    - 先安装`gcc`
        - `yum install gcc-c++`
        - `gcc --version` 检查有没有安装好
    - `mkdir /usr/local/src/redis`：创建目录，并上传压缩包
        - `tar -zxvf redis-3.0.0.tar.gz`: 解压之后会有一个`redis-3.0.0`的源码目录
        - 解压时候的是源码不能运行，先进入目录编译。`cd redis-3.0.0`, `make`
        - 编译好了，再进行安装, 在 **源码包**(redis-3.0.0)里面执行命令，把`redis`安装到`/usr/local/src/redis`目录里，**注意目录结构**
            - `make PREFIX=/usr/local/src/redis install`: 然后`redis`目录下会多一个`bin`目录
    - 前端启动
        - `cd /usr/local/src/redis/bin` >> `./redis-server` : 启动redis服务
    - 后端启动        
        - 先把源码里的`redis.conf`复制到`bin`目录里，**注意目录结构**
            - 先确定是在源码目录下，`cp redis.conf ../bin/`, 
        - 再修改配置文件参数
            - `redis.conf`里的`daemonize on`修改为`yes`
            - 再执行 `./redis-server ./redis-conf`
            - 再执行 `./redis-cli`
            - 再`ping`
    - 连接客户端
        - 先开放`6379`这个端口，管理端口的文件是`/etc/sysconfig/iptables`
        - 再重启防火墙 `service iptables restart`, 会出现4个[确定]

### tomcat
    - 先解压 :`tar -xvf apache-tomcat-7.0.57.tar.gz`
    - 再启动 ：在`bin`目录下启动`startup.sh`
    - 启动查看日志 : `./startup.sh && tail -f ../logs/catalina.out` 


