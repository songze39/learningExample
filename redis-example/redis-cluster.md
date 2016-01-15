#redis3集群配置
##配置集群需要至少修改或开启redis.conf文件中的以下项：
	port 7000   #端口号
	daemonize yes　#后台启动
	cluster-enabled yes  #集群配置
	cluster-config-file nodes.conf #开启后自动创建 
	cluster-node-timeout 5000   #集群结点超时
	appendonly yes  #开启AOF

##命令说明
	一、redis-trib.rb脚本：
	1. 创建集群命令 : redis-trib.rb create –replicas 1 127.0.0.1:7000 127.0.0.1:7001 \
			127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005
	    –replicas 1：表示我们希望为集群中的每个主节点创建一个从节点
	2. 检测集群命令：./redis-trib.rb check 127.0.0.1:7000
	  　　可以查看出主/从节点。
	3.　新增节点：./redis-trib.rb add-node  new_host:new_port existing_host:existing_port
	    	默认是主节点，需要分配solt
	4. 为新节点分配slot: redis-trib.rb reshard 10.10.34.14:6386
	5. 增加slave节点:/redis-trib.rb add-node –slave –master-id  ’ee05942ee38a56421a07eea01bc6072fe5e23bfd’ 127.0.0.1:7008  127.0.0.1:7000  
	
	二、集群：
	1. CLUSTER INFO 打印集群的信息  
	2. CLUSTER NODES 列出集群当前已知的所有节点（node），以及这些节点的相关信息。    
	
	三、节点：
	1. CLUSTER MEET <ip> <port> 将 ip 和 port 所指定的节点添加到集群当中，让它成为集群的一份子。  
	2. CLUSTER FORGET <node_id> 从集群中移除 node_id 指定的节点。  
	3. CLUSTER REPLICATE <node_id> 将当前节点设置为 node_id 指定的节点的从节点。  
	4. CLUSTER SAVECONFIG 将节点的配置文件保存到硬盘里面。  
	
	四、槽(slot):  
	1. CLUSTER ADDSLOTS <slot> [slot ...] 将一个或多个槽（slot）指派（assign）给当前节点。  
	2. CLUSTER DELSLOTS <slot> [slot ...] 移除一个或多个槽对当前节点的指派。  
	3. CLUSTER FLUSHSLOTS 移除指派给当前节点的所有槽，让当前节点变成一个没有指派任何槽的节点。  
	4. CLUSTER SETSLOT <slot> NODE <node_id>将槽 slot 指派给 node_id指定的节点,如果槽已经指派给另一个节点,那么先让另一个节点删除该槽>，然后再进行指派。  
	5. CLUSTER SETSLOT <slot> MIGRATING <node_id> 将本节点的槽 slot 迁移到 node_id 指定的节点中。  
	6. CLUSTER SETSLOT <slot> IMPORTING <node_id> 从 node_id 指定的节点中导入槽 slot 到本节点。  
	7. CLUSTER SETSLOT <slot> STABLE 取消对槽 slot 的导入（import）或者迁移（migrate）。  
	
	五、键 :
	1. CLUSTER KEYSLOT <key> 计算键 key 应该被放置在哪个槽上。  
	2. CLUSTER COUNTKEYSINSLOT <slot> 返回槽 slot 目前包含的键值对数量。  
	3. CLUSTER GETKEYSINSLOT <slot> <count> 返回 count 个 slot 槽中的键。  

	