package com.example.RestCrudDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.RestCrudDemo.resource","com.example.RestCrudDemo.service"})
public class RestCrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestCrudDemoApplication.class, args);
	}

}
