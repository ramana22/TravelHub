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

import jakarta.annotation.PostConstruct;
@SpringBootApplication(scanBasePackages = "com.travel.hub")
@EnableJpaRepositories(basePackages = "com.travel.repository")
@EntityScan(basePackages = "com.travel.model")
@ComponentScan(basePackages = "com.travel")
public class TravelHubBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(TravelHubBackendApplication.class, args);
	}
	@Autowired
    private DataSource dataSource;

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
