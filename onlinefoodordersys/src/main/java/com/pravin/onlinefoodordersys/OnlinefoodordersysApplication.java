package com.pravin.onlinefoodordersys;

import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.ServletContextApplicationContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.*"})
@EnableSwagger2

public class OnlinefoodordersysApplication extends SpringBootServletInitializer{


	public static void main(String [] args) {
		SpringApplication.run(OnlinefoodordersysApplication.class, args);
	}
}
