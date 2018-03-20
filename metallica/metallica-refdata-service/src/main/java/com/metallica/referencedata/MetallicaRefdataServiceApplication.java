package com.metallica.referencedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages="com.metallica")
@EnableDiscoveryClient
public class MetallicaRefdataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetallicaRefdataServiceApplication.class, args);
	}
}
