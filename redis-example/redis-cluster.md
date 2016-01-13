#redis3集群配置
##配置集群需要至少修改或开启redis.conf文件中的以下项：
	port 7000   #端口号
	daemonize yes　#后台启动
	cluster-enabled yes  #集群配置
	cluster-config-file nodes.conf #开启后自动创建 
	cluster-node-timeout 5000   #集群结点超时
	appendonly yes  #开启AOF
		