package com.arm.isg.ds.onprem.simulator.device.data_generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DataGeneratorApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DataGeneratorApplication.class);

	private final ConfigurableApplicationContext context;

	public DataGeneratorApplication(@Autowired ConfigurableApplicationContext context) {
		this.context = context;
	}

	public static void main(String[] args) {
		SpringApplication.run(DataGeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.debug("Starting Device : DataGenerator");

		GenerateAndSend generateAndSend = new GenerateAndSend();
		generateAndSend.run();

		log.debug("Exiting Device DataGenerator");
		System.exit(SpringApplication.exit(context));
	}
}
