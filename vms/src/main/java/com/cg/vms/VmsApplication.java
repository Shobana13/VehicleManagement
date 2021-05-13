package com.cg.vms;

import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class VmsApplication {
	org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	public static void main(String[] args) {
		SpringApplication.run(VmsApplication.class, args);
	}
	
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.paths(PathSelectors.any())
			.build();
	}

}
