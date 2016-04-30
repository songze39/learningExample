package com.dubbo.example.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dubbo.example.api.ZookeeperService;

public class ZookeeperServiceClient {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:appcontext.xml");
		context.start();
		ZookeeperService zookeeperService = (ZookeeperService) context.getBean("zookeeperService"); // 获取远程服务代理
		String hello = zookeeperService.sayHello("world"); // 执行远程方法
		System.out.println(hello); // 显示调用结果
		context.close();
	}
}
