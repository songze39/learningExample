package com.generator.example;

import java.io.Reader;

import com.rabbitframework.commons.utils.ResourceUtils;
import com.rabbitframework.generator.RabbitGenerator;
import com.rabbitframework.generator.RabbitGeneratorBuilder;

public class GeneratorTest {
	public static void main(String[] args) throws Exception {
		Reader reader = ResourceUtils
				.getResourceAsReader("generator-config.xml");
		try {
			RabbitGeneratorBuilder builder = new RabbitGeneratorBuilder();
			RabbitGenerator rabbitGenerator = builder.build(reader);
			rabbitGenerator.generator();
		} finally {
			reader.close();
		}
	}
}
