package com.dubbo.example.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:appcontext.xml");
		context.start();
		// System.in.read(); // 按任意键退出
	}
}
