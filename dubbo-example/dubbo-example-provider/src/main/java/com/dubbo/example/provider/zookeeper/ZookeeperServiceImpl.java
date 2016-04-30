package com.dubbo.example.provider.zookeeper;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dubbo.example.api.ZookeeperService;

public class ZookeeperServiceImpl implements ZookeeperService {

	@Override
	public String sayHello(String name) {
		System.out.println("["
				+ new SimpleDateFormat("HH:mm:ss").format(new Date())
				+ "] Hello " + name + ", request from consumer: "
				+ RpcContext.getContext().getRemoteAddress());
		return "Hello " + name + ", response form provider: "
				+ RpcContext.getContext().getLocalAddress();
	}

}
