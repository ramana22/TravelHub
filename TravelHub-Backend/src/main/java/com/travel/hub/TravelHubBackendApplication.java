package com.travel.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@SpringBootApplication(scanBasePackages = "com.travel.hub")
@EnableJpaRepositories(basePackages = "com.travel.repository")
@EntityScan(basePackages = "com.travel.model")
@ComponentScan(basePackages = "com.travel")
public class TravelHubBackendApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(TravelHubBackendApplication.class, args);
	}

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Allow requests from any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                .allowedHeaders("*"); // Allowed headers
    }  
}
