package com.generator.example;

import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitframework.commons.utils.ResourceUtils;
import com.rabbitframework.generator.RabbitGenerator;
import com.rabbitframework.generator.RabbitGeneratorBuilder;

public class GeneratorRun {
	private static final Logger log = LoggerFactory.getLogger(GeneratorRun.class);

	public static void main(String[] args) throws Exception {
		log.debug("generator start");
		Reader reader = ResourceUtils.getResourceAsReader("generator-config.xml");
		try {
			RabbitGeneratorBuilder builder = new RabbitGeneratorBuilder();
			RabbitGenerator rabbitGenerator = builder.build(reader);
			rabbitGenerator.generator();
		} finally {
			reader.close();
		}
		log.debug("generator end");
	}
}
