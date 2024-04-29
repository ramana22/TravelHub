package com.travel.hub;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;
@SpringBootApplication(scanBasePackages = "com.travel.hub")
@EnableJpaRepositories(basePackages = "com.travel.repository")
@EntityScan(basePackages = "com.travel.model")
@ComponentScan(basePackages = "com.travel")
public class TravelHubBackendApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(TravelHubBackendApplication.class, args);
	}
	@Autowired
    private DataSource dataSource;

     @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Allow requests from any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                .allowedHeaders("*"); // Allowed headers
    }


    @PostConstruct
    public void testConnection() {
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("Connected to MySQL!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
