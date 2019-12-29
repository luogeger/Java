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
    
#### 文件搜索
- `whereis`, ``
    - 只能搜索命令的命令
- `locate`    
    - 只能搜索目录名
- `find`
- `grep` :字符串搜索

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
- 查看当前所在目录：`pwd` (Print Working Directory) 
- 创建目录: `mkdir` (Make Directory) 
- 删除目录：`rm -rf 目录名字`
    - `r` 就是向下递归，不管有多少级目录，一并删除
    - `f` 就是直接强行删除，不作任何提示的意思
    - `rmdir` (Remove Directory) 删除文件夹，只能删除空文件夹，不常用
- 切换目录：`cd` (Change Directory) 
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
- `wc` (Word Count) 字数信息统计
    - `wc index.html`
- `touch` 创建文件
    - `touch index.html`
- 修改文件名
-     
-  `rm` (remove) 删除文件，
    - `rm index.html` 删除文件
    - `rm -rf  blog` 删除目录
    - `rm -rf *` 删除当前目录下的所有

#### 压缩文件的操作
- `.tar` 打包文件
- `.gz` 压缩文件
- `.tar.gz` 打包并压缩的文件、
- 压缩 a.bxt 和 b.txt
    - `tar -zcvf ab.tar a.txt b.txt`: 压缩
    - `rm -rf *.txt` :再删除`a.txt`和`b.txt`
    - `tar -zxvf ab.tar` ： 再解压
- `unzip elasticsearch.6.3.0.zip -d ik-analyzer` 解压到当前新建的`ik-analyzer`目录下       


#### 网络
- `ip addr`


#### 端口
- 查看已开放的端口(默认不开放任何端口)
    - `firewall-cmd --list-ports`
- 开启80端口
    - `firewall-cmd --zone=public --add-port=80/tcp --permanent`
        - `=public`(作用域) `=80/tcp`(端口和通讯协议) `--permanent`(永久生效)
- 删除80端口
    - `firewall-cmd --zone= public --remove-port=80/tcp --permanent`
- 检查端口被哪个服务占用
    - `netstat -lnp|grep 8080`


#### 进程
- 查看进程：`ps -ef`
- 查看redis进程: `ps -aux|grep redis`


#### 服务
- 检测软件是否安装：``
- 服务的形式启动和关闭redis: `chkconfig --add redis`
- 开机自启服务：`chkconfig redis on`, `chkconfig rabbitmq-server on` 
- 启动服务：`service redis start`
- 停止服务：`service reids stop`
- 查看服务状态：`service reids status`
- 查看所有注册的脚本文件: `chkconfig --list`

#### 防火墙
- 查看防火墙状态: `firewall-cmd --state` 
- 关闭防火墙
    - `systemctl stop firewalld`
    - `systemctl stop firewalld.service`
- 重启防火墙
    - `firewall-cmd --reload` or `systemctl restart firewalld.service`
- 禁止防火墙开机启动
    - `systemctl disable firewalld.service`


#### 时间
    - `hwclock` 硬件时间
    - `date` 系统时间
    - 设置时间
        - `yum -y install ntp ntpdate`
        - `ntpdate cn.pool.ntp.org`


#### 其他
- `ifconfig eth0 up` : 权限不够
- `chkconfig --level 2345 network on` : 网络服务在系统启动级别是2345时默认启动


### 安装软件

#### rabbitMQ
- 检测rabbitmq是否安装成功: `rpm -qa|grep rabbitmq` 
- 启动rabbitmq: `service rabbitmq-server start`
    
    
#### redis
- 启动：
    - `./redis-server`    
    - 指定配置启动：`/home/redis-4.0.11/src/redis.service /home/redis-4.0.11/redis.conf`
    - 任何目录下启动：`service redis start`
    - 关闭服务：`service redis stop`  可能需要密码
