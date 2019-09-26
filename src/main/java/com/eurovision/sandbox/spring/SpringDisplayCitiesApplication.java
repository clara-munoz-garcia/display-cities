package com.eurovision.sandbox.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * SpringDisplayCitiesApplication
 * 
 * @author clara.munoz
 */
@SpringBootApplication(scanBasePackages = "com.eurovision.sandbox")
@EntityScan(basePackages = "com.eurovision.sandbox.vo")
@EnableJpaRepositories(basePackages = "com.eurovision.sandbox.repository")
public class SpringDisplayCitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDisplayCitiesApplication.class, args);
	}

}
