#zookeeper说明
	1、配置说明
<table  border="1">
  <tr>
    	<td>配置</td>
     	<td>作用</td>
  </tr>
  <tr>
     	<td>tickTime</td>
      	<td>客户端和服务器之间的心跳间隔（毫秒）</td>
  </tr>
  <tr>
     	<td>initLimit</td>
      	<td>FOLLOWER与LEADER初始连接的最大延迟心跳数</td>
  </tr>
  <tr>
     	<td>syncLimit</td>
      	<td>FOLLOWER与LEADER请求应答的最大延迟心跳数</td>
  </tr>
  <tr>
     	<td>dataDir</td>
      	<td>数据文件目录</td>
  </tr>
  <tr>
     	<td>dataLogDir</td>
      	<td>日志文件目录</td>
  </tr>
  
  <tr>
     	<td>clientPort</td>
      	<td>客户端连接端口</td>
  </tr>
</table>

	2、命令说明:下载zookeeper后，命令文件在bin目录下
<table  border="1">
  <tr>
    	<td>命令</td>
     	<td>说明</td>
  </tr>
  <tr>
     	<td>zkServer.sh start</td>
      	<td>启动服务</td>
  </tr>
  <tr>
     	<td>zkServer.sh status</td>
      	<td>查看状态，如果mode为leader表示主节点</td>
  </tr>
  <tr>
     	<td>jps</td>
      	<td></td>
  </tr>
 <tr>
     	<td>zkServer.sh restart</td>
      	<td>重新启动服务</td>
  </tr>
  <tr>
     	<td>zkServer.sh stop</td>
      	<td>停止服务</td>
  </tr>
   <tr>
     	<td>zkCli.sh -server ip:port</td>
      	<td>客户端命令，进入zk控制台</td>
  </tr>
</table>

	3、单机与集群配置
		a、单机配置比较简单，直接下载zookeeper以后，就可以启动服务。
		b、集群配置:在linux环境中以单机配置多个zookeeper为例,这里配置三个服务：
			第一步：当下载zookeeper后，创建zokeeper目录，然后在此目录下分别创建server1,server2,server3目录,同时创建以下二个目录：
				mkdir data　：用于存储数据
				mkdir datalog　:　用于数据日志。
				然后，把zookeeper工程分别拷贝到server1,server2,server3目录中。
			第二步：在以上创建好的data目录中分别创建myid文件,编辑文件给服务器编号,分别输入1,2,3数字，不能重复，因为在集群中需要根据里面的数字进行集群服务的选址。
			第三步：在zookeeper工程目录下的conf目录下修改zoo.cfg文件，此文件可以拷贝zoo_sample.cfg或直接改名,然后在修改zoo.cfg文件中的以下内容(以下为示例):
				tickTime=2000
				#这个配置项是用来配置 Zookeeper 接受客户端（这里所说的客户端不是用户连接 Zookeeper 服务器的客户端，
				#而是 Zookeeper 服务器集群中连接到 Leader 的 Follower 服务器）初始化连接时最长能忍受多少个心跳时间间隔数。
				#当已经超过 10 个心跳的时间（也就是 tickTime）长度后 Zookeeper 服务器还没有收到客户端的返回信息，那么表明这个客户端连接失败。
				#总的时间长度就是 5*2000=10 秒
				initLimit=5
				#这个配置项标识 Leader 与 Follower 之间发送消息，请求和应答时间长度，最长不能超过多少个 tickTime 的时间长度，总的时间长度就是 2*2000=4 秒
				syncLimit=2
				dataDir=xxxx/zookeeper/server1/data
				dataLogDir=xxx/zookeeper/server1/dataLog
				clientPort=2181
				server.1=127.0.0.1:2000:2001
				server.2=127.0.0.1:3000:3001
				server.3=127.0.0.1:4000:4001
				以上server.1到server.3是集群配置项，说明有三台zookeeper服务，其中的数字与myid文件中的数字一一对应，
				其值的格式为：ip:followerport:leaderport，说明：
				(第一个端口(port)是从(follower)机器连接到主(leader)机器的端口，第二个端口是用来进行 leader选举的端口)
				
			第四步：到此服务配置完成，此步聚启动zookeeper服务，分别在各目录下的zookeeper的项目里输入：bin/zkServer.sh start，就会启动zookeeper服务，
				启动第一个服务时，会有报错，但这不影响，把其它几个服务启来，就好了，启动后，可以通过jps或zkServer.sh status命令查看，具体见以上输令说明。

	4、
