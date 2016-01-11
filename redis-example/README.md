#redis3测试,学习使用jedis3
	redis安装
	$ wget http://download.redis.io/releases/redis-3.0.6.tar.gz 
	$ tar xzf redis-3.0.6.tar.gz 
	$ cd redis-3.0.6 $ make

##启动redis服务
	$ src/redis-server
	加上号使redis以后台程序方式运行
	$ src/redis-server &
	启动时指定配置文件
	redis-server ./redis.conf
	
###redis服务启动指定配置文件
	配置文件 redis.conf 在Redis根目录下。
	#修改daemonize为yes，即默认以后台程序方式运行（还记得前面手动使用&号强制后台运行吗）。
	daemonize no
	#可修改默认监听端口
	port 6379
	#修改生成默认日志文件位置
	logfile "/home/futeng/logs/redis.log"
	#配置持久化文件存放位置
	dir /home/futeng/data/redisData

	#开启redis客户端
	$ src/redis-cli
	#如果更改了端口，使用`redis-cli`客户端连接时，也需要指定端口，例如：
	$ src/redis-cli -p 6380
	redis> set foo bar	
	OK
	redis> get foo
	"bar"


