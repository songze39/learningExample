package com.dubbo.example.provider;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:appcontext.xml");
		context.start();
		System.in.read(); // 按任意键退出
		context.close();
	}
}
