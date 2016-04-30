package com.dubbo.example.provider.zookeeper;

import java.io.IOException;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.dubbo.example.api.ZookeeperService;

public class CodeProvider {

	public static void main(String[] args) throws IOException {
		// 服务实现
		ZookeeperService zookeeperService = new ZookeeperServiceImpl();
		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		application.setName("zookeeper-provider");
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setProtocol("zookeeper");
		registry.setAddress("192.168.2.112:2181,192.168.2.112:2182,192.168.2.112:2183");
//		registry.setUsername("aaa");
//		registry.setPassword("bbb");

		// 服务提供者协议配置
		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		protocol.setPort(20888);
		protocol.setThreads(200);

		// 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

		// 服务提供者暴露服务配置
		ServiceConfig<ZookeeperService> service = new ServiceConfig<ZookeeperService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
		service.setApplication(application);
		service.setRegistry(registry); // 多个注册中心可以用setRegistries()
		service.setProtocol(protocol); // 多个协议可以用setProtocols()
		service.setInterface(ZookeeperService.class);
		service.setRef(zookeeperService);
		service.setVersion("1.0.0");
		// 暴露及注册服务
		service.export();
		
		System.out.println("start dubbo server");
		System.in.read(); // 按任意键退出
	}

}
