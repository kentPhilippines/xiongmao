package com.payProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan(basePackages = "com.payProject.*")
public class application {
	public static void main(String[] args) {
		SpringApplication.run(application.class, args);
	}
}
