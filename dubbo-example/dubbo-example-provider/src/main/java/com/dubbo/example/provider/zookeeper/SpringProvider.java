package com.dubbo.example.provider.zookeeper;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringProvider {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:appcontext.xml");
		context.start();
		System.out.println("start dubbo server");
		System.in.read(); // 按任意键退出
		context.close();
	}
}
