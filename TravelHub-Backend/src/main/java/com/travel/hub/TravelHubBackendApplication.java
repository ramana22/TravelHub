package com.travel.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication(scanBasePackages = "com.travel.hub")
@EnableJpaRepositories(basePackages = "com.travel.repository")
@EntityScan(basePackages = "com.travel.model")
@ComponentScan(basePackages = "com.travel")
public class TravelHubBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(TravelHubBackendApplication.class, args);
	}
}
