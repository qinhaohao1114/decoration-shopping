package com.decoration.server.application;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekasApplication implements CommandLineRunner{

	private final static Logger logger = Logger.getLogger(EurekasApplication.class);
	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekasApplication.class).run(args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("rocky-eureka-server start OK");
	}
}
